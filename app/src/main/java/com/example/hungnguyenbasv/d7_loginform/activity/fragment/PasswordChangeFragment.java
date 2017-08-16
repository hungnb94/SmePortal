package com.example.hungnguyenbasv.d7_loginform.activity.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.activity.LoginActivity;
import com.example.hungnguyenbasv.d7_loginform.activity.model.ChangePasswordResponse;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIService;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by hung.nguyenba.sv on 8/16/2017.
 */

public class PasswordChangeFragment extends Fragment {
    private View root;
    private TextView txtPassword;
    private EditText txtNewPassword,txtConfrimPassword;
    private String TAG="Check ChangePassword";
    private ImageView btnChangePasswordOut;
    private Button btnTiepTuc;
    private String token,email;
    private APIService apiService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.password_change_fragment, container, false);
        apiService = APIUtils.getAPIService();
        addControl();
        addEvent();
        RetrievePreferences();
        token = getActivity().getSharedPreferences
                (
                        LoginActivity.SHARED_PREFERENCES,
                        MODE_PRIVATE
                ).getString("token", "");
        return root;
    }
    public void addControl(){
        txtPassword = (TextView) root.findViewById(R.id.tvPassword);
        btnChangePasswordOut = (ImageView) root.findViewById(R.id.btnChangePasswordOut);
        txtNewPassword = (EditText) root.findViewById(R.id.tvNewPassword);
        txtConfrimPassword = (EditText) root.findViewById(R.id.tvConfirmPassword);
        btnTiepTuc = (Button) root.findViewById(R.id.btnChange);
    }
    public void addEvent(){
        btnChangePasswordOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(PasswordChangeFragment.this).commit();
            }
        });
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNewPassword.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Old Password not empty", Toast.LENGTH_SHORT).show();
                }else if(txtNewPassword.getText().toString().equals(txtConfrimPassword.getText().toString())){
                    sendPassword(token,txtPassword.getText().toString(),txtNewPassword.getText().toString());
                }else {
                    Toast.makeText(getActivity(), "Not Enter the correct password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void RetrievePreferences(){
        // Lấy ra giá trị đã lưu
        SharedPreferences prefs = getActivity().getSharedPreferences("MyFile", MODE_PRIVATE);
        if (prefs != null) {
            txtPassword.setText(prefs.getString("password",""));
            email = prefs.getString("email","");
        }else System.out.println("check: failPreferrences");
    }
    public void SettingPreferences(String txtEmail,String txtPassWord){
        // File chia sẻ sử dụng trong nội bộ ứng dụng, hoặc các ứng dụng được chia sẻ cùng User.
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("MyFile", MODE_PRIVATE).edit();
        editor.putString("email",txtEmail);
        editor.putString("password",txtPassWord);
        editor.apply();
    }
    public void sendPassword(String token, String old_password,String password) {
        apiService.changePassowrd(token, old_password,password).enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                if(response.isSuccessful()) {
                    getActivity().getSharedPreferences("MyFile", 0).edit().clear().commit();
                    SettingPreferences(email,txtNewPassword.getText().toString());
                    Toast.makeText(getActivity(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "post submitted to API: ");
                }
            }
            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });

    }
}