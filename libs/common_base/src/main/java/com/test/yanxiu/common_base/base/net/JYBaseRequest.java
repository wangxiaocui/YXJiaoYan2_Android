package com.test.yanxiu.common_base.base.net;

import com.test.yanxiu.common_base.Constants;
import com.test.yanxiu.common_base.utils.serverUrl.UrlRepository;
import com.yanxiu.lib.yx_basic_library.network.YXRequestBase;


/**
 * requset基类
 */

public abstract class JYBaseRequest extends YXRequestBase {
    // TODO 这里的参数，需要根据server的协议来定，目前都是易学易练的公用参数
    public String osType = Constants.osType;
    public String pcode = Constants.pcode;
//    public String token = UserInfoManager.getInstance().getToken();
    public String trace_uid;
    public String version = Constants.version;


    @Override
    protected String urlServer() {
        return UrlRepository.getInstance().getServer();
    }

    @Override
    protected boolean shouldLog() {
        return true;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }

    public String getTrace_uid() {
        return trace_uid;
    }

    public void setTrace_uid(String trace_uid) {
        this.trace_uid = trace_uid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
