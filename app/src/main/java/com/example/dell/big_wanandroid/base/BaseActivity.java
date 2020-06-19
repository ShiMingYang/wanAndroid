package com.example.dell.big_wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Dell on 2019/4/28.
 */

public abstract class BaseActivity<p extends BasePresenter,v extends BaseView> extends AppCompatActivity implements BaseView{

    protected p presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutId());
        ButterKnife.bind(this);
        presenter = initPresenter();
        if (presenter!=null) {
            presenter.bind((v)this);
        }
        initView();
        initData();
        initListener();
    }

    protected void initListener() {

    }

    protected void initData() {

    }

    protected void initView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.OnDestroy();
    }

    protected abstract p initPresenter();

    protected abstract int getlayoutId();
}
