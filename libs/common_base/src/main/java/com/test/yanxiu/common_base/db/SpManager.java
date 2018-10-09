package com.test.yanxiu.common_base.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.test.yanxiu.common_base.JYApplication;

/**
 * sharePreference管理类
 * 所有sp存储，都应该写在该类里
 */
public class SpManager {

    public static final String SP_NAME = "jiaoyan_sp";
    private static SharedPreferences mySharedPreferences = JYApplication.getContext().getApplicationContext()
            .getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);


}
