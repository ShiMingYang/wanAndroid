package com.example.dell.big_wanandroid.study.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.study.bean.ListBeanSS;

import java.util.List;

/**
 * Created by Dell on 2019/5/1.
 */

public class ListPSSXlvAdapter extends RecyclerView.Adapter {
    private final Context mcontext;
    private final List<ListBeanSS.DataBean.DatasBean> mlist;
    private OnItemClickLisener mlisener;

    public ListPSSXlvAdapter(Context context, List<ListBeanSS.DataBean.DatasBean> list) {

        mcontext = context;
        this.mlist = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mcontext).inflate(R.layout.home_item, null);
        return new ListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ListViewHolder holder1 = (ListViewHolder) holder;
        ListBeanSS.DataBean.DatasBean bean = mlist.get(position);
        holder1.mAuthor.setText(mlist.get(position).getAuthor());
        holder1.mPlatform.setText(mlist.get(position).getSuperChapterName()+"/"+mlist.get(position).getChapterName());
        holder1.title.setText(mlist.get(position).getTitle());
        holder1.mTime.setText(mlist.get(position).getNiceDate());

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
                if (mlisener != null) {
                    mlisener.OnItemClickLisener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public interface OnItemClickLisener {
        void OnItemClickLisener(int position);
    }

    public void SetOnItemClickLisener(OnItemClickLisener lisener) {
        mlisener = lisener;
    }

    class ListViewHolder extends  RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mAuthor;
        TextView mPlatform;
        ImageView mXinImg;
        ImageView mShizhongImg;
        TextView title;
        TextView mTime;
        TextView mNews;
        TextView mTag;

        public ListViewHolder(View itemView) {
            super(itemView);
            this.mImg = (ImageView) itemView.findViewById(R.id.img);
            this.mAuthor = (TextView) itemView.findViewById(R.id.author);
            this.mNews = (TextView) itemView.findViewById(R.id.news);
            this.mPlatform = (TextView) itemView.findViewById(R.id.platform);
            this.mXinImg = (ImageView) itemView.findViewById(R.id.xin_img);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.mTag = (TextView) itemView.findViewById(R.id.tag);
            this.mShizhongImg = (ImageView) itemView.findViewById(R.id.shizhong_img);
            this.mTime = (TextView) itemView.findViewById(R.id.time);
        }

    }

}
