package com.yanxiu.gphone.jiaoyan.module.signin.net;

import com.test.yanxiu.common_base.base.net.JYBaseRequest;

/**
 * 获取手机验证码
 * Created by Hu Chao on 18/10/11.
 */
public class GetMobileRequest extends JYBaseRequest {

    public String Mobile;
    public String Type;

    @Override
    protected String urlPath() {
        return "/verifyCode/getMobile";
    }

}
