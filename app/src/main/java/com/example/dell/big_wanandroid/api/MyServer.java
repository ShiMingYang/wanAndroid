package com.example.dell.big_wanandroid.api;

import com.example.dell.big_wanandroid.Project.bean.ListBeanP;
import com.example.dell.big_wanandroid.Project.bean.TabBeanP;
import com.example.dell.big_wanandroid.bean.FlowBean;
import com.example.dell.big_wanandroid.bean.RegisterBean;
import com.example.dell.big_wanandroid.home.bean.BannerBean;
import com.example.dell.big_wanandroid.home.bean.ListBean;
import com.example.dell.big_wanandroid.move.flow.MoveBean;
import com.example.dell.big_wanandroid.study.bean.ListBeanSS;
import com.example.dell.big_wanandroid.study.bean.StudyBean;
import com.example.dell.big_wanandroid.wechathao.bean.WechatListBean;
import com.example.dell.big_wanandroid.wechathao.bean.WechatTabBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Dell on 2019/4/28.
 */

public interface MyServer {

    public String regisUrl="http://yun918.cn/study/public/index.php/";

    @POST("register")
    @FormUrlEncoded
    Observable<RegisterBean> regisData(@FieldMap HashMap<String,Object> map);

    public String homeUrl="https://www.wanandroid.com/";

    @GET("banner/json")
    Observable<BannerBean> banData();

    @GET("article/list/{page}/json")
    Observable<ListBean> listData(@Path("page") int page);

    @GET("project/tree/json")
    Observable<TabBeanP> tabDataP();


    @GET("project/list/{page}/json?")
    Observable<ListBeanP> listDataP(@Path("page") int page, @Query("cid") int cid);

    //知识体系
//    https://www.wanandroid.com/tree/json

    @GET("tree/json")
    Observable<StudyBean> studyData();
    //知识体系内部文章列表
    //https://www.wanandroid.com/article/list/0/json?cid=172
    @GET("article/list/{page}/json?")
    Observable<ListBeanSS> studyListssData(@Path("page") int page, @Query("cid") int cid);

    //公众号Tab
//    https://wanandroid.com/wxarticle/chapters/json
    @GET("wxarticle/chapters/json")
    Observable<WechatTabBean> wehcarTabData();

    //公众号数据list
    //https://wanandroid.com/wxarticle/list/408/1/json

    @GET("wxarticle/list/{cid}/1/json")
    Observable<WechatListBean> wehcarListData(@Path("cid") int id);

    //https://wanandroid.com/wxarticle/list/405/1/json?k=Java

    @GET("wxarticle/list/{cid}/1/json?")
    Observable<WechatListBean> serchData(@Path("cid") int id,@Query("k") String k);

    //导航界面
    //https://www.wanandroid.com/navi/json


    @GET("navi/json")
    Observable<MoveBean> moveData();

    //流式布局接口
    //https://www.wanandroid.com/friend/json

    @GET("friend/json")
    Observable<FlowBean> flowdata();



}
