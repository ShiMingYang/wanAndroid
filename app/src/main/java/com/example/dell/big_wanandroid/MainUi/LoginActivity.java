package com.example.dell.big_wanandroid.MainUi;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.utils.ToastUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.uname)
    EditText uname;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.qq)
    ImageView qq;
    @BindView(R.id.wx)
    ImageView wx;
    @BindView(R.id.weibo)
    ImageView weibo;
    @BindView(R.id.lllll)
    LinearLayout lllll;
    private ImageView mBack;
    /**
     * 登录
     */
    private TextView mTv;
    /**
     * 请输入用户名
     */
    private EditText mUname;
    /**
     * 请输入密码
     */
    private EditText mPwd;
    /**
     * 登录
     */
    private Button mLogin;

    /**
     * 注册
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        initper();
    }

    private void initper() {
        String[] per = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, per, 100);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.back, R.id.login, R.id.register, R.id.qq, R.id.wx, R.id.weibo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.login:
                String names = uname.getText().toString();
                String pwds = pwd.getText().toString();
                if (names.isEmpty() || pwds.isEmpty()) {
                    ToastUtil.showShort("账号或者密码不能为空");
                    return;
                }
                Intent intent = new Intent();
                SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor edit = login.edit();
                edit.putString("uname", names);
                edit.commit();
                intent.putExtra("uname", names);
                intent.putExtra("pwds", pwds);

                setResult(200, intent);
                finish();
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.qq:
                QQlogin(SHARE_MEDIA.QQ);

                break;
            case R.id.wx:
                break;
            case R.id.weibo:
                Login(SHARE_MEDIA.SINA);
                break;

        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void login(HashMap<String, String> map) {
        if (map != null) {
            String name = map.get("name");
            String pwds = map.get("pwd");
            uname.setText(name);
            pwd.setText(pwds);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    private void initView() {

    }


    private void QQlogin(SHARE_MEDIA type) {
        UMShareAPI umShareAPI = UMShareAPI.get(this);
        umShareAPI.getPlatformInfo(LoginActivity.this, type, authListener);
    }

    private void Login(SHARE_MEDIA type) {
        UMShareAPI umShareAPI = UMShareAPI.get(this);
        umShareAPI.getPlatformInfo(LoginActivity.this, type, authListener);
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
//                Log.e("AAAAAAAA", "onComplete: "+key+",,,,12"+value );
                EventBus.getDefault().post(data);
                finish();
            }
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };


}
