package com.example.dell.big_wanandroid.MainUi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.api.MyServer;
import com.example.dell.big_wanandroid.bean.FlowBean;
import com.example.dell.big_wanandroid.utils.FlowLayout;
import com.example.dell.big_wanandroid.utils.ToastUtil;

import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetActivity extends AppCompatActivity  {

    private static final String TAG = "NetActivity";
    private FlowLayout mFlow;
    private ImageView mIv;
    private Toolbar mToolbar;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        initData();
        initView();
    }

    private void initData() {

        Retrofit retrofit = new Retrofit.Builder()

                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MyServer.homeUrl)
                .build();

        MyServer server = retrofit.create(MyServer.class);
        final Observable<FlowBean> data = server.flowdata();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FlowBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FlowBean flowBean) {
                        if (flowBean != null) {
                            List<FlowBean.DataBean> data1 = flowBean.getData();
                            for (int i = 0; i < data1.size(); i++) {
                                //获取视图,视图可以自定义,可以添加自己想要的效果
                                TextView label = (TextView) View.inflate(MyApp.getsMyApp(), R.layout.item_label, null);
                                //获取数据
                                final String title = data1.get(i).getName();
                                final String link = data1.get(i).getLink();

                                //绑定数据
                                label.setText(title);
                                Random myRandom = new Random();
                                int ranColor = 0xff000000 | myRandom.nextInt(0x00ffffff);
                                label.setBackgroundColor(ranColor);

                                label.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(MyApp.getsMyApp(), NetWebActivity.class);
                                        intent.putExtra("web", link);
                                        intent.putExtra("title", title);
                                        startActivity(intent);
                                    }
                                });

                                //加到容器中,parent是FlowLayout
                                mFlow.addView(label);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void initView() {
        mFlow = (FlowLayout) findViewById(R.id.flow);

        mIv = (ImageView) findViewById(R.id.iv);
        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTv = (TextView) findViewById(R.id.tv);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mTv.setText("常用网站");
        Random myRandom = new Random();
        int ranColor = 0xff000000 | myRandom.nextInt(0x00ffffff);
        mTv.setTextColor(ranColor);
    }

    private void showToast(String data) {
        ToastUtil.showShort(data);
    }



}
