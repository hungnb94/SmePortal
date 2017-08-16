package com.example.hungnguyenbasv.d7_loginform.activity.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hungnguyenbasv.d7_loginform.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by hung.nguyenba.sv on 8/16/2017.
 */

public class AccountDetailFragment extends Fragment {
    private ImageButton btnAccountDetailsOut;
    private TextView tvEmail;
    private View root;
    private RelativeLayout Email, btnChangePassword;

    public AccountDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.account_detail_fragment, container, false);
        addControl();
        addEvent();
        RetrievePreferences();
        return root;
    }

    public void addControl() {
        btnAccountDetailsOut = (ImageButton) root.findViewById(R.id.btnAccountDetailsOut);
        Email = (RelativeLayout) root.findViewById(R.id.Email);
        tvEmail = (TextView) root.findViewById(R.id.tvEmail);
        btnChangePassword = (RelativeLayout) root.findViewById(R.id.RlChangePassword);
    }

    public void addEvent() {
        btnAccountDetailsOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(AccountDetailFragment.this).commit();
            }
        });
        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                //Khi được goi, fragment truyền vào sẽ thay thế vào vị trí FrameLayout trong Activity chính
                transaction.add(android.R.id.content, new EmailChangeFragment());
                transaction.commit();

//                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                EmailChangeFragment emailChangeFragment = new EmailChangeFragment();
//                fragmentTransaction.add(android.R.id.content,emailChangeFragment);
//                fragmentTransaction.commit();
            }
        });
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                //Khi được goi, fragment truyền vào sẽ thay thế vào vị trí FrameLayout trong Activity chính
                transaction.add(android.R.id.content, new PasswordChangeFragment());
                transaction.commit();

            }
        });
    }

    public void RetrievePreferences() {
        // Lấy ra giá trị đã lưu
        SharedPreferences prefs = getActivity().getSharedPreferences("MyFile", MODE_PRIVATE);
        if (prefs != null) {
            tvEmail.setText(prefs.getString("email", ""));
        } else System.out.println("check: failPreferrences");
    }
}