package com.example.dell.big_wanandroid.study.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.View.EmapyV;
import com.example.dell.big_wanandroid.base.BaseActivity;
import com.example.dell.big_wanandroid.presenter.EmapyP;
import com.example.dell.big_wanandroid.study.adapter.ListVpAdapterS;
import com.example.dell.big_wanandroid.study.bean.StudyBean;
import com.example.dell.big_wanandroid.study.fragment.StudyListFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListVPActivity extends BaseActivity<EmapyP, EmapyV> implements EmapyV {


    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private String name;
    private List<StudyBean.DataBean.ChildrenBean> children;

    @Override
    protected EmapyP initPresenter() {
        return new EmapyP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_list_vp;
    }




    @Override
    protected void initView() {
        name = getIntent().getStringExtra("name");
        StudyBean.DataBean child = (StudyBean.DataBean) getIntent().getSerializableExtra("child");
        List<StudyBean.DataBean.ChildrenBean> list = child.getChildren();

        toolbar.setTitle(name);
        setSupportActionBar(toolbar);

        ArrayList<StudyListFragment> studyListFragments = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            StudyListFragment listFragment = new StudyListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("cid", list.get(i).getId());
            listFragment.setArguments(bundle);
            studyListFragments.add(listFragment);
        }


        ListVpAdapterS vpAdapterS = new ListVpAdapterS(getSupportFragmentManager(), studyListFragments,list);
        vp.setAdapter(vpAdapterS);
        tab.setupWithViewPager(vp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });


    }
}
