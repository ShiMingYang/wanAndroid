package com.example.dell.big_wanandroid.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioGroup;

import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.base.BaseFragment;
import com.example.dell.big_wanandroid.study.adapter.StudyAdapter;
import com.example.dell.big_wanandroid.study.bean.StudyBean;
import com.example.dell.big_wanandroid.study.presenter.Studyp;
import com.example.dell.big_wanandroid.study.ui.ListVPActivity;
import com.example.dell.big_wanandroid.study.view.StudyV;
import com.example.dell.big_wanandroid.utils.RlvHideUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Dell on 2019/4/26.
 */

public class StudyFragment extends BaseFragment<Studyp, StudyV> implements StudyV {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.smr)
    SmartRefreshLayout smr;
    @BindView(R.id.flbtn)
    FloatingActionButton flbtn;
    private StudyAdapter adapter;
    private List<StudyBean.DataBean> list;


    @Override
    protected Studyp initpresenter() {
        return new Studyp();
    }

    @Override
    protected int getlayout() {
        return R.layout.study;
    }

    @Override
    protected void initData() {
        presenter.getdata();
    }

    @Override
    protected void initView() {
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));

        final RadioGroup rgbtn = getActivity().findViewById(R.id.rg);
        RlvHideUtils.Hide(rgbtn, rlv, flbtn);
        RlvHideUtils.OnClicks(flbtn, rlv);

        smr.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                initData();
                smr.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData();
                smr.finishRefresh();
            }
        });


    }

    @Override
    public void setData(StudyBean bean) {
        list = bean.getData();
        adapter = new StudyAdapter(getContext(), list);
        rlv.setAdapter(adapter);
        adapter.SetOnItemClickLisener(new StudyAdapter.OnItemClickLisener() {
            @Override
            public void OnItemClickLisener(StudyBean.DataBean name, int position) {
                Intent intent = new Intent(getContext(), ListVPActivity.class);
                intent.putExtra("child", name);

                intent.putExtra("name", list.get(position).getName());
                startActivity(intent);
            }
        });
    }

    @Override
    public void seterror(String msg) {

    }

}
