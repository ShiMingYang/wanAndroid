package com.example.dell.big_wanandroid.move.flow;

import com.example.dell.big_wanandroid.api.MyServer;
import com.example.dell.big_wanandroid.httpUtils.BaseObserver;
import com.example.dell.big_wanandroid.httpUtils.HttpUtils;
import com.example.dell.big_wanandroid.httpUtils.ResultCallBack;
import com.example.dell.big_wanandroid.httpUtils.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by Dell on 2019/5/7.
 */

public class MoveM {

    public void getdata(final ResultCallBack<MoveBean> resultCallBack) {
        HttpUtils.getInstance().getApiserver(MyServer.homeUrl, MyServer.class)
                .moveData()
                .compose(RxUtils.<MoveBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MoveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoveBean moveBean) {
                     resultCallBack.onSuccess(moveBean);
                    }
                });
    }
}
