package com.example.dell.big_wanandroid.Project.fragmentP;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;

import com.example.dell.big_wanandroid.Project.adapter.XlvAdapterlistP;
import com.example.dell.big_wanandroid.Project.bean.ListBeanP;
import com.example.dell.big_wanandroid.Project.presenter.ProjectListP;
import com.example.dell.big_wanandroid.Project.ui.PrjWebActivity;
import com.example.dell.big_wanandroid.Project.view.ProjectListView;
import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.base.BaseFragment;
import com.example.dell.big_wanandroid.utils.RlvHideUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;


/**
 * Created by Dell on 2019/4/29.
 */
public class FragmentListP extends BaseFragment<ProjectListP, ProjectListView> implements ProjectListView {
    @BindView(R.id.smr)
    SmartRefreshLayout smr;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.flbtn)
    FloatingActionButton flbtn;
    private int page = 1;
    private int cid;
    private XlvAdapterlistP adapterlistP;
    private List<ListBeanP.DataBean.DatasBean> list;

    @Override
    protected ProjectListP initpresenter() {
        return new ProjectListP();
    }

    @Override
    protected int getlayout() {
        return R.layout.projects;
    }

    @Override
    protected void initView() {
        cid = getArguments().getInt("cid");
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));


        smr.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
                smr.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initData();
                smr.finishRefresh();
            }
        });


    }

    @Override
    protected void initData() {
        presenter.getData(page, cid);
    }

    @Override
    public void SetListData(ListBeanP listBeanP) {
        list = listBeanP.getData().getDatas();
        adapterlistP = new XlvAdapterlistP(getContext(), list);
        rlv.setAdapter(adapterlistP);
        adapterlistP.SetOnItemClickLisener(new XlvAdapterlistP.OnItemClickLisener() {
            @Override
            public void OnItemClickLisener(int position) {
                Intent intent = new Intent(getContext(), PrjWebActivity.class);
                intent.putExtra("url", list.get(position).getLink());
                intent.putExtra("tt", list.get(position).getTitle());
                intent.putExtra("author", list.get(position).getAuthor());
                intent.putExtra("charname", list.get(position).getChapterName());
                intent.putExtra("time", list.get(position).getNiceDate());
                startActivity(intent);
            }
        });
        final RadioGroup rgbtn = getActivity().findViewById(R.id.rg);


        RlvHideUtils.TabHide(rgbtn, rlv, flbtn);
        RlvHideUtils.OnTabClicks(flbtn, rlv);
    }


}
