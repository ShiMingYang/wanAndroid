package com.example.dell.big_wanandroid.move.vpFragmentTeam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.api.MyServer;
import com.example.dell.big_wanandroid.httpUtils.BaseObserver;
import com.example.dell.big_wanandroid.httpUtils.HttpUtils;
import com.example.dell.big_wanandroid.httpUtils.RxUtils;
import com.example.dell.big_wanandroid.move.flow.MoveBean;
import com.example.dell.big_wanandroid.utils.FlowlayoutManger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Dell on 2019/5/3.
 */

public class MoveListFment extends Fragment {
    private static final String TAG = "MoveListFment";
    private View view;
    private int cid;
    private RecyclerView mRlv;
    private MoveListXlvAdapter adapter;
    private ArrayList<MoveBean.DataBean.ArticlesBean> list1;
    private List<MoveBean.DataBean.ArticlesBean> articles;
    private TextView mName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.movetlist, null);
        cid = getArguments().getInt("cid");
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        HttpUtils.getInstance().getApiserver(MyServer.homeUrl, MyServer.class)
                .moveData()
                .compose(RxUtils.<MoveBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MoveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoveBean moveBean) {
                        List<MoveBean.DataBean> data = moveBean.getData();

                        for (int i = 0; i < data.size(); i++) {
                            mName.setText(data.get(cid).getName());
                            articles = data.get(cid).getArticles();
                            Log.e(TAG, "onNext: " + mName.toString());
                        }
                        list1.addAll(articles);
                        adapter.notifyDataSetChanged();

                    }
                });
    }

    private void initView(View inflate) {
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);
        mName = (TextView) inflate.findViewById(R.id.name);
        mRlv.setLayoutManager(new FlowlayoutManger());
        list1 = new ArrayList<>();
        adapter = new MoveListXlvAdapter(getContext(), list1);

        adapter.SetOnItemClickLisener(new MoveListXlvAdapter.OnItemClickLisener() {
            @Override
            public void OnItemClickLisener(int position) {
                Intent intent = new Intent(getContext(), MoveWebActivity.class);
                intent.putExtra("link", list1.get(position).getLink());
                intent.putExtra("title", list1.get(position).getChapterName());
                startActivity(intent);
            }
        });
/*
      //返回头布局的内容  粘性头布局
        final NormalDecoration decoration = new NormalDecoration() {
            @Override
            public String getHeaderName(int i) {
                return list1.get(i).getChapterName();
            }
        };

        //自定义头布局,可不设置
        decoration.setOnDecorationHeadDraw(new NormalDecoration.OnDecorationHeadDraw() {
            @Override
            public View getHeaderView(final int i) {
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_header,null);
                TextView tv = inflate.findViewById(R.id.tv);
                tv.setText(list1.get(i).getChapterName());

                return inflate;
            }
        });

        mRlv.addItemDecoration(decoration);
        //头布局的点击事件
        decoration.setOnHeaderClickListener(new NormalDecoration.OnHeaderClickListener() {
            @Override
            public void headerClick(int i) {
                Toast.makeText(getActivity(), list1.get(i).getChapterName(), Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getContext(), FlowActivity.class));
//                startActivity(new Intent(getContext(), MaterialActivity.class));
            }
        });*/
        mRlv.setAdapter(adapter);

    }
}
