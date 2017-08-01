package com.example.hungnguyenbasv.d7_loginform.activity.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.model.LoginSuccessResponse;
import com.example.hungnguyenbasv.d7_loginform.activity.model.Message;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIService;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIUtils;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sharedPreferences;
    public static final String SHARED_PREFERENCES = "MY_SHARED_PREFERENCES";
    EditText edtEmail, edtPassword;
    Button btnSignIn;
    CheckBox cbRemember;
    TextView tvSignup, tvForgotPassword;
    private String TAG = "LoginActivity TAG";
    ProgressDialog pDialog;
    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        addEvent();
        initParams();
    }

    private void initView() {
        tvSignup = (TextView) findViewById(R.id.tvSignup);
        tvForgotPassword = (TextView) findViewById(R.id.tvForgotPassword);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

        cbRemember = (CheckBox) findViewById(R.id.checkbox);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);

    }

    private void addEvent() {
        // Set click listener
        tvSignup.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);

        btnSignIn.setOnClickListener(this);
    }

    private void initParams() {
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        String saveLogin = sharedPreferences.getString("saveLogin", String.valueOf(false));
        if (saveLogin.equals(true + "")) {
            String email = sharedPreferences.getString("email", "");
            String password = sharedPreferences.getString("password", "");
            edtEmail.setText(email);
            edtPassword.setText(password);
            cbRemember.setChecked(true);
        } else {
            cbRemember.setChecked(false);
        }
    }

    public void clickRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSignup:
                clickRegister();
                break;
            case R.id.tvForgotPassword:
                clickForgotPassword();
                break;
            case R.id.btnSignIn:
                login();
                break;
            default:
                break;
        }
    }

    private void clickForgotPassword() {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    /*
     * Đăng nhập sử dụng retrofit
     *
     * @param email - email đăng nhập
     *
     * @param password - password đăng nhập
     *
     * @return
     */
    private void loginRetrofit(String email, String password) {
        mAPIService = APIUtils.getAPIService();
        mAPIService.login(email, password).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (pDialog.isShowing()) pDialog.dismiss();
                if (response.isSuccessful()) {
                    try {
                        String res = response.body().string();
                        Log.e(TAG, "onResponse 1" + res);
                        Gson gson = new Gson();
                        Message message = gson.fromJson(res, Message.class);

                        if (message.getStatus() == 1) {
                            LoginSuccessResponse loginSuccessResponse =
                                    gson.fromJson(res, LoginSuccessResponse.class);
                            Log.e(TAG, "LoginSuccessResponse token" + loginSuccessResponse.getData().getToken());

                            // Lưu token để dùng đi dùng lại, không phải mất công login nhiều lần nữa
                            if (sharedPreferences == null) {
                                sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
                            }
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("token", loginSuccessResponse.getData().getToken());
                            editor.commit();

                            Intent intent = new Intent(LoginActivity.this, MenuMainActivity.class);
//                            intent.putExtra("token", loginSuccessResponse.getData().getToken());
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "onResponse exception");
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (pDialog.isShowing()) pDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void login() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //Lưu thông tin email, password
        if (cbRemember.isChecked()) {
            editor.putString("email", email);
            editor.putString("password", password);
            editor.putString("saveLogin", true + "");
        } else {
            editor.putString("saveLogin", false + "");
        }
        editor.commit();

        // Kiểm tra trường nhập hợp lệ, sau đó đăng nhập
        if (checkParam(email, password) == true) {
            // Hiện dialog chờ trong khi gửi thông tin đăng nhập
            pDialog = new ProgressDialog(this);
            pDialog.setMessage(getResources().getString(R.string.please_wait));
            pDialog.setCancelable(false);
            pDialog.show();
            loginRetrofit(email, password);
        } else {
            if (email == null || email.length() == 0) {
                edtEmail.setError(getResources().getString(R.string.this_field_cannot_be_blank));
            }
            if (password == null || password.length() == 0) {
                edtPassword.setError(getResources().getString(R.string.this_field_cannot_be_blank));
            }
        }
    }

    /*
     * Kiểm tra trường nhập hợp lệ.
     *
     * @param email - email đăng nhập
     *
     * @param password - password đăng nhập
     *
     * @return true - trường nhập hợp lệ
     */
    public boolean checkParam(String email, String password) {
        if (email == null || password == null) return false;
        if (email.length() == 0 || password.length() == 0) return false;
        return true;
    }

}
