package com.example.hungnguyenbasv.d7_loginform.activity.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.model.Message;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIService;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by hung.nguyenba.sv on 7/18/2017.
 */

public class RegisterActivity extends Activity implements View.OnClickListener {
    static final String TAG = "RegisterActivity Log";
    TextView tvLogin;
    EditText edtFirstname, edtLastname, edtEmail, edtPassword, edtReenterPass;
    Button btnRegister;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        addEvent();
    }

    private void addEvent() {
        btnRegister.setOnClickListener(this);

        tvLogin.setOnClickListener(this);
    }

    private void initView() {
        tvLogin = (TextView) findViewById(R.id.tvLogin);

        edtFirstname = (EditText) findViewById(R.id.edtFirstname);
        edtLastname = (EditText) findViewById(R.id.edtLastname);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtReenterPass = (EditText) findViewById(R.id.edtConfirmPass);

        btnRegister = (Button) findViewById(R.id.btnRegister);
    }

    public void clickLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvLogin:
                clickLogin();
                break;
            case R.id.btnRegister:
                clickRegister();
                break;
            default:
                break;
        }
    }

    private void clickRegister() {
        String firstName = edtFirstname.getText().toString();
        String lastName = edtLastname.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String confirmPass = edtReenterPass.getText().toString();

        if (password.equals(confirmPass) == false) {
            edtReenterPass.setBackgroundColor(Color.RED);
            return;
        } else {
            edtReenterPass.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGray));
        }
        // Hiện progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        registerRetrofit(firstName, lastName, email, password);
    }

    private void registerRetrofit(String firstName, String lastName, String email, String password) {
        APIService mAPIService = APIUtils.getAPIService();
        mAPIService.register(firstName, lastName, email, password).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, retrofit2.Response<Message> response) {
                if (pDialog.isShowing()) pDialog.dismiss();
                if (response.isSuccessful()) {
                    Message message = response.body();
                    Toast.makeText(RegisterActivity.this,
                            message.getMessage(),
                            Toast.LENGTH_SHORT).show();
                    if (message.getStatus() == 1){
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                if (pDialog.isShowing()) pDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
