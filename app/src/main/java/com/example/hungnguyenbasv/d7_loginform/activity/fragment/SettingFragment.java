package com.example.hungnguyenbasv.d7_loginform.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.activity.LoginActivity;

public class SettingFragment extends Fragment {
    ImageButton ibtnBack;
    RelativeLayout rlEditProfile, rlAccountDetails, rlNotifications, rlBlockedUsers, rlLogout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        initView(view);
        addEvent();
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        return view;
    }

    private void initView(View view) {
        ibtnBack = (ImageButton) view.findViewById(R.id.ibtnBack);
        rlEditProfile = (RelativeLayout) view.findViewById(R.id.rlEditProfile);
        rlAccountDetails = (RelativeLayout) view.findViewById(R.id.rlAccountDetails);
        rlNotifications = (RelativeLayout) view.findViewById(R.id.rlNotifications);
        rlBlockedUsers = (RelativeLayout) view.findViewById(R.id.rlBlockedUsers);
        rlLogout = (RelativeLayout) view.findViewById(R.id.rlLogout);
    }

    private void addEvent() {
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
        rlEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile();
            }
        });
        rlAccountDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accountDetails();
            }
        });
        rlNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notification();
            }
        });
        rlBlockedUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blockedUser();
            }
        });
        rlLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void back() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(SettingFragment.this).commit();
    }

    private void editProfile() {
    }

    private void accountDetails() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //Khi được goi, fragment truyền vào sẽ thay thế vào vị trí FrameLayout trong Activity chính
        transaction.replace(android.R.id.content, new AccountDetailFragment());
        transaction.commit();

    }

    private void notification() {
    }

    private void blockedUser() {

    }

    private void logout() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
