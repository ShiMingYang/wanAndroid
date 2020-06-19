package com.example.dell.big_wanandroid.study.presenter;

import com.example.dell.big_wanandroid.base.BasePresenter;
import com.example.dell.big_wanandroid.httpUtils.ResultCallBack;
import com.example.dell.big_wanandroid.study.bean.StudyBean;
import com.example.dell.big_wanandroid.study.model.StudyM;
import com.example.dell.big_wanandroid.study.view.StudyV;

/**
 * Created by Dell on 2019/4/30.
 */

public class Studyp extends BasePresenter<StudyV> {

    private StudyM studyM;

    @Override
    protected void initModel() {
        studyM = new StudyM();
    }

    public void getdata() {
        studyM.GetData(new ResultCallBack<StudyBean>() {
            @Override
            public void onSuccess(StudyBean bean) {
                mView.setData(bean);
            }

            @Override
            public void onFail(String msg) {
                mView.seterror(msg);
            }
        });
    }


}
