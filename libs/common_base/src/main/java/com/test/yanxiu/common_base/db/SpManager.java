package com.test.yanxiu.common_base.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.yanxiu.common_base.JYApplication;

import java.util.List;

/**
 * sharePreference管理类
 * 所有sp存储，都应该写在该类里
 */
public class SpManager {

    public static final String SP_NAME = "jiaoyan_sp";
    private static SharedPreferences mySharedPreferences = JYApplication.getContext().getApplicationContext()
            .getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

    // 自动连续播放
    private static final String AUTO_PLAY_NEXT = "auto_play_next";

    public static void setAutoPlayNext(boolean shouldAutoPlayNext) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putBoolean(AUTO_PLAY_NEXT, shouldAutoPlayNext);
        editor.commit();
    }

    public static boolean getAutoPlayNext() {
        return mySharedPreferences.getBoolean(AUTO_PLAY_NEXT, false);
    }

    // 允许移动网络播放视频
    private static final String ALLOW_4G_PLAY = "allow_4g_play";

    public static void setAllow4GPlay(boolean canPlayWith4G) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putBoolean(ALLOW_4G_PLAY, canPlayWith4G);
        editor.commit();
    }

    public static boolean getAllow4GPlay() {
        return mySharedPreferences.getBoolean(ALLOW_4G_PLAY, false);
    }

    // 4G观看清晰度
    private static final String VIDEO_RESOLUTION_4G = "video_resolution_4g";

    public static void setVideoResolution4g(String resolution) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(VIDEO_RESOLUTION_4G, resolution);
        editor.commit();
    }

    public static String getVideoResolution4g() {
        return mySharedPreferences.getString(VIDEO_RESOLUTION_4G, "流畅");
    }

    // 搜索历史
    private static final String SEARCH_HISTORY_KEY = "search_history_key";

    public static void setSearchHistoryKey(List<String> searchHistoryKey) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(SEARCH_HISTORY_KEY, new Gson().toJson(searchHistoryKey));
        editor.commit();
    }

    public static List<String> getSearchHistoryKey() {
        return new Gson().fromJson(mySharedPreferences.getString(SEARCH_HISTORY_KEY, null), new TypeToken<List<String>>() {
        }.getType());
    }
}
