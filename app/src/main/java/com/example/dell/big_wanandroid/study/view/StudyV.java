package com.example.dell.big_wanandroid.study.view;

import com.example.dell.big_wanandroid.base.BaseView;
import com.example.dell.big_wanandroid.study.bean.StudyBean;

/**
 * Created by Dell on 2019/4/30.
 */

public interface StudyV extends BaseView{
    void setData(StudyBean bean);

    void seterror(String msg);
}
