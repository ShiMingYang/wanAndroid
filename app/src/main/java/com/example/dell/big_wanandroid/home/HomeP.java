package com.example.dell.big_wanandroid.home;

import com.example.dell.big_wanandroid.base.BasePresenter;
import com.example.dell.big_wanandroid.home.bean.BannerBean;
import com.example.dell.big_wanandroid.home.bean.ListBean;
import com.example.dell.big_wanandroid.home.callback.homeBack;
import com.example.dell.big_wanandroid.httpUtils.ResultCallBack;

import java.lang.ref.WeakReference;

/**
 * Created by Dell on 2019/4/28.
 */

public class HomeP extends BasePresenter<HomeView> {

    private HomeM homeM;


    public void getdata(int page) {
        homeM.getData(page,new ResultCallBack<ListBean>() {

            @Override
            public void onSuccess(ListBean bean) {
                mView.setData(bean);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    protected void initModel() {
        homeM = new HomeM();
        BaseM.add(homeM);
    }

    public void getbanData() {
        homeM.getbanData(new homeBack<BannerBean>() {
            @Override
            public void onBanSunccess(BannerBean cc) {
                mView.setBandata(cc);
            }
        });
    }
}
