package com.test.yanxiu.common_base.base.net;

import java.io.Serializable;

/**
 * rersponse基类
 */

public class JYBaseResponse implements Serializable {
    private int Code;
    private String Info;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        this.Code = code;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        this.Info = info;
    }

}
