package com.example.dell.big_wanandroid.MainUi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.big_wanandroid.R;

import java.util.ArrayList;

/**
 * Created by Dell on 2019/5/13.
 */

class SerXlvAdapter extends RecyclerView.Adapter{


    private final View.OnClickListener serachActivity;
    public final ArrayList<String> list;

    public SerXlvAdapter(View.OnClickListener serachActivity, ArrayList<String> list) {

        this.serachActivity = serachActivity;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.serch_item, null);
        return new ListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListViewHolder holder1 = (ListViewHolder) holder;
        holder1.mTv1.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ListViewHolder extends  RecyclerView.ViewHolder {
            ImageView mPic;
            TextView mTv1;
            public ListViewHolder(View itemView) {
                super(itemView);
                this.mPic = (ImageView) itemView.findViewById(R.id.pic);
                this.mTv1 = (TextView) itemView.findViewById(R.id.tv1);
            }
        }
}
