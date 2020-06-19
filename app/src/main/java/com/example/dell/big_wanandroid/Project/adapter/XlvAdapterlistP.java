package com.example.dell.big_wanandroid.Project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.big_wanandroid.Project.bean.ListBeanP;
import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.utils.SpUtil;

import java.util.List;

/**
 * Created by Dell on 2019/4/29.
 */

public class XlvAdapterlistP extends RecyclerView.Adapter {
    private final Context mcontext;
    private final List<ListBeanP.DataBean.DatasBean> list;
    private OnItemClickLisener mlisener;

    public XlvAdapterlistP(Context context, List<ListBeanP.DataBean.DatasBean> list) {

        mcontext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mcontext).inflate(R.layout.projectlist_item, null);
        return new ListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ListViewHolder holder1 = (ListViewHolder) holder;
        holder1.mProjectAuthor.setText(list.get(position).getAuthor());
        holder1.mProjectSynopsis.setText(list.get(position).getTitle());
        holder1.mProjectTime.setText(list.get(position).getNiceDate());
        holder1.mProjectTitle.setText(list.get(position).getDesc());
        boolean img = (boolean) SpUtil.getParam("img", false);
        if (img) {
            Glide.with(mcontext).load(R.drawable.icon_wan).into(holder1.mProjectImg);
        }else {
            Glide.with(mcontext).load(list.get(position).getEnvelopePic()).into(holder1.mProjectImg);
        }


        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlisener!=null)
                    mlisener.OnItemClickLisener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

     public interface OnItemClickLisener{
             void OnItemClickLisener(int position);
          }

          public void SetOnItemClickLisener(OnItemClickLisener lisener){
              mlisener = lisener;
          }

    class ListViewHolder extends   RecyclerView.ViewHolder {
        ImageView mProjectImg;
        ImageView mProjectWanImg;
        TextView mProjectSynopsis;
        RelativeLayout mOne;
        TextView mProjectTitle;
        RelativeLayout mTwo;
        TextView mProjectTime;
        TextView mProjectAuthor;
        public ListViewHolder(View itemView) {
            super(itemView);
            this.mProjectImg = (ImageView) itemView.findViewById(R.id.project_img);
            this.mProjectWanImg = (ImageView) itemView.findViewById(R.id.project_wan_img);
            this.mProjectSynopsis = (TextView) itemView.findViewById(R.id.project_synopsis);
            this.mOne = (RelativeLayout) itemView.findViewById(R.id.one);
            this.mProjectTitle = (TextView) itemView.findViewById(R.id.project_title);
            this.mTwo = (RelativeLayout) itemView.findViewById(R.id.two);
            this.mProjectTime = (TextView) itemView.findViewById(R.id.project_time);
            this.mProjectAuthor = (TextView) itemView.findViewById(R.id.project_author);
        }
    }

}
