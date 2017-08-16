package com.example.hungnguyenbasv.d7_loginform.activity.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.hungnguyenbasv.d7_loginform.activity.fragment.FollowersFragment;
import com.example.hungnguyenbasv.d7_loginform.activity.fragment.FollowingFragment;
import com.example.hungnguyenbasv.d7_loginform.activity.fragment.ProfileFragment;

/**
 * Created by hung.nguyenba.sv on 7/27/2017.
 */

public class UserAdapter extends FragmentStatePagerAdapter {
    Activity context;
    ProfileFragment profileFragment;
    FollowingFragment followingFragment;

    FollowersFragment followersFragment;

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public UserAdapter(FragmentManager fm, Activity context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                if (profileFragment == null) profileFragment = new ProfileFragment();
                frag = profileFragment;
                break;
            case 1:
                if (followingFragment == null) followingFragment = new FollowingFragment(context);
                frag = followingFragment;
                break;
            case 2:
                if (followersFragment == null) followersFragment = new FollowersFragment();
                frag = followersFragment;
                break;
            default:
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Profile";
            case 1:
                return "Following";
            default:
                return "Followers";
        }
    }
}
