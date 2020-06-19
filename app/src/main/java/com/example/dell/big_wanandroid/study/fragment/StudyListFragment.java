package com.example.dell.big_wanandroid.study.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.base.BaseFragment;
import com.example.dell.big_wanandroid.study.adapter.ListPSSXlvAdapter;
import com.example.dell.big_wanandroid.study.bean.ListBeanSS;
import com.example.dell.big_wanandroid.study.presenter.ListPS;
import com.example.dell.big_wanandroid.study.ui.StudyWebActivity;
import com.example.dell.big_wanandroid.study.view.ListVS;
import com.example.dell.big_wanandroid.utils.RlvHideUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Dell on 2019/4/30.
 */

public class StudyListFragment extends BaseFragment<ListPS, ListVS> implements ListVS {
    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.smr)
    SmartRefreshLayout smr;
    @BindView(R.id.flbtn)
    FloatingActionButton flbtn;
    Unbinder unbinder;
    private int cid;
    private int page = 0;
    private boolean isScroll;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handler.sendEmptyMessage(0);

        }
    };

         public void getdata(){

             handler.postDelayed(new Runnable() {
                 @Override
                 public void run() {

                 }
             },1000);
         }
    @Override
    protected ListPS initpresenter() {
        return new ListPS();
    }

    @Override
    protected int getlayout() {
        return R.layout.list_studyitem;
    }

    @Override
    protected void initData() {
        presenter.getdata(page, cid);
    }

    @Override
    protected void initView() {
        cid = getArguments().getInt("cid");
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void setListss(ListBeanSS bean) {
        final List<ListBeanSS.DataBean.DatasBean> list = bean.getData().getDatas();
        ListPSSXlvAdapter adapter = new ListPSSXlvAdapter(getContext(), list);
        rlv.setAdapter(adapter);

        RlvHideUtils.HideWechtList(rlv, flbtn);
        RlvHideUtils.OnClicks(flbtn, rlv);
        smr.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                initData();
                adapter.notifyDataSetChanged();
                smr.finishRefresh();

            }
        });

        adapter.SetOnItemClickLisener(new ListPSSXlvAdapter.OnItemClickLisener() {
            @Override
            public void OnItemClickLisener(int position) {
                Intent intent = new Intent(getContext(), StudyWebActivity.class);
                intent.putExtra("title", list.get(position).getTitle());
                intent.putExtra("author", list.get(position).getAuthor());
                intent.putExtra("charname", list.get(position).getChapterName());
                intent.putExtra("time", list.get(position).getNiceDate());
                intent.putExtra("web", list.get(position).getLink());
                startActivity(intent);
            }
        });
    }

    @Override
    public void setFiallist(String msg) {

    }


}
