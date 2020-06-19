package com.example.dell.big_wanandroid.Project.presenter;

import com.example.dell.big_wanandroid.Project.bean.ListBeanP;
import com.example.dell.big_wanandroid.Project.bean.TabBeanP;
import com.example.dell.big_wanandroid.Project.model.ProjectListM;
import com.example.dell.big_wanandroid.Project.model.ProjectM;
import com.example.dell.big_wanandroid.Project.view.ProjectListView;
import com.example.dell.big_wanandroid.Project.view.ProjectView;
import com.example.dell.big_wanandroid.base.BasePresenter;

/**
 * Created by Dell on 2019/4/29.
 */

public class ProjectListP extends BasePresenter<ProjectListView> {


    private ProjectListM listM;

    @Override
    protected void initModel() {
        listM = new ProjectListM();
    }

    public void getData(int page, int cid) {
        listM.getListData(page,cid, new ProjectListM.CallBackListP() {
            @Override
            public void getListData(ListBeanP listBeanP) {
                mView.SetListData(listBeanP);
            }
        });
    }
}
