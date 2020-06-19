package com.example.dell.big_wanandroid.move.flow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.move.vpFragmentTeam.MoveWebActivity;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NavigationAdapter extends RecyclerView.Adapter {
    public List<MoveBean.DataBean> list;
    private Context context;
    private setOnClickListener sc;

    public NavigationAdapter(List<MoveBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.navigationitem, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final int num = i;
        ViewHolder viewHolder1 = (ViewHolder) viewHolder;
        viewHolder1.tv.setText(list.get(i).getName());
        ArrayList<String> title = new ArrayList<>();
        for (int j = 0; j < list.get(i).getArticles().size(); j++) {
            String title1 = list.get(i).getArticles().get(j).getTitle();
            title.add(title1);
        }
        if (title != null) {
            viewHolder1.tabflowlayout.setAdapter(new TagAdapter<String>(title) {
                @Override
                public View getView(com.zhy.view.flowlayout.FlowLayout parent, int position, String string) {
                    /*int[] array = {R.color.Amber, R.color.arrow_color, R.color.colorPrimary
                            , R.color.colorPrimaryDark, R.color.Blue, R.color.color_title_bg, R.color.Green
                            , R.color.Deep_Orange, R.color.Lime, R.color.Teal, R.color.Indigo, R.color.Pink
                            , R.color.Yellow, R.color.Amber, R.color.Purple, R.color.Light_Green
                            , R.color.Light_Blue};

                    int random = (int) (Math.random() * (array.length - 1));*/
                    View inflate = LayoutInflater.from(context).inflate(R.layout.tag_textview, parent, false);
                    TextView tv = inflate.findViewById(R.id.tag_textview);
                    tv.setText(string);
//                    tv.setTextColor(context.getResources().getColor(array[random]));
                    Random myRandom = new Random();
                    int ranColor = 0xff000000 | myRandom.nextInt(0x00ffffff);
                    tv.setTextColor(ranColor);
                    return inflate;
                }


            });
        }
        viewHolder1.tabflowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, com.zhy.view.flowlayout.FlowLayout parent) {
                String link = list.get(num).getArticles().get(position).getLink();
                String name = list.get(position).getName();
                Intent intent = new Intent(context, MoveWebActivity.class);
                intent.putExtra("link", link);
                intent.putExtra("title", name);
                intent.putExtra("author", list.get(position).getArticles().get(position).getAuthor());
                intent.putExtra("charname", list.get(position).getArticles().get(position).getChapterName());
                intent.putExtra("time", list.get(position).getArticles().get(position).getNiceDate());
                context.startActivity(intent);
                return false;
            }

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv;
        public TagFlowLayout tabflowlayout;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv = (TextView) rootView.findViewById(R.id.tv);
            this.tabflowlayout = (TagFlowLayout) rootView.findViewById(R.id.tabflowlayout);
        }
    }

    interface setOnClickListener {
        void setClickListener(View v, int porition);
    }

    public void setList(setOnClickListener sc) {
        this.sc = sc;
    }
}

