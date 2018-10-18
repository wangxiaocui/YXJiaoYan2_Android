package com.test.yanxiu.common_base.route.data;

import com.yanxiu.lib.yx_basic_library.base.bean.YXBaseBean;

/**
 * 跳转GoEvaluationActivity时，传入的值
 * Created by 戴延枫 on 2018/10/9.
 */

public class RouteCourseDetailCommitData extends YXBaseBean {
    private String courseId;

    private String evalutionContent;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getEvalutionContent() {
        return evalutionContent;
    }

    public void setEvalutionContent(String evalutionContent) {
        this.evalutionContent = evalutionContent;
    }
}
