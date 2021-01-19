package com.example.dell.big_wanandroid.Project.presenter;

import com.example.dell.big_wanandroid.Project.bean.TabBeanP;
import com.example.dell.big_wanandroid.Project.model.ProjectM;
import com.example.dell.big_wanandroid.Project.view.ProjectView;
import com.example.dell.big_wanandroid.base.BasePresenter;
import com.example.dell.big_wanandroid.httpUtils.ResultCallBack;

/**
 * Created by Dell on 2019/4/29.
 */

public class ProjectP extends BasePresenter<ProjectView> {

    private ProjectM projectM;

    @Override
    protected void initModel() {
        projectM = new ProjectM();
    }

    public void getTabData() {
       projectM.getTabData(new ResultCallBack<TabBeanP>() {
           @Override
           public void onSuccess(TabBeanP bean) {
               mView.SetTabData(bean);
           }

           @Override
           public void onFail(String msg) {
                mView.SetOnFail(msg);
           }
       });
    }
}
