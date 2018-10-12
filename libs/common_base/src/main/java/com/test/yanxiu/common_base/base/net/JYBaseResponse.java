package com.test.yanxiu.common_base.base.net;

import java.io.Serializable;

/**
 * rersponse基类
 */

public class JYBaseResponse implements Serializable {
    private int code;
    private String info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
