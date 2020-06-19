package com.example.dell.big_wanandroid.study.presenter;

import android.util.Log;

import com.example.dell.big_wanandroid.base.BasePresenter;
import com.example.dell.big_wanandroid.httpUtils.ResultCallBack;
import com.example.dell.big_wanandroid.study.bean.ListBeanSS;
import com.example.dell.big_wanandroid.study.model.ListMS;
import com.example.dell.big_wanandroid.study.view.ListVS;

/**
 * Created by Dell on 2019/5/1.
 */

public class ListPS extends BasePresenter<ListVS> {
    private static final String TAG = "ListPS";
    private ListMS listMS;

    @Override
    protected void initModel() {
        listMS = new ListMS();
    }

    public void getdata(int page, int cid) {
        listMS.getData(page,cid, new ResultCallBack<ListBeanSS>() {
            @Override
            public void onSuccess(ListBeanSS bean) {
                Log.e(TAG, "onSuccess: "+bean.toString() );
                    mView.setListss(bean);
            }

            @Override
            public void onFail(String msg) {
                mView.setFiallist(msg);
            }
        });
    }
}
