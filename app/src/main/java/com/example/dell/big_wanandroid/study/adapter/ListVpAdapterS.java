package com.example.dell.big_wanandroid.study.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.study.bean.StudyBean;
import com.example.dell.big_wanandroid.study.fragment.StudyListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2019/4/30.
 */

public class ListVpAdapterS extends FragmentPagerAdapter {


    private final ArrayList<StudyListFragment> studyListFragments;
    private final List<StudyBean.DataBean.ChildrenBean> children;


    public ListVpAdapterS(FragmentManager supportFragmentManager, ArrayList<StudyListFragment> studyListFragments, List<StudyBean.DataBean.ChildrenBean> children) {
        super(supportFragmentManager);
        this.studyListFragments = studyListFragments;
        this.children = children;
    }

    @Override
    public Fragment getItem(int position) {
        return studyListFragments.get(position);
    }

    @Override
    public int getCount() {
        return studyListFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return children.get(position).getName();
    }
}
