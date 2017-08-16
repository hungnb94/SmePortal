package com.example.hungnguyenbasv.d7_loginform.activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.hungnguyenbasv.d7_loginform.activity.fragment.CommentsFragment;
import com.example.hungnguyenbasv.d7_loginform.activity.fragment.DetailsFragment;
import com.example.hungnguyenbasv.d7_loginform.activity.fragment.TeamFragment;
import com.example.hungnguyenbasv.d7_loginform.activity.fragment.UpdatesProjectInforFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hung.nguyenba.sv on 8/16/2017.
 */

public class ProjectInforAdapter extends FragmentStatePagerAdapter{
    List<Fragment> fragmentList = new ArrayList<>();

    public ProjectInforAdapter(FragmentManager fm) {
        super(fm);
        fragmentList.add(new DetailsFragment());
        fragmentList.add(new TeamFragment());
        fragmentList.add(new UpdatesProjectInforFragment());
        fragmentList.add(new CommentsFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Details";
            case 1:
                return "The team";
            case 2:
                return "Update";
            default:
                return "Comments";
        }
    }
}
