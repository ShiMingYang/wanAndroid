package com.example.dell.big_wanandroid.bean;

/**
 * Created by Dell on 2019/4/28.
 */

public class RegisterBean {

    /**
     * code : 200
     * ret : 注册成功
     * data :
     */

    private int code;
    private String ret;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
