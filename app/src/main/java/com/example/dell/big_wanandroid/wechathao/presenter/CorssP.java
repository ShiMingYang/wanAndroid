package com.example.dell.big_wanandroid.wechathao.presenter;

import com.example.dell.big_wanandroid.base.BasePresenter;
import com.example.dell.big_wanandroid.httpUtils.ResultCallBack;
import com.example.dell.big_wanandroid.wechathao.bean.WechatListBean;
import com.example.dell.big_wanandroid.wechathao.bean.WechatTabBean;
import com.example.dell.big_wanandroid.wechathao.model.CrossM;
import com.example.dell.big_wanandroid.wechathao.view.CrossV;

/**
 * Created by Dell on 2019/5/2.
 */

public class CorssP extends BasePresenter<CrossV> {

    private CrossM crossM;

    @Override
    protected void initModel() {
        crossM = new CrossM();

    }

    public void getdata() {
        crossM.getdata(new ResultCallBack<WechatTabBean>() {
            @Override
            public void onSuccess(WechatTabBean bean) {
                mView.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
