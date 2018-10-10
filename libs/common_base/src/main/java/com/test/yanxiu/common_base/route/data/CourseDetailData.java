package com.test.yanxiu.common_base.route.data;

import com.yanxiu.lib.yx_basic_library.base.bean.YXBaseBean;

/**
 * 跳转CourseDetaiActivity时，传入的值
 * Created by 戴延枫 on 2018/10/9.
 */

public class CourseDetailData extends YXBaseBean {
    private String courseId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
