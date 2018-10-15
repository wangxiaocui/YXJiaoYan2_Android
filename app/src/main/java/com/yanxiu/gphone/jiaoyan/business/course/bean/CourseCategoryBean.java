package com.yanxiu.gphone.jiaoyan.business.course.bean;

import com.yanxiu.lib.yx_basic_library.base.bean.YXBaseBean;

/**
 * Created by Hu Chao on 18/10/15.
 */
public class CourseCategoryBean extends YXBaseBean {
    private String id;
    private String name;
    private String keyword;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
