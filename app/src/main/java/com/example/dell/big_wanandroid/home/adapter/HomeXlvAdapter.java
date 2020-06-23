package com.example.dell.big_wanandroid.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.big_wanandroid.GreenDaos.Person;
import com.example.dell.big_wanandroid.GreenDaos.UtilsDao;
import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.home.bean.BannerBean;
import com.example.dell.big_wanandroid.home.bean.ListBean;
import com.example.dell.big_wanandroid.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Dell on 2019/4/28.
 */

public class HomeXlvAdapter extends RecyclerView.Adapter {
    private final Context mcontext;
    public final ArrayList<ListBean.DataBean.DatasBean> list;
    public final ArrayList<BannerBean.DataBean> banbeans;
    private OnItemClickLisener mlisener;
    private OnItemBanClickLisener mbanClickLisener;
    private ListBean.DataBean.DatasBean bean;
    private Person person;

    public HomeXlvAdapter(Context context, ArrayList<ListBean.DataBean.DatasBean> list, ArrayList<BannerBean.DataBean> banbeans) {

        mcontext = context;
        this.list = list;
        this.banbeans = banbeans;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {//增加头布局banner
            return 0;
        } else {
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(mcontext).inflate(R.layout.banner_item, null);
            return new BanViewHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(mcontext).inflate(R.layout.home_item, null);
            return new ListViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        if (viewType==0) {
            final BanViewHolder holder1 = (BanViewHolder) holder;
            holder1.mBanner.setImages(banbeans).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    BannerBean.DataBean path1 = (BannerBean.DataBean) path;
                    Glide.with(mcontext).load(path1.getImagePath()).into(imageView);
                }
            }).start();

            holder1.mBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    if(mbanClickLisener!=null){
                        mbanClickLisener.OnItemBanClickLisener(position);
                    }
                }
            });
        }else {
             int newposition=position;
            if (banbeans.size()>0) {
                newposition=position-1;
            }
            ListViewHolder holder1 = (ListViewHolder) holder;
            bean = list.get(newposition);
            holder1.mAuthor.setText(list.get(newposition).getAuthor());
            holder1.mPlatform.setText(list.get(newposition).getSuperChapterName()+"/"+list.get(newposition).getChapterName());
            holder1.title.setText(list.get(newposition).getTitle());
            holder1.mTime.setText(list.get(newposition).getNiceDate());

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

            //此处是为了 界面点击收藏变换图标同时添加数据库
            person = new Person();
            person.setId(null);
            person.setAuthor(bean.getAuthor());
            person.setChapterName(bean.getChapterName());
            person.setNiceDate(bean.getNiceDate());
            person.setTitle(bean.getTitle());
            Person queryData = UtilsDao.getUtilsDao().queryData(person);
            if (queryData==null) {
                holder1.mXinImg.setImageResource(R.mipmap.follow_unselected);
//
            }else {
                holder1.mXinImg.setImageResource(R.mipmap.follow);
            }

            holder1.mXinImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    person = new Person();
                    person.setId(null);
                    person.setAuthor(bean.getAuthor());
                    person.setChapterName(bean.getChapterName());
                    person.setNiceDate(bean.getNiceDate());
                    person.setTitle(bean.getTitle());

                    Person queryData = UtilsDao.getUtilsDao().queryData(person);
                    if (queryData==null) {
                        UtilsDao.getUtilsDao().inser(person);
                        holder1.mXinImg.setImageResource(R.mipmap.follow);
                        ToastUtil.showShort("收藏成功");
//
                    }else {
                        UtilsDao.getUtilsDao().deletes(queryData);
                        holder1.mXinImg.setImageResource(R.mipmap.follow_unselected);
                        ToastUtil.showShort("取消收藏");
                    }
                }
            });

            final int finalNewposition = newposition;
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlisener!=null) {
                        mlisener.OnItemClickLisener(finalNewposition);
                    }
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        if (banbeans.size() > 0) {
            return list.size() + 1;
        } else {
            return list.size();
        }
    }


        //接口回调 item的点击事件
     public interface OnItemClickLisener{
             void OnItemClickLisener(int position);
          }

          public void SetOnItemClickLisener(OnItemClickLisener lisener){
              mlisener = lisener;
          }

          public interface OnItemBanClickLisener{
             void OnItemBanClickLisener(int position);
          }

          public void SetOnItemBanClickLisener(OnItemBanClickLisener banClickLisener){

              this.mbanClickLisener = banClickLisener;
          }

    public void setlistData(ListBean cc) {//拿到响应的数据进行设置到界面
        list.addAll(cc.getData().getDatas());
        notifyDataSetChanged();
    }

    public void setbanData(BannerBean cc) {
        banbeans.addAll(cc.getData());
        notifyDataSetChanged();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mAuthor;
        TextView mPlatform;
        ImageView mXinImg;
//        ImageView mredxin_img;
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
            this.mTag = (TextView) itemView.findViewById(R.id.tag);
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
