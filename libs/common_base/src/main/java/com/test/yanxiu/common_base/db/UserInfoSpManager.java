package com.test.yanxiu.common_base.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.test.yanxiu.common_base.JYApplication;
import com.test.yanxiu.common_base.bean.UserInfoBean;

/**
 * sharePreference管理类
 * 所有sp存储，都应该写在该类里
 */
public class UserInfoSpManager {

    public static final String SP_NAME = "jiaoyan_sp_userInfo";
    private static SharedPreferences mySharedPreferences = JYApplication.getContext().getApplicationContext()
            .getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);


    private static final String USER_INFO = "userInfo";
    /*用户唯一标示token*/
    private static final String TOKEN = "token";
    private static final String PASSPORT = "pass_port";

    /**
     * 保存token
     *
     * @param token 唯一标示
     */
    protected static void saveToken(String token) {
        if (TextUtils.isEmpty(token)) {
            token = null;
        }
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(TOKEN, token);
        editor.commit();
    }

    /**
     * 获取token
     *
     * @return token
     */
    protected static String getToken() {
        if (TextUtils.isEmpty(mySharedPreferences.getString(TOKEN, null))) {
            return null;
        }
        return mySharedPreferences.getString(TOKEN, null);
    }


    protected static void savePassPort(String passPort) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(PASSPORT, passPort);
        editor.commit();
    }


    //region userinfo

    /**
     * 保存或clearuserinfo
     *
     * @param userInfo 不为空即保存；传入null即clear
     */
    protected static void saveUserInfo(@Nullable UserInfoBean userInfo) {
        String userInfoStr = "";
        if (userInfo != null) {
            userInfoStr = new Gson().toJson(userInfo);
        }
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(USER_INFO, userInfoStr);
        editor.apply();
    }


    protected static UserInfoBean getUserInfo() {
        String userInfoStr = mySharedPreferences.getString(USER_INFO, "");
        return new Gson().fromJson(userInfoStr, UserInfoBean.class);
    }
    //endregion


    //region 其他信息
    /**
     * 记录最近登录的账号
     */
    public static final String LAST_ACCOUNT_RECORDS = "LastAccount";//最近一次登录账号记录

    /**
     * 记录最近成功登录的账号
     *
     * @param account 最近登录的账号
     */
    public static void saveLastAccount(String account) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(LAST_ACCOUNT_RECORDS, account);
        editor.commit();
    }

    /**
     * 获取上次登录成功的账号
     *
     * @return
     */
    public static String getLastAccount() {
        return mySharedPreferences.getString(LAST_ACCOUNT_RECORDS, "");
    }
    //endregion 其他信息
}
