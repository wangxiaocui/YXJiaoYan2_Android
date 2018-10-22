package com.yanxiu.gphone.jiaoyan.business.course.bean;

import com.yanxiu.lib.yx_basic_library.base.bean.YXBaseBean;

/**
 * Created by Hu Chao on 18/10/18.
 */
public class CourseBean extends YXBaseBean {
    private String ID;
    private int Recommend;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getRecommend() {
        return Recommend;
    }

    public void setRecommend(int recommend) {
        Recommend = recommend;
    }
}
