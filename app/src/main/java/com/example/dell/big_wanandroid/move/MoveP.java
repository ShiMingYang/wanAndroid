package com.example.dell.big_wanandroid.move;

import com.example.dell.big_wanandroid.base.BasePresenter;
import com.example.dell.big_wanandroid.httpUtils.ResultCallBack;
import com.example.dell.big_wanandroid.move.flow.MoveM;
import com.example.dell.big_wanandroid.move.flow.MoveV;
import com.example.dell.big_wanandroid.move.flow.MoveBean;

/**
 * Created by Dell on 2019/5/7.
 */

public class MoveP extends BasePresenter<MoveV> {

    private MoveM moveM;

    @Override
    protected void initModel() {
        moveM = new MoveM();
    }

    public void getdata() {
        moveM.getdata(new ResultCallBack<MoveBean>() {
            @Override
            public void onSuccess(MoveBean bean) {
                mView.show(bean);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }


}
