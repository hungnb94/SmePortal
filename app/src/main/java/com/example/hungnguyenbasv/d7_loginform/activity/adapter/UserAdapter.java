package com.example.hungnguyenbasv.d7_loginform.activity.adapter;

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
    ProfileFragment profileFragment;
    FollowingFragment followingFragment;
    FollowersFragment followersFragment;


    public void setProfileFragment(ProfileFragment profileFragment) {
        this.profileFragment = profileFragment;
    }

    public void setFollowingFragment(FollowingFragment followingFragment) {
        this.followingFragment = followingFragment;
    }

    public void setFollowersFragment(FollowersFragment followersFragment) {
        this.followersFragment = followersFragment;
    }

    public UserAdapter(FragmentManager fm) {
        super(fm);
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
                if (followingFragment == null) followingFragment = new FollowingFragment();
                frag = followingFragment;
                break;
            default:
                if (followersFragment == null) followersFragment = new FollowersFragment();
                frag = followersFragment;
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
        switch (position){
            case 0: return "Profile";
            case 1: return "Following";
            default: return "Followers";
        }
    }
}
