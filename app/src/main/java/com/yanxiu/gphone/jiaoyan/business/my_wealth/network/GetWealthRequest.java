package com.yanxiu.gphone.jiaoyan.business.my_money.network;

import com.test.yanxiu.common_base.base.net.JYBaseRequest;

/**
 * Created By cailei on 2018/10/23
 */
public class GetWealthRequest extends JYBaseRequest {
    @Override
    protected String urlPath() {
        return "user/getWealth";
    }

    public String TailD;
    public String PageSize;
}
