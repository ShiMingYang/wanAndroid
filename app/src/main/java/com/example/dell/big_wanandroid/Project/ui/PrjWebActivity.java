package com.example.dell.big_wanandroid.Project.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.big_wanandroid.GreenDaos.Person;
import com.example.dell.big_wanandroid.GreenDaos.UtilsDao;
import com.example.dell.big_wanandroid.R;

import java.lang.reflect.Method;

public class PrjWebActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbarp;
    private WebView mWeb;
    private String tt;
    private String url;
    private ImageView mIv;
    private TextView mTv;
    private MenuItem follow;
    private String author;
    private String charname;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prj_web);
        tt = getIntent().getStringExtra("tt");
        url = getIntent().getStringExtra("url");
        author = getIntent().getStringExtra("author");
        charname = getIntent().getStringExtra("charname");
        time = getIntent().getStringExtra("time");
        initView();
    }

    private void initView() {
        mToolbarp = (Toolbar) findViewById(R.id.toolbarp);
        mWeb = (WebView) findViewById(R.id.web);
        mTv = (TextView) findViewById(R.id.tv);
        mIv = (ImageView) findViewById(R.id.iv);
        mIv.setOnClickListener(this);

        mToolbarp.setTitle("");
        setSupportActionBar(mToolbarp);

         mTv.setText(tt);
        mWeb.setWebViewClient(new WebViewClient());
        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        mWeb.loadUrl(url);

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
                shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my Share text.");
                shareIntent.setType("text/plain");
                //设置分享列表的标题，并且每次都显示分享列表
                startActivity(Intent.createChooser(shareIntent, "分享到"));
                break;
            case R.id.it22:
                Uri uri = Uri.parse(url);
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.it2:
                Person person = new Person();
                person.setTitle(tt);
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
            case R.id.iv:
                finish();
                break;
        }
    }
}
