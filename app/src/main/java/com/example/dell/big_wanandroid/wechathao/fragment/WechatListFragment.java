package com.example.dell.big_wanandroid.wechathao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.api.MyServer;
import com.example.dell.big_wanandroid.httpUtils.BaseObserver;
import com.example.dell.big_wanandroid.httpUtils.HttpUtils;
import com.example.dell.big_wanandroid.httpUtils.RxUtils;
import com.example.dell.big_wanandroid.utils.RlvHideUtils;
import com.example.dell.big_wanandroid.wechathao.adapter.WechatListXlvAdapter;
import com.example.dell.big_wanandroid.wechathao.bean.WechatListBean;
import com.example.dell.big_wanandroid.wechathao.ui.WechatWebActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * Created by Dell on 2019/5/2.
 */

public class WechatListFragment extends Fragment implements View.OnClickListener {

    Unbinder unbinder;
    private EditText mEdTv;
    private Handler handler = new Handler();
    /**
     * 搜索
     */
    private Button mBtn;
    private RecyclerView mRlv;
    private SmartRefreshLayout mSmr;
    private WechatListXlvAdapter adapter;
    private int cid;
    private String datasss;
    private ArrayList<WechatListBean.DataBean.DatasBean> arrayList;
    private int page = 1;
    private View view;
    private FloatingActionButton mFlbtn;
    private LinearLayout mLlll;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.crosslist, null);
        cid = getArguments().getInt("cid");
        initData();
        initView(inflate);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    private void serchData() {

        HttpUtils.getInstance().getApiserver(MyServer.homeUrl, MyServer.class)
                .serchData(cid, datasss)
                .compose(RxUtils.<WechatListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WechatListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WechatListBean wechatListBean) {
                        Log.d("TTT", "onNext: " + wechatListBean.getData().toString());
                        adapter.list.clear();
                        arrayList.addAll(wechatListBean.getData().getDatas());
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void initData() {
        HttpUtils.getInstance().getApiserver(MyServer.homeUrl, MyServer.class)
                .wehcarListData(cid)
                .compose(RxUtils.<WechatListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WechatListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WechatListBean wechatListBean) {
                        arrayList.addAll(wechatListBean.getData().getDatas());
                        adapter.notifyDataSetChanged();
                        final List<WechatListBean.DataBean.DatasBean> list = wechatListBean.getData().getDatas();
                        adapter.SetOnItemClickLisener(new WechatListXlvAdapter.OnItemClickLisener() {
                            @Override
                            public void OnItemClickLisener(int position) {
                                Intent intent = new Intent(getContext(), WechatWebActivity.class);
                                intent.putExtra("ttt", list.get(position).getTitle());
                                intent.putExtra("clink", list.get(position).getLink());
                                intent.putExtra("author", list.get(position).getAuthor());
                                intent.putExtra("charname", list.get(position).getChapterName());
                                intent.putExtra("time", list.get(position).getNiceDate());
                                startActivity(intent);
                            }
                        });


                    }
                });
    }

    private void initView(final View inflate) {
        mEdTv = (EditText) inflate.findViewById(R.id.ed_tv);
        mBtn = (Button) inflate.findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);
        mSmr = (SmartRefreshLayout) inflate.findViewById(R.id.smr);
        mFlbtn = (FloatingActionButton) inflate.findViewById(R.id.flbtn);
        mLlll = (LinearLayout) inflate.findViewById(R.id.llll);

        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList = new ArrayList<>();
        adapter = new WechatListXlvAdapter(getActivity(), arrayList);
        mRlv.setAdapter(adapter);


        mSmr.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initData();
                mSmr.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
                mSmr.finishRefresh();
            }
        });


        final RadioGroup rgbtn = getActivity().findViewById(R.id.rg);

        RlvHideUtils.TabHide(rgbtn, mRlv, mFlbtn);
        RlvHideUtils.OnTabClicks(mFlbtn, mRlv);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn:
                datasss = mEdTv.getText().toString().trim();
                Log.d("TTT", "onClick: " + datasss);
                if (datasss != null) {
                    serchData();
                }

                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.serch, menu);
        MenuItem item = menu.findItem(R.id.serchs);
        item.setVisible(true);
        item.setChecked(true);
        super.onCreateOptionsMenu(menu, inflater);
    }


}
