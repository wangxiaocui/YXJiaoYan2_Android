package com.yanxiu.gphone.jiaoyan.business.course_detail.bean;

import com.yanxiu.lib.yx_basic_library.base.bean.YXBaseBean;

/**
 * Created by 戴延枫 on 2018/10/11.
 */

public class IntroductionBean extends YXBaseBean {
    private String Name;//姓名
    private String Head;//头像
    private String HonorName;//	荣誉称号
    private String RealCert;//实名认证状态 0未认证 1已认证

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getHead() {
        return Head;
    }

    public void setHead(String head) {
        Head = head;
    }

    public String getHonorName() {
        return HonorName;
    }

    public void setHonorName(String honorName) {
        HonorName = honorName;
    }

    public String getRealCert() {
        return RealCert;
    }

    public void setRealCert(String realCert) {
        RealCert = realCert;
    }
}
