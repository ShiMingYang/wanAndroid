package com.example.dell.big_wanandroid.MainUi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.dell.big_wanandroid.GreenDaos.CollectXlvAdapter;
import com.example.dell.big_wanandroid.GreenDaos.Person;
import com.example.dell.big_wanandroid.GreenDaos.SimpleTouchHelperCallBack;
import com.example.dell.big_wanandroid.GreenDaos.TouchCallback;
import com.example.dell.big_wanandroid.GreenDaos.UtilsDao;
import com.example.dell.big_wanandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectActivity extends AppCompatActivity {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    private ArrayList<Person> list = new ArrayList<>();;
    private CollectXlvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
          rlv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CollectXlvAdapter(this, list);
        rlv.setAdapter(adapter);
        //拖拽移动和侧滑删除的功能
        SimpleTouchHelperCallBack simpleTouchHelperCallBack
                = new SimpleTouchHelperCallBack( adapter);
        //设置左边滑动删除
        simpleTouchHelperCallBack.setSwipeEnable(true);

                adapter.SetOnItemClickLisener(new CollectXlvAdapter.OnItemClickLisener() {
                    @Override
                    public void OnItemClickLisener(Person bean, int position) {
                                UtilsDao.getUtilsDao().deletes(bean);
                                adapter.notifyItemChanged(position);
                                adapter.notifyDataSetChanged();
                    }
                });

        ItemTouchHelper helper = new ItemTouchHelper(simpleTouchHelperCallBack);

        helper.attachToRecyclerView(rlv);
    }

    private void initData() {
        List<Person> query = UtilsDao.getUtilsDao().query();
        if (query.size()>0) {
            list.addAll(query);
            adapter.notifyDataSetChanged();
        }else {
            list.clear();
        }
    }


}
