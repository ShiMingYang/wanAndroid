package com.example.dell.big_wanandroid.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Dell on 2019/4/28.
 */

public class BaseModel {
 protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    public void onDestory() {
        compositeDisposable.clear();
    }
}
