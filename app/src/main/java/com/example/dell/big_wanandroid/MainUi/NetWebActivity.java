package com.example.dell.big_wanandroid.MainUi;

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

import com.example.dell.big_wanandroid.R;

public class NetWebActivity extends AppCompatActivity  {

    private ImageView mIvBack;
    private Toolbar mToolbar;
    private WebView mWeb;
    private String web;
    private String title;
    private ProgressBar mPbar;
    private TextView mTvtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_web);
        web = getIntent().getStringExtra("web");
        title = getIntent().getStringExtra("title");
        initView();

    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mWeb = (WebView) findViewById(R.id.web);
        mTvtitle = (TextView) findViewById(R.id.tvtitle);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mTvtitle.setText(title);

        mWeb.setWebViewClient(new WebViewClient());
        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        mWeb.loadUrl(web);
        mPbar = (ProgressBar) findViewById(R.id.pbar);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus, menu);


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
                Uri uri = Uri.parse(web);
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
