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
import android.widget.Toast;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.activity.LoginActivity;
import com.example.hungnguyenbasv.d7_loginform.activity.model.ChangeEmailResponse;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIService;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by hung.nguyenba.sv on 8/16/2017.
 */

public class EmailChangeFragment extends Fragment {
    private View root;
    private APIService apiService;
    private ImageView btnChangeEmailOut;
    private Button btnChangeEmail;
    private EditText tvEmail, tvNewEmail;
    private String TAG = "Check ChangEmail:";
    private String token, password;

    public EmailChangeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.email_change_fragment, container, false);
        addControl();
        RetrievePreferences();
        apiService = APIUtils.getAPIService();
        token = getActivity().getSharedPreferences
                (
                        LoginActivity.SHARED_PREFERENCES,
                        MODE_PRIVATE
                ).getString("token", "");
        addEvent();
        return root;
    }

    public void addControl() {
        tvEmail = (EditText) root.findViewById(R.id.tvEmail);
        btnChangeEmailOut = (ImageView) root.findViewById(R.id.btnChangeEmailOut);
        btnChangeEmail = (Button) root.findViewById(R.id.btnChangeEmail);
        tvNewEmail = (EditText) root.findViewById(R.id.tvNewEmail);
    }

    public void addEvent() {
        btnChangeEmailOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(EmailChangeFragment.this).commit();
            }
        });
        btnChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvNewEmail.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Email Not Empty", Toast.LENGTH_SHORT).show();
                } else
                    sendEmail(token, tvNewEmail.getText().toString());

            }
        });
    }

    public void RetrievePreferences() {
        // Lấy ra giá trị đã lưu
        SharedPreferences prefs = getActivity().getSharedPreferences("MyFile", MODE_PRIVATE);
        if (prefs != null) {
            tvEmail.setText(prefs.getString("email", ""));
            password = prefs.getString("password", "");
        } else System.out.println("check: failPreferrences");
    }

    public void SettingPreferences(String txtEmail, String txtPassWord) {
        // File chia sẻ sử dụng trong nội bộ ứng dụng, hoặc các ứng dụng được chia sẻ cùng User.
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("MyFile", MODE_PRIVATE).edit();
        editor.putString("email", txtEmail);
        editor.putString("password", txtPassWord);
        editor.apply();
    }

    public void sendEmail(String token, final String email) {
        apiService.changeEmail(token, email).enqueue(new Callback<ChangeEmailResponse>() {
            @Override
            public void onResponse(Call<ChangeEmailResponse> call, Response<ChangeEmailResponse> response) {
                if (response.isSuccessful()) {
                    getActivity().getSharedPreferences("MyFile", 0).edit().clear().commit();
                    SettingPreferences(tvNewEmail.getText().toString(), password);
                    Toast.makeText(getActivity(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "post submitted to API: ");
                }
            }

            @Override
            public void onFailure(Call<ChangeEmailResponse> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });

    }
}