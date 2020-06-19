package com.example.dell.big_wanandroid.move.flow;

import com.example.dell.big_wanandroid.R;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;

/**
 * Created by Dell on 2019/5/6.
 */

public class TabVpAdapter implements TabAdapter {
    private List<String> mlist;

    public TabVpAdapter(ArrayList<String> list) {

        mlist = list;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public ITabView.TabBadge getBadge(int position) {
        return null;
    }

    @Override
    public ITabView.TabIcon getIcon(int position) {
        return null;
    }

    @Override
    public ITabView.TabTitle getTitle(int position) {
        return new ITabView.TabTitle.Builder()
                .setContent(mlist.get(position))
                .setTextColor(R.color.white,R.color.blue)
                .build();
    }

    @Override
    public int getBackground(int position) {
        return 0;
    }
}
