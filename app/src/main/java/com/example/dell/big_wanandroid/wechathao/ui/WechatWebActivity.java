package com.example.dell.big_wanandroid.wechathao.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dell.big_wanandroid.GreenDaos.Person;
import com.example.dell.big_wanandroid.GreenDaos.UtilsDao;
import com.example.dell.big_wanandroid.R;

import java.lang.reflect.Method;

public class WechatWebActivity extends AppCompatActivity implements View.OnClickListener {


    private String link;
    private String titles;
    private ImageView mIvBack;
    private Toolbar mToolbar;
    private WebView mWeb;
    private ProgressBar mPbar;
    private TextView mTvtitle;
    private MenuItem follow;
    private String author;
    private String charname;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_web);
        link = getIntent().getStringExtra("clink");
        titles = getIntent().getStringExtra("ttt");
        author = getIntent().getStringExtra("author");
        charname = getIntent().getStringExtra("charname");
        time = getIntent().getStringExtra("time");
        initView();


    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mWeb = (WebView) findViewById(R.id.web);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(this);
        mPbar = (ProgressBar) findViewById(R.id.pbar);
        mTvtitle = (TextView) findViewById(R.id.tvtitle);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mTvtitle.setText(titles);

        mWeb.setWebViewClient(new WebViewClient());
        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        mWeb.loadUrl(link);

        mWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    mPbar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    // 加载中
                    mPbar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    mPbar.setProgress(newProgress);//设置进度值
                }
            }
        });


    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus, menu);
        follow = menu.findItem(R.id.it2);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.it11:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, link);
                shareIntent.setType("text/plain");
                //设置分享列表的标题，并且每次都显示分享列表
                startActivity(Intent.createChooser(shareIntent, "分享到"));
                break;
            case R.id.it22:
                //跳转到浏览器的操作
                Uri uri = Uri.parse(link);
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.it2:
                Person person = new Person();
                person.setTitle(titles);
                person.setNiceDate(time);
                person.setChapterName(charname);
                person.setAuthor(author);
                Person queryData = UtilsDao.getUtilsDao().queryData(person);
                if (queryData==null) {
                    UtilsDao.getUtilsDao().inser(person);
                    follow.setIcon(R.mipmap.follow);
                }else {
                    UtilsDao.getUtilsDao().deletes(queryData);
                    follow.setIcon(R.mipmap.follow_unselected);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
