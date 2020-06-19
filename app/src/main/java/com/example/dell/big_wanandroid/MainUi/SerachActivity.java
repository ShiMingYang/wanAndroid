package com.example.dell.big_wanandroid.MainUi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.utils.FlowLayout;

import java.util.ArrayList;
import java.util.Random;

public class SerachActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBacks;
    /**
     * 搜索历史
     */
    private TextView mLishi;
    /**
     * 清空
     */
    private TextView mClear;
    private RecyclerView mRlv;
    private FlowLayout mFlow;
    /**
     * 发现更多干货
     */
    private EditText mSerch;
    private ArrayList<String> list;
    private ArrayList<String> mList;
    private SerXlvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach);
        initView();
    }

    private void initView() {
        mBacks = (ImageView) findViewById(R.id.backs);
        mLishi = (TextView) findViewById(R.id.lishi);
        mClear = (TextView) findViewById(R.id.clear);
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mFlow = (FlowLayout) findViewById(R.id.flow);
        mSerch = (EditText) findViewById(R.id.serch);
        mBacks.setOnClickListener(this);
        mClear.setOnClickListener(this);

        list = new ArrayList<>();
        list.add("面试");
        list.add("Studio3");
        list.add("动画");
        list.add("自定义View");
        list.add("性能优化速度");
        list.add("gradle");
        list.add("Camera 相机");
        list.add("代码混淆安全");
        list.add("逆向加固");

        mList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            //获取视图,视图可以自定义,可以添加自己想要的效果
            TextView label = (TextView) View.inflate(SerachActivity.this, R.layout.item_label, null);
            String integer = list.get(i);
            label.setText(integer);
            //绑定数据
            Random myRandom = new Random();
            int ranColor = 0xff000000 | myRandom.nextInt(0x00ffffff);
            label.setBackgroundColor(ranColor);

            label.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSerch.setText(integer);
                    mrlvData(mList,integer);
                }


            });

            //加到容器中,parent是FlowLayout
            mFlow.addView(label);
        }




    }

    private void mrlvData(ArrayList<String> mList, String integer) {
        mList.add(integer);
        mRlv.setLayoutManager(new LinearLayoutManager(SerachActivity.this));
        adapter = new SerXlvAdapter(this,mList);
        mRlv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.backs:
                finish();
                break;
            case R.id.clear:
                if (mList==null) {
                    mClear.setText("清空");
                  return;
                }else {
                    adapter.list.clear();
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
