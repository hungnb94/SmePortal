package com.example.hungnguyenbasv.d7_loginform.activity.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.activity.LoginActivity;
import com.example.hungnguyenbasv.d7_loginform.activity.adapter.UserAdapter;

import static android.content.Context.MODE_PRIVATE;

public class UserFragment extends Fragment {
    SharedPreferences sharedPreferences;
    String TAG = "UserFragment TAG";
    ProfileFragment profileFragment;
    ViewPager pager;
    TabLayout tabLayout;
    ImageView ivSetting;
    String token;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "On create");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        Toast.makeText(getContext(), "User Init", Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        initView(view);
        sendDataFragment();
        initFragment();
        return view;
    }

    public void initView(View view) {
        try {
            pager = (ViewPager) view.findViewById(R.id.view_pager_user);
            tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_user);
            ivSetting = (ImageView) view.findViewById(R.id.ivSetting);
            ivSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    //Khi được goi, fragment truyền vào sẽ thay thế vào vị trí FrameLayout trong Activity chính
                    transaction.replace(android.R.id.content, new SettingFragment());
                    transaction.commit();
                }
            });
        }catch (Exception e){

        }
    }

    private void sendDataFragment() {
        profileFragment = new ProfileFragment();
//        Bundle bundle = getActivity().getIntent().getExtras();
//        if (bundle.getString("token") != null) token = bundle.getString("token", "");

//        Lấy token từ sharedPreferences
        if (TextUtils.isEmpty(token)) {
            sharedPreferences = getActivity().getSharedPreferences(LoginActivity.SHARED_PREFERENCES, MODE_PRIVATE);
            token = sharedPreferences.getString("token", "");
        }

        Bundle bundle = new Bundle();
        bundle.putString("token", token);
        profileFragment.setArguments(bundle);
    }

    public void initFragment() {
        try {
            FragmentManager manager = getActivity().getSupportFragmentManager();
            final UserAdapter adapter = new UserAdapter(manager, getActivity());
            pager.setAdapter(adapter);
            tabLayout.setupWithViewPager(pager);
            pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        } catch (NullPointerException e) {

        }
    }
}
