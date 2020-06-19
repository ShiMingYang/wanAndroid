package com.example.dell.big_wanandroid.MainUi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.big_wanandroid.R;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.uname)
    EditText uname;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.qrpwd)
    EditText qrpwd;
    @BindView(R.id.regist)
    Button regist;
    @BindView(R.id.re_back)
    ImageView reBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //initView();
    }

    private void initView() {
        final String name = uname.getText().toString().trim();
        final String pwd = this.pwd.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String qrpwd = this.qrpwd.getText().toString().trim();
        if (!pwd.equals(qrpwd)) {
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("psw", pwd);
        EventBus.getDefault().post(map);
        finish();



       /* Retrofit retrofit = new Retrofit.Builder().baseUrl(MyServer.regisUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        HashMap<String, Object> map = new HashMap<>();
        *//*username:“zhangsan”;  //注册输入的用户名
        password:“123456”;  //密码
        phone:”13400000000”;  //手机号格式要正确
        verify:”zwdf” //获取到的验证码*//*
        map.put("username", uname);
        map.put("password", pwd);
        map.put("phone", uname);
        map.put("verify", uname);
        Observable<RegisterBean> data = myServer.regisData(map);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        if (registerBean.getCode()==200) {
                            Toast.makeText(RegisterActivity.this, registerBean.getRet(), Toast.LENGTH_SHORT).show();
                            HashMap<String, String> map = new HashMap<>();
                            map.put("name",name);
                            map.put("psw",pwd);
                            EventBus.getDefault().post(map);
                            finish();
                        }else {
                            Toast.makeText(RegisterActivity.this, "失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
*/

    }


    @OnClick({R.id.re_back, R.id.regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.re_back:
                finish();
                break;
            case R.id.regist:
                initView();
                break;
        }
    }
}
