package com.example.dell.big_wanandroid.Project.model;

import com.example.dell.big_wanandroid.Project.bean.ListBeanP;
import com.example.dell.big_wanandroid.Project.bean.TabBeanP;
import com.example.dell.big_wanandroid.api.MyServer;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dell on 2019/4/29.
 */

public class ProjectListM {

    public interface CallBackListP{
        void getListData(ListBeanP listBeanP);
    }
    public void getListData(int page, int cid, final CallBackListP callBackListP) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MyServer.homeUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<ListBeanP> dataP = myServer.listDataP(page,cid);
        dataP.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListBeanP>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListBeanP listBeanP) {
                        callBackListP.getListData(listBeanP);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
