package com.example.hungnguyenbasv.d7_loginform.activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.hungnguyenbasv.d7_loginform.activity.fragment.DiscoverFragment;
import com.example.hungnguyenbasv.d7_loginform.activity.fragment.MessageFragment;
import com.example.hungnguyenbasv.d7_loginform.activity.fragment.MyFolderFragment;
import com.example.hungnguyenbasv.d7_loginform.activity.fragment.UserFragment;

/**
 * Created by hung.nguyenba.sv on 7/24/2017.
 */

public class MenuMainAdapter extends FragmentStatePagerAdapter {
    UserFragment userFragment;
    MessageFragment messageFragment;
    DiscoverFragment discoverFragment;
    MyFolderFragment myFolderFragment;

    public void setUserFragment(UserFragment userFragment) {
        this.userFragment = userFragment;
    }

    public void setMessageFragment(MessageFragment messageFragment) {
        this.messageFragment = messageFragment;
    }


    public void setDiscoverFragment(DiscoverFragment discoverFragment) {
        this.discoverFragment = discoverFragment;
    }

    public void setMyFolderFragment(MyFolderFragment myFolderFragment) {
        this.myFolderFragment = myFolderFragment;
    }


    public MenuMainAdapter(FragmentManager fm) {
        super(fm);
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        switch (position) {
//            case 0:
//                return "User";
//            case 1:
//                return "Message";
//            case 2:
//                return "Discover";
//            default:
//                return "My folder";
//        }
//    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (userFragment == null) userFragment= new UserFragment();
                userFragment.initFragment();
                return userFragment;
            case 1:
                if (messageFragment == null) messageFragment = new MessageFragment();
                return messageFragment;
            case 2:
                if (discoverFragment == null) discoverFragment = new DiscoverFragment();
                return discoverFragment;
            case 3:
                if (myFolderFragment == null) myFolderFragment = new MyFolderFragment();
                return myFolderFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

}
