package com.example.dell.big_wanandroid.GreenDaos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.big_wanandroid.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Dell on 2019/4/28.
 */

public class CollectXlvAdapter extends RecyclerView.Adapter implements TouchCallback{
    private final Context mcontext;
    public final ArrayList<Person> list;
    private OnItemClickLisener mlisener;

    public CollectXlvAdapter(Context context, ArrayList<Person> list) {

        mcontext = context;
        this.list = list;
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
        Person person = list.get(position);
        holder1.mAuthor.setText(list.get(position).getAuthor());
            holder1.mPlatform.setText(list.get(position).getChapterName());
            holder1.title.setText(list.get(position).getTitle());
            holder1.mTime.setText(list.get(position).getNiceDate());

            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlisener!=null) {
                        mlisener.OnItemClickLisener(person,position);
                    }
                }
            });



    }

     public interface OnItemClickLisener{
             void OnItemClickLisener(Person bean,int position);
          }

    public void SetOnItemClickLisener(OnItemClickLisener lisener){
              mlisener = lisener;
          }

    @Override
    public int getItemCount() {

            return list.size();

    }

    @Override
    public void onItemMove(int fromposition, int toposition) {
        Collections.swap(list,fromposition,toposition);
        notifyItemMoved(fromposition,toposition);
    }

    @Override
    public void onItemDelete(int position) {
        list.remove(position);
        //刷新条目删除后的数据
        // 局部刷新,索引混乱,越界
        notifyItemRemoved(position);
    }

    class ListViewHolder extends  RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mAuthor;
        TextView mPlatform;
        ImageView mXinImg;
        ImageView mredxin_img;
        ImageView mShizhongImg;
        TextView title;
        TextView mTime;
        TextView mNews;
        public ListViewHolder(View itemView) {
            super(itemView);
            this.mImg = (ImageView) itemView.findViewById(R.id.img);
            this.mAuthor = (TextView) itemView.findViewById(R.id.author);
            this.mNews = (TextView) itemView.findViewById(R.id.news);
            this.mPlatform = (TextView) itemView.findViewById(R.id.platform);
            this.mXinImg = (ImageView) itemView.findViewById(R.id.xin_img);
//            this.mredxin_img = (ImageView) itemView.findViewById(R.id.redxin_img);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.mShizhongImg = (ImageView) itemView.findViewById(R.id.shizhong_img);
            this.mTime = (TextView) itemView.findViewById(R.id.time);
        }

    }

    class BanViewHolder extends  RecyclerView.ViewHolder {
        Banner mBanner;

        public BanViewHolder(View itemView) {
            super(itemView);

            this.mBanner = (Banner) itemView.findViewById(R.id.sbanner);
        }
    }


}
