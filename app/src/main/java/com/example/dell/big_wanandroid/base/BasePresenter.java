package com.example.dell.big_wanandroid.base;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
/**
 * Created by Dell on 2019/4/28.
 */

public abstract class BasePresenter<v extends BaseView> {


    protected v mView;
    protected ArrayList<BaseModel> BaseM = new ArrayList<>();


    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void bind(v baseview) {
        WeakReference<v> WeakRefer = new WeakReference<v>(baseview);
        v v = WeakRefer.get();

        this.mView = v;
    }

    public void OnDestroy() {
        mView = null;
        for (BaseModel baseModel : BaseM) {
            baseModel.onDestory();
        }
        BaseM.clear();
    }

}
