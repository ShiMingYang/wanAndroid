package com.example.dell.big_wanandroid.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.big_wanandroid.HomeActivity;
import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.utils.Constants;
import com.example.dell.big_wanandroid.utils.SpUtil;
import com.example.dell.big_wanandroid.utils.UIModeUtil;

/**
 * Created by Dell on 2019/4/28.
 */

public class SettingFragment extends Fragment {
    private View view;
    private CheckBox mCbCache;
    private CheckBox mCbImg;
    private CheckBox mCbNight;
    /**
     * 521.00KB
     */
    private TextView mTvClear;
    private LinearLayout mLvClear;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.setting, null);
        initView(inflate);
        return inflate;
    }


    private void initView(View inflate) {
        mCbCache = (CheckBox) inflate.findViewById(R.id.cb_cache);
        mCbImg = (CheckBox) inflate.findViewById(R.id.cb_img);
        mCbNight = (CheckBox) inflate.findViewById(R.id.cb_night);
        mTvClear = (TextView) inflate.findViewById(R.id.tv_clear);
        mLvClear = (LinearLayout) inflate.findViewById(R.id.lv_clear);

        mTvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvClear.setText("0.0kb");
            }
        });
        int currentNightMode = getActivity().getResources()
                .getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
            //判断当前是日间模式
            mCbNight.setChecked(false);
        } else {
            mCbNight.setChecked(true);
        }


        noimg();

        mCbNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //切换模式
                //切换日夜间模式的时候Activity会重新创建
                //对应的这个碎片也会重建,重建的时候SwitchCompat会设置默认值
                //设置默认值的时候这个回调会被调用
                //if (用户点击的情况下){
                if (buttonView.isPressed()) {
                    //切换并保存模式
                    UIModeUtil.changeModeUI((HomeActivity) getActivity());
                    //保存当前碎片的type
                    SpUtil.setParam(Constants.DAY_NIGHT_FRAGMENT_POS, HomeActivity.MAIN);
                }
            }
        });
    }

    public void noimg() {
        mCbImg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    SpUtil.setParam("img",isChecked);
//                }else {
//                    SpUtil.setParam("img",isChecked);
//                }
                SpUtil.setParam("img", isChecked);
            }
        });
    }

}
