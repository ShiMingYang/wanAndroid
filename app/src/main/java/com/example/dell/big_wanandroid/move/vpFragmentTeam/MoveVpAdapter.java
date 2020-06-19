package com.example.dell.big_wanandroid.move.vpFragmentTeam;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dell.big_wanandroid.move.flow.MoveBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2019/5/3.
 */

public class MoveVpAdapter  extends FragmentPagerAdapter{
    private final ArrayList<MoveListFment> moveListFments;
    private final List<MoveBean.DataBean> list1;

    public MoveVpAdapter(FragmentManager fm, ArrayList<MoveListFment> moveListFments, List<MoveBean.DataBean> list1) {
        super(fm);
        this.moveListFments = moveListFments;
        this.list1 = list1;
    }

    @Override
    public Fragment getItem(int position) {
        return moveListFments.get(position);
    }

    @Override
    public int getCount() {
        return moveListFments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list1.get(position).getName();
    }
}
