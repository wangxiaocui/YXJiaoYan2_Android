package com.yanxiu.gphone.jiaoyan.business.course_detail.bean;

import com.yanxiu.lib.yx_basic_library.base.bean.YXBaseBean;

/**
 * DirectioryAdapter里的数据
 * Created by 戴延枫 on 2018/10/11.
 */

public class DirectioryBean extends YXBaseBean {
    private String url;//图片链接
    private String classNum;//课程节数
    private String className;//	课程名字

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
