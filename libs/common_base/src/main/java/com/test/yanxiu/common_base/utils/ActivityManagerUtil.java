package com.test.yanxiu.common_base.utils;

import android.app.Activity;

import com.yanxiu.lib.yx_basic_library.util.YXActivityMangerUtil;

/**
 * YXActivityManagerUtil扩展
 * Created by daiyanfeng on 2018/10/8.
 */

public class ActivityManagerUtil extends YXActivityMangerUtil {

    /**
     * 获取是否有首页
     *
     * @return
     */
    public static boolean hasMainActivity() {
        if (activityList != null && activityList.size() > 0) {
            for (Activity activity : activityList) {
                if ("MainActivity".equals(activity.getClass().getSimpleName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 退出登录
     *
     * @param activity
     */
    public static void signOut(Activity activity) {
        clearGTPushSettings();
        activity.finish();
    }

    /**
     * 去除 通知以及推送
     */
    private static void clearGTPushSettings() {
//        NotificationManager notificationManager = (NotificationManager) FSAApplication.getInstance()
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.cancelAll();
//        if (UserInfoManager.getInstance().getUserInfo() != null) {
//            PushManager.getInstance()
//                    .unBindAlias(FSAApplication.getInstance(),
//                            String.valueOf(UserInfoManager.getInstance().getUserInfo().getUserId()),
//                            true, "2000");//只对当前cid做解绑
//        }
    }

}
