package com.example.dell.big_wanandroid.home;

import com.example.dell.big_wanandroid.api.MyServer;
import com.example.dell.big_wanandroid.base.BaseModel;
import com.example.dell.big_wanandroid.home.bean.BannerBean;
import com.example.dell.big_wanandroid.home.bean.ListBean;
import com.example.dell.big_wanandroid.home.callback.homeBack;
import com.example.dell.big_wanandroid.httpUtils.BaseObserver;
import com.example.dell.big_wanandroid.httpUtils.HttpUtils;
import com.example.dell.big_wanandroid.httpUtils.ResultCallBack;
import com.example.dell.big_wanandroid.httpUtils.RxUtils;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dell on 2019/4/28.
 */

public class HomeM extends BaseModel {


    public void getData(int page, final ResultCallBack<ListBean> homeBack) {

        MyServer myServer = HttpUtils.getInstance().getApiserver(MyServer.homeUrl, MyServer.class);
        Observable<ListBean> listData = myServer.listData(page);
         listData.compose(RxUtils.<ListBean>rxObserableSchedulerHelper())
                 .subscribe(new BaseObserver<ListBean>() {
                     @Override
                     public void onSubscribe(Disposable d) {
                      compositeDisposable.add(d);
                     }

                     @Override
                     public void onNext(ListBean listBean) {
                        homeBack.onSuccess(listBean);
                     }
                 });
//        Retrofit retrofit = new Retrofit.Builder().build();
      /*  OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url().get().build();
        Call call = client.newCall(request);
        try {
            ResponseBody body = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

      /*  Retrofit retrofit = new Retrofit.Builder().baseUrl(MyServer.homeUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<ListBean> data = myServer.listData(page);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListBean listBean) {
                        homeBack.onBanSunccess(listBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }

    public void getbanData(final homeBack<BannerBean> homeBack) {

        MyServer myServer1 = HttpUtils.getInstance()
                .getApiserver(MyServer.homeUrl, MyServer.class);
        Observable<BannerBean> banData = myServer1.banData();
        banData.compose(RxUtils.<BannerBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        homeBack.onBanSunccess(bannerBean);
                    }
                });
        /*Retrofit retrofit = new Retrofit.Builder().baseUrl(MyServer.homeUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<BannerBean> data = myServer.banData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        homeBack.onBanSunccess(bannerBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }
}
