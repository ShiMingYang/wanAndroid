package com.example.dell.big_wanandroid.study.model;

import com.example.dell.big_wanandroid.api.MyServer;
import com.example.dell.big_wanandroid.base.BaseModel;
import com.example.dell.big_wanandroid.httpUtils.BaseObserver;
import com.example.dell.big_wanandroid.httpUtils.HttpUtils;
import com.example.dell.big_wanandroid.httpUtils.ResultCallBack;
import com.example.dell.big_wanandroid.httpUtils.RxUtils;
import com.example.dell.big_wanandroid.study.bean.ListBeanSS;
import com.example.dell.big_wanandroid.study.bean.StudyBean;

import io.reactivex.disposables.Disposable;

/**
 * Created by Dell on 2019/5/1.
 */

public class ListMS extends BaseModel {


    public void getData(int page, int cid, final ResultCallBack<ListBeanSS> resultCallBack) {
        HttpUtils.getInstance().getApiserver(MyServer.homeUrl,MyServer.class)
                .studyListssData(page,cid)
                .compose(RxUtils.<ListBeanSS>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ListBeanSS>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ListBeanSS studyBean) {
                        resultCallBack.onSuccess(studyBean);
                    }
                });
    }
}
