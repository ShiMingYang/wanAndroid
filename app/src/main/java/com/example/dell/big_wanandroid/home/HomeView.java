package com.example.dell.big_wanandroid.home;

import com.example.dell.big_wanandroid.base.BaseView;
import com.example.dell.big_wanandroid.home.bean.BannerBean;
import com.example.dell.big_wanandroid.home.bean.ListBean;

/**
 * Created by Dell on 2019/4/28.
 */

public interface HomeView extends BaseView {
    void setData(ListBean cc);

    void setBandata(BannerBean cc);
}
