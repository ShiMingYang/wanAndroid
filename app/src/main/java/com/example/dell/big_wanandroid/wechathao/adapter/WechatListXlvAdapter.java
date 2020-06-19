package com.example.dell.big_wanandroid.wechathao.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.wechathao.bean.WechatListBean;

import java.util.List;

/**
 * Created by Dell on 2019/5/2.
 */

public class WechatListXlvAdapter extends RecyclerView.Adapter {
    private final FragmentActivity mactivity;
    public final List<WechatListBean.DataBean.DatasBean> list;
    private OnItemClickLisener mlisener;

    public WechatListXlvAdapter(FragmentActivity activity, List<WechatListBean.DataBean.DatasBean> list) {

        mactivity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mactivity).inflate(R.layout.home_item, null);
        return new ListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ListViewHolder holder1 = (ListViewHolder) holder;
        holder1.mAuthor.setText(list.get(position).getAuthor());
        holder1.mPlatform.setText(list.get(position).getSuperChapterName()+"/"+list.get(position).getChapterName());
        holder1.mTitle.setText(list.get(position).getTitle());
        holder1.mTime.setText(list.get(position).getNiceDate());

        WechatListBean.DataBean.DatasBean bean = list.get(position);
        if (bean.isFresh()) {
            holder1.mNews.setVisibility(View.VISIBLE);
        }else {
            holder1.mNews.setVisibility(View.GONE);
        }
        if (bean.getTags().size()>0) {
            holder1.mTag.setVisibility(View.VISIBLE);
        }else {
            holder1.mTag.setVisibility(View.GONE);
        }
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mlisener!=null) {
                    mlisener.OnItemClickLisener(position);
                }
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

    class ListViewHolder extends  RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mAuthor;
        TextView mPlatform;
        TextView mTitle;
        ImageView mXinImg;
        ImageView mShizhongImg;
        TextView mTime;
        TextView mNews;
        TextView mTag;
        public ListViewHolder(View itemView) {
            super(itemView);
            this.mImg = (ImageView) itemView.findViewById(R.id.img);
            this.mAuthor = (TextView) itemView.findViewById(R.id.author);
            this.mPlatform = (TextView) itemView.findViewById(R.id.platform);
            this.mTitle = (TextView) itemView.findViewById(R.id.title);
            this.mXinImg = (ImageView) itemView.findViewById(R.id.xin_img);
            this.mShizhongImg = (ImageView) itemView.findViewById(R.id.shizhong_img);
            this.mTime = (TextView) itemView.findViewById(R.id.time);
            this.mTag = (TextView) itemView.findViewById(R.id.tag);
            this.mNews = (TextView) itemView.findViewById(R.id.news);
        }
    }


}
