package com.example.dell.big_wanandroid.study.model;

import com.example.dell.big_wanandroid.api.MyServer;
import com.example.dell.big_wanandroid.base.BaseModel;
import com.example.dell.big_wanandroid.httpUtils.BaseObserver;
import com.example.dell.big_wanandroid.httpUtils.HttpUtils;
import com.example.dell.big_wanandroid.httpUtils.ResultCallBack;
import com.example.dell.big_wanandroid.httpUtils.RxUtils;
import com.example.dell.big_wanandroid.study.bean.StudyBean;

import io.reactivex.disposables.Disposable;

/**
 * Created by Dell on 2019/4/30.
 */

public class StudyM extends BaseModel {

    public void GetData(final ResultCallBack<StudyBean> resultCallBack) {
        HttpUtils.getInstance().getApiserver(MyServer.homeUrl,MyServer.class)
                .studyData()
                .compose(RxUtils.<StudyBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<StudyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(StudyBean studyBean) {
                        resultCallBack.onSuccess(studyBean);
                    }
                });

    }
}
