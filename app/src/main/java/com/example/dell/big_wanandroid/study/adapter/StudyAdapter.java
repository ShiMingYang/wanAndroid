package com.example.dell.big_wanandroid.study.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.study.bean.StudyBean;

import java.util.List;
import java.util.Random;

/**
 * Created by Dell on 2019/4/30.
 */

public class StudyAdapter extends RecyclerView.Adapter {
    private final Context mcontext;
    private final List<StudyBean.DataBean> list;
    private OnItemClickLisener mlisener;
    private String name;

    public StudyAdapter(Context context, List<StudyBean.DataBean> list) {

        mcontext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mcontext).inflate(R.layout.studys, null);
        return new ListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ListViewHolder holder1 = (ListViewHolder) holder;
        holder1.mTitle.setText(list.get(position).getName());
        Random random = new Random();
        int ranColor = 0xff000000 | random.nextInt(0x00ffffff);
        holder1.mTitle.setTextColor(ranColor);
        List<StudyBean.DataBean.ChildrenBean> children = list.get(position).getChildren();
        //字符串拼接
//        name = "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < children.size(); i++) {
            String name1 = children.get(i).getName();
//            name +=name1;
            buffer.append("  "+name1);
        }
        holder1.mTv2.setText(buffer);

        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mlisener!=null) {
                    mlisener.OnItemClickLisener(list.get(position),position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

     public interface OnItemClickLisener{
             void OnItemClickLisener(StudyBean.DataBean bean, int position);
          }

          public void SetOnItemClickLisener(OnItemClickLisener lisener){
              mlisener = lisener;
          }

    class ListViewHolder extends  RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mTv2;
        public ListViewHolder(View itemView) {
            super(itemView);
            this.mTitle = (TextView) itemView.findViewById(R.id.title);
            this.mTv2 = (TextView) itemView.findViewById(R.id.tv2);
        }
    }


}
