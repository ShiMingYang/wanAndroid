package com.example.dell.big_wanandroid.wechathao.model;

import com.example.dell.big_wanandroid.api.MyServer;
import com.example.dell.big_wanandroid.base.BaseModel;
import com.example.dell.big_wanandroid.httpUtils.BaseObserver;
import com.example.dell.big_wanandroid.httpUtils.HttpUtils;
import com.example.dell.big_wanandroid.httpUtils.ResultCallBack;
import com.example.dell.big_wanandroid.httpUtils.RxUtils;
import com.example.dell.big_wanandroid.wechathao.bean.WechatListBean;
import com.example.dell.big_wanandroid.wechathao.bean.WechatTabBean;

import io.reactivex.disposables.Disposable;

/**
 * Created by Dell on 2019/5/2.
 */

public class CrossM extends BaseModel {

    public void getdata(final ResultCallBack<WechatTabBean> resultCallBack) {
        HttpUtils.getInstance().getApiserver(MyServer.homeUrl,MyServer.class)
                .wehcarTabData()
                .compose(RxUtils.<WechatTabBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WechatTabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WechatTabBean wechatTabBean) {
                    resultCallBack.onSuccess(wechatTabBean);
                    }
                });
    }
}
