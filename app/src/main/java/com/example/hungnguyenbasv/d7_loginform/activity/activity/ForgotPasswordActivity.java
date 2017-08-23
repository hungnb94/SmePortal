package com.example.hungnguyenbasv.d7_loginform.activity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.model.ResetPasswordResponse;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIService;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText edtEmail;
    Button btnReset;
    ImageView imvClose;
    private String TAG = "ForgotPasswordActivity TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        btnReset = (Button) findViewById(R.id.btnResetPassword);
        imvClose = (ImageView) findViewById(R.id.imvClose);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void resetPassword() {
        String email = edtEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            edtEmail.setError(getResources().getString(R.string.this_field_cannot_be_blank));
            return;
        }
        APIService mApiService = APIUtils.getAPIService();
        mApiService.resetPassword(email).enqueue(new Callback<ResetPasswordResponse>() {
            @Override
            public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {
                Log.e(TAG, "Reset password failure " + t.toString());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.reset_password_failure), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
