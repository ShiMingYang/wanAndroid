package com.example.dell.big_wanandroid.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.base.BaseFragment;
import com.example.dell.big_wanandroid.wechathao.adapter.WechatVPAdapter;
import com.example.dell.big_wanandroid.wechathao.bean.WechatTabBean;
import com.example.dell.big_wanandroid.wechathao.fragment.WechatListFragment;
import com.example.dell.big_wanandroid.wechathao.presenter.CorssP;
import com.example.dell.big_wanandroid.wechathao.view.CrossV;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Dell on 2019/4/26.
 */

public class CrossFragment extends BaseFragment<CorssP, CrossV> implements CrossV {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;


    @Override
    protected CorssP initpresenter() {
        return new CorssP();
    }

    @Override
    protected int getlayout() {
        return R.layout.cross;
    }

    @Override
    protected void initData() {
        presenter.getdata();
    }

    @Override
    protected void initView() {

    }

    /**
     * 响应数据并传递tab---id 到子fragment中
     * @param bean
     */
    @Override
    public void onSuccess(WechatTabBean bean) {
        List<WechatTabBean.DataBean> list = bean.getData();
        ArrayList<WechatListFragment> wechatListFragments = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            WechatListFragment listFragment = new WechatListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("cid", list.get(i).getId());
            listFragment.setArguments(bundle);
            wechatListFragments.add(listFragment);

        }

        WechatVPAdapter vpAdapter = new WechatVPAdapter(getChildFragmentManager(), wechatListFragments, list);
        vp.setAdapter(vpAdapter);
        tab.setupWithViewPager(vp);
    }
}
