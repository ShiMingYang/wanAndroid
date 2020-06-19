package com.example.dell.big_wanandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Dell on 2019/4/28.
 */

public abstract class BaseFragment<p extends BasePresenter,v extends BaseView> extends Fragment implements BaseView{

    private Unbinder unbinder;
    protected p presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getlayout(), null);
        unbinder = ButterKnife.bind(this, inflate);
        presenter = initpresenter();
        if (presenter!=null) {
            presenter.bind(this);
        }
        initView();
        initData();
        initListener();
        return inflate;
    }


    protected void initView() {

    }

    protected void initData() {

    }

    protected void initListener() {

    }

    protected abstract p initpresenter();

    protected abstract int getlayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
