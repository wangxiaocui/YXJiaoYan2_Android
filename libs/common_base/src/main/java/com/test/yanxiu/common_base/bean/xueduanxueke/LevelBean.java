package com.test.yanxiu.common_base.bean.xueduanxueke;

import com.yanxiu.lib.yx_basic_library.base.bean.YXBaseBean;

import java.util.List;

/**
 * Created by Hu Chao on 18/10/23.
 */
public class LevelBean extends YXBaseBean {
    private String id;
    private String name;
    private List<SubjectBean> subjects;

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

    public List<SubjectBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectBean> subjects) {
        this.subjects = subjects;
    }
}
