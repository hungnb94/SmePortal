package com.example.hungnguyenbasv.d7_loginform.activity.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.adapter.MenuMainAdapter;
import com.example.hungnguyenbasv.d7_loginform.activity.fragment.UserFragment;


public class MenuMainActivity extends FragmentActivity {
    SharedPreferences sharedPreferences;
    String TAG = "MenuMainActivity TAG";
    UserFragment userFragment;
//    MessageFragment messageFragment;
//    DiscoverFragment discoverFragment;
//    MyFolderFragment myFolderFragment;
    ViewPager pager;
    TabLayout tabLayout;
    String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        sendDataFragment();
        initFragment();
    }

    private void sendDataFragment() {
        userFragment = new UserFragment();
//        Bundle bundle = getIntent().getExtras();
//        token = bundle.getString("token");

//        Lấy token từ sharedPreferences
        if (TextUtils.isEmpty(token)) {
            sharedPreferences = getSharedPreferences(LoginActivity.SHARED_PREFERENCES, MODE_PRIVATE);
            token = sharedPreferences.getString("token", "");
        }

        Bundle bundle = new Bundle();
        bundle.putString("token", token);
        userFragment.setArguments(bundle);
    }

    private void initFragment() {
        pager = (ViewPager) findViewById(R.id.view_pager_menu);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout_menu);
        FragmentManager manager = getSupportFragmentManager();

        final MenuMainAdapter adapter = new MenuMainAdapter(manager);
        adapter.setUserFragment(userFragment);

        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_user);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_chat);
        tabLayout.getTabAt(2).setIcon(R.drawable.discover);
        tabLayout.getTabAt(3).setIcon(R.drawable.my_folder);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    UserFragment userFragment = (UserFragment) adapter.getItem(0);
                    userFragment.initFragment();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
