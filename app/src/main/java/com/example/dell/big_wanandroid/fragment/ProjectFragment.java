package com.example.dell.big_wanandroid.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.example.dell.big_wanandroid.Project.adapter.VpAdapterP;
import com.example.dell.big_wanandroid.Project.bean.TabBeanP;
import com.example.dell.big_wanandroid.Project.fragmentP.FragmentListP;
import com.example.dell.big_wanandroid.Project.presenter.ProjectP;
import com.example.dell.big_wanandroid.Project.view.ProjectView;
import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.base.BaseFragment;
import com.example.dell.big_wanandroid.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Dell on 2019/4/26.
 */

public class ProjectFragment extends BaseFragment<ProjectP, ProjectView> implements ProjectView {


    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;

    @Override
    protected ProjectP initpresenter() {
        return new ProjectP();
    }

    @Override
    protected int getlayout() {
        return R.layout.project;
    }

    @Override
    protected void initView() {
        //tab的之间的纵向分割线
        LinearLayout linearLayout= (LinearLayout) tab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(),R.drawable.tablayout_divider_vetical));
        linearLayout.setDividerPadding(8);



    }

    @Override
    protected void initData() {
        presenter.getTabData();
    }

    @Override
    public void SetTabData(TabBeanP tabBeanP) {
        List<TabBeanP.DataBean> list = tabBeanP.getData();
        ArrayList<FragmentListP> fragmentListPS = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            FragmentListP listP = new FragmentListP();
            Bundle bundle = new Bundle();
            bundle.putInt("cid",list.get(i).getId());
            listP.setArguments(bundle);
            fragmentListPS.add(listP);
        }

        VpAdapterP vpAdapterP = new VpAdapterP(getChildFragmentManager(), fragmentListPS, list);
        vp.setAdapter(vpAdapterP);
        tab.setupWithViewPager(vp);
    }

    @Override
    public void SetOnFail(String msg) {
        ToastUtil.showShort(msg);
    }


}
