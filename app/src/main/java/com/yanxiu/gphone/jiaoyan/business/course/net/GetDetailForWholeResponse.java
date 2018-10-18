package com.yanxiu.gphone.jiaoyan.business.course.net;

import com.test.yanxiu.common_base.base.net.JYBaseResponse;
import com.yanxiu.gphone.jiaoyan.business.course.bean.CourseBean;
import com.yanxiu.lib.yx_basic_library.base.bean.YXBaseBean;

import java.util.List;

public class GetDetailForWholeResponse extends JYBaseResponse {

    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        this.Data = data;
    }

    public class DataBean extends YXBaseBean {

        private int Total;
        private List<CourseBean> CourseList;

        public List<CourseBean> getCourseList() {
            return CourseList;
        }

        public void setCourseList(List<CourseBean> courseList) {
            CourseList = courseList;
        }

        public int getTotal() {
            return Total;
        }

        public void setTotal(int total) {
            Total = total;
        }
    }
}