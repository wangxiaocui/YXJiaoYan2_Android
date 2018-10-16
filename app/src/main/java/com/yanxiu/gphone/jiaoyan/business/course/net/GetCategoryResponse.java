package com.yanxiu.gphone.jiaoyan.business.course.net;

import com.test.yanxiu.common_base.base.net.JYBaseResponse;
import com.yanxiu.gphone.jiaoyan.business.course.bean.CourseCategoryBean;

import java.util.List;

/**
 * 视频课分类
 */
public class GetCategoryResponse extends JYBaseResponse {

    private List<CourseCategoryBean> data;

    public List<CourseCategoryBean> getData() {
        return data;
    }

    public void setData(List<CourseCategoryBean> data) {
        this.data = data;
    }
}
