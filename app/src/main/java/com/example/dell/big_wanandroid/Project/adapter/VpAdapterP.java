package com.example.dell.big_wanandroid.Project.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dell.big_wanandroid.Project.bean.TabBeanP;
import com.example.dell.big_wanandroid.Project.fragmentP.FragmentListP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2019/4/29.
 */

public class VpAdapterP extends FragmentPagerAdapter {
    private final ArrayList<FragmentListP> fragmentListPS;
    private final List<TabBeanP.DataBean> list;

    public VpAdapterP(FragmentManager fm, ArrayList<FragmentListP> fragmentListPS, List<TabBeanP.DataBean> list) {
        super(fm);
        this.fragmentListPS = fragmentListPS;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentListPS.get(position);
    }

    @Override
    public int getCount() {
        return fragmentListPS.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getName();
    }
}
