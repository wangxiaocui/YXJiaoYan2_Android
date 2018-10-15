package com.yanxiu.gphone.jiaoyan.business.course.bean;

import com.yanxiu.lib.yx_basic_library.base.bean.YXBaseBean;

/**
 * Created by Hu Chao on 18/6/11.
 */
public class ClassStudyScoreRankingBean extends YXBaseBean {

    private int userId;
    private String userName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
