package com.example.dell.big_wanandroid.move.vpFragmentTeam;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.move.flow.MoveBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Dell on 2019/5/3.
 */

class MoveListXlvAdapter extends RecyclerView.Adapter {
    private final Context mcontext;
    private final List<MoveBean.DataBean.ArticlesBean> list;
    private OnItemClickLisener mlisener;

    public MoveListXlvAdapter(Context context, ArrayList<MoveBean.DataBean.ArticlesBean> list) {

        mcontext = context;
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mcontext).inflate(R.layout.move_list_item, null);
        return new ListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ListViewHolder holder1 = (ListViewHolder) holder;
        holder1.mTv.setText(list.get(position).getTitle());
        Random myRandom = new Random();
        int ranColor = 0xff000000 | myRandom.nextInt(0x00ffffff);
//        holder1.mTv.setBackgroundColor(ranColor);
        holder1.mTv.setTextColor(ranColor);

        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mlisener != null) {
                    mlisener.OnItemClickLisener(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickLisener {
        void OnItemClickLisener(int position);
    }

    public void SetOnItemClickLisener(OnItemClickLisener lisener) {
        mlisener = lisener;
    }

    class ListViewHolder extends  RecyclerView.ViewHolder {
        TextView mTv;


        public ListViewHolder(View itemView) {
            super(itemView);
            this.mTv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

}
