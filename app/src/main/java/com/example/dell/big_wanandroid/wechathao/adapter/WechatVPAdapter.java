package com.example.dell.big_wanandroid.wechathao.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dell.big_wanandroid.wechathao.bean.WechatTabBean;
import com.example.dell.big_wanandroid.wechathao.fragment.WechatListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2019/5/2.
 */

public class WechatVPAdapter extends FragmentPagerAdapter {
    private final ArrayList<WechatListFragment> wechatListFragments;
    private final List<WechatTabBean.DataBean> list;

    public WechatVPAdapter(FragmentManager fm, ArrayList<WechatListFragment> wechatListFragments, List<WechatTabBean.DataBean> list) {
        super(fm);
        this.wechatListFragments = wechatListFragments;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return wechatListFragments.get(position);
    }

    @Override
    public int getCount() {
        return wechatListFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getName();
    }
}
