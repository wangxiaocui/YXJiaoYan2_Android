package com.test.yanxiu.common_base.route.data;

import com.yanxiu.lib.yx_basic_library.base.bean.YXBaseBean;

/**
 * 跳转SettingSubjectActivity时，传入的值
 * Created by Hu Chao on 2018/10/23.
 */

public class RouteSubjectTypeData extends YXBaseBean {

    public static final int FROM_USER_INFO = 0; //用户信息的学科学段
    public static final int FROM_USER_INTEREST = 1; //感兴趣的学科学段

    private int from;


    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }
}
