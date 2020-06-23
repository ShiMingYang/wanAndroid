package com.example.dell.big_wanandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.big_wanandroid.MainUi.CollectActivity;
import com.example.dell.big_wanandroid.MainUi.LoginActivity;
import com.example.dell.big_wanandroid.MainUi.MainScrollingActivity;
import com.example.dell.big_wanandroid.MainUi.MyApp;
import com.example.dell.big_wanandroid.MainUi.NetActivity;
import com.example.dell.big_wanandroid.MainUi.SerachActivity;
import com.example.dell.big_wanandroid.View.EmapyV;
import com.example.dell.big_wanandroid.api.MyServer;
import com.example.dell.big_wanandroid.base.BaseActivity;
import com.example.dell.big_wanandroid.bean.FlowBean;
import com.example.dell.big_wanandroid.fragment.CrossFragment;
import com.example.dell.big_wanandroid.fragment.HomeFragment;
import com.example.dell.big_wanandroid.fragment.MoveFragment;
import com.example.dell.big_wanandroid.fragment.ProjectFragment;
import com.example.dell.big_wanandroid.fragment.SettingFragment;
import com.example.dell.big_wanandroid.fragment.StudyFragment;
import com.example.dell.big_wanandroid.presenter.EmapyP;
import com.example.dell.big_wanandroid.utils.CircularAnimUtil;
import com.example.dell.big_wanandroid.utils.Constants;
import com.example.dell.big_wanandroid.utils.MyJZVideoPlayerStandard;
import com.example.dell.big_wanandroid.utils.SpUtil;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends BaseActivity<EmapyP, EmapyV> implements EmapyV {

  private static final int HOME = 0;
    private static final int STUDY = 1;
    private static final int CROSS = 2;
    private static final int MOVE = 3;
    private static final int PROJCET = 4;
    public static final int MAIN = 5;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.yyy)
    TextView myyy;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fl)
    FrameLayout mFl;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.nv)
    NavigationView mNv;
    @BindView(R.id.dl)
    DrawerLayout mDl;
    @BindView(R.id.btn1)
    RadioButton btn1;
    @BindView(R.id.btn2)
    RadioButton btn2;
    @BindView(R.id.btn3)
    RadioButton btn3;
    @BindView(R.id.btn4)
    RadioButton btn4;
    @BindView(R.id.btn5)
    RadioButton btn5;
    private FragmentManager manager;
    private ArrayList<Fragment> fragments;
    private int mlastposition;
    private TextView tv;
    private View unlogin;
    private int type;
    private MenuItem serachitem;
    private String uname;
    private String uname1;
    private ImageView ivhead;


    private void initFragment() {

        //如果是因为切换日夜间模式导致Activyt重建,需要直接进入设置Fragment
       type = (int) SpUtil.getParam(Constants.DAY_NIGHT_FRAGMENT_POS, HOME);

        FragmentTransaction transaction = manager.beginTransaction();
        FragmentTransaction add = transaction.add(R.id.fl, fragments.get(0));
        add.commit();

    }



    private void addFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new StudyFragment());
        fragments.add(new CrossFragment());
        fragments.add(new MoveFragment());
        fragments.add(new ProjectFragment());
        fragments.add(new SettingFragment());
        mTv.setText(R.string.home);
    }

      //回调注册的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            uname = data.getStringExtra("uname");
            tv.setText(uname);
            unlogin.setVisibility(View.VISIBLE);

        }
    }
    //三方回调的登录信息
    @Subscribe(threadMode = ThreadMode.MAIN)
      public void getlogindata(Map<String, String> data){

        if (data!=null) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key.equals("name")) {
                    tv.setText(value);
                }else if(key.equals("iconurl")){
                    Glide.with(this).load(value).into(ivhead);
                }
            }
        }
      }
    private void loginData() {
        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        uname1 = login.getString("uname", "");
        if (uname1.equals("")) {
            tv.setText("未登录");
            unlogin.setVisibility(View.GONE);
        } else {

            tv.setText(uname1);
            unlogin.setVisibility(View.VISIBLE);
        }
    }

    protected void initView() {

        EventBus.getDefault().register(this);
        CrashReport.initCrashReport(getApplicationContext());
        manager = getSupportFragmentManager();
        addFragment();
        initFragment();


        mToolbar.setTitle("");//使用自己写的ToolBar 隐藏到系统的
        setSupportActionBar(mToolbar);
        mNv.setItemIconTintList(null);

         //侧滑
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToolbar, R.string.app_name, R.string.app_name);
        mDl.addDrawerListener(toggle);
        toggle.syncState();

        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        //旋转主界面
        mDl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                // 滑动的过程当中不断地回调 slideOffset：0~1
                View content = mDl.getChildAt(0);
                float scale = 1 - slideOffset;//1~0
                float leftScale = (float) (1 - 0.18 * scale);
                float rightScale = (float) (0.82f + 0.18 * scale);//0.7~1
                drawerView.setScaleX(leftScale);//1~0.7
                drawerView.setScaleY(leftScale);//1~0.7

                content.setScaleX(rightScale);
                content.setScaleY(rightScale);
                content.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));//0~width

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        //侧滑头布局
        View headerView = mNv.getHeaderView(0);
        tv = headerView.findViewById(R.id.tv_head);
        ivhead = headerView.findViewById(R.id.iv_head);
        unlogin = headerView.findViewById(R.id.unlogin);
        unlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unlogin();
                loginData();
            }
        });
        headerView.findViewById(R.id.tv_head).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivityForResult(intent, 100);
            }
        });
        //侧滑的点击item事件
        mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.it1:
                        mDl.closeDrawer(Gravity.LEFT);
                        btn1.setChecked(true);
                        if (HOME!=mlastposition) {
                            switchFrafment(HOME);

                        }
                        rg.setVisibility(View.VISIBLE);
                        mTv.setText(R.string.home);

                        break;
                    case R.id.it2:

                        if (uname==null) {
                            Intent intent = new Intent(MyApp.getsMyApp(),LoginActivity.class);
                            startActivityForResult(intent,100);
                        }else {
                            Intent intent = new Intent(MyApp.getsMyApp(),CollectActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case R.id.it3:
                        mDl.closeDrawer(Gravity.LEFT);
                        if (MAIN!=mlastposition) {
                            switchFrafment(MAIN);
                            rg.setVisibility(View.GONE);
                            mTv.setText("设置");
                        }
                        break;
                    case R.id.it4:
                    startActivity(new Intent(MyApp.getsMyApp(), MainScrollingActivity.class));
                        break;
                }

                return false;
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn1:
                        switchFrafment(HOME);
                        mTv.setText(R.string.home);
                        break;
                    case R.id.btn2:
                        switchFrafment(STUDY);
                        mTv.setText(R.string.study);
                        break;
                    case R.id.btn3:
                        switchFrafment(CROSS);
                        mTv.setText(R.string.cross);
                        break;
                    case R.id.btn4:
                        switchFrafment(MOVE);
                        mTv.setText(R.string.move);
                        break;
                    case R.id.btn5:
                        switchFrafment(PROJCET);
                        mTv.setText(R.string.project);
                        break;
                }
            }
        });


    }

    private void unlogin() {
        //注销登录
        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor edit = login.edit();
        edit.putString("uname", "");
        edit.putString("psw", "");
        edit.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        serachitem = menu.findItem(R.id.op2);
        serachitem.setVisible(true);
        //serch.setMenuItem(serachitem);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.op1:
                Intent intent = new Intent();
                intent.setClass(MyApp.getsMyApp(), NetActivity.class);
                CircularAnimUtil.startActivity(this, intent, myyy, R.color.fab_bg);
                //CircularAnimUitl.
                break;

            case R.id.op2:
                Intent intent1 = new Intent();
                intent1.setClass(MyApp.getsMyApp(), SerachActivity.class);
                //myyy  是一个依赖控件，就是从哪个控件开始往四周扩散，在ToolBar的最右侧写一个空的控件
                CircularAnimUtil.startActivity(this, intent1, myyy, R.color.fab_ce);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 单击回退
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(MyJZVideoPlayerStandard.c == 1){

                JZVideoPlayer.quitFullscreenOrTinyWindow();

                MyJZVideoPlayerStandard.c = 0;
                //这个方法是点击回退 保持当前红心状态
             /*   Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                return true;*/
            }else {
                exitBy2Click();
            }
        }
        return false;
    }
    /**
     * 双击退出
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            SpUtil.setParam(Constants.DAY_NIGHT_FRAGMENT_POS,HOME);
            Snackbar.make(rg, "再按一次回退建退出玩Android", Snackbar.LENGTH_LONG)
                    .setAction("知道了", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MyApp.getInstance(), "撤销了退出", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出

                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected EmapyP initPresenter() {
        return new EmapyP();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_home;
    }


    private void switchFrafment(int position) {
        Fragment fragment = fragments.get(position);
        FragmentTransaction transaction = manager.beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl, fragment);
        }
        transaction.show(fragment);
        transaction.hide(fragments.get(mlastposition));
        transaction.commit();
        mlastposition = position;
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request build = new Request.Builder().url("").build();
//        Call call = okHttpClient.newCall(build);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//            }
//        });
    }

    public static void setSessionContinueMillis(long interval) {
        // 将默认Session间隔时长改为40秒。
        MobclickAgent.setSessionContinueMillis(1000*40);
    }




    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MyServer.homeUrl)
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<FlowBean> flowdata = myServer.flowdata();




    }





}
