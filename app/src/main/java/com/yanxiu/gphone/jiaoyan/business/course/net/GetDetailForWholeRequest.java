package com.yanxiu.gphone.jiaoyan.business.course.net;

import com.test.yanxiu.common_base.base.net.JYBaseRequest;
import com.test.yanxiu.common_base.base.net.JYMockRequest;
import com.yanxiu.lib.yx_basic_library.network.IYXHttpCallback;

import java.util.UUID;

/**
 * 2.2.2.	获取”全部”分类详细数据
 */
public class GetDetailForWholeRequest extends JYMockRequest {
    @Override
    public <T> UUID startRequest(final Class<T> clazz, final IYXHttpCallback<T> callback) {
        Token = "1234567890-0987654321";
        return super.startRequest(clazz, callback);
    }

    public String TailD = "0";
    public String PageSize = "10";

    @Override
    protected String urlServer() {
        return "http://10.10.20.243/v1";
    }

    @Override
    protected String urlPath() {
        return "/course/getDetailForWhole";
    }

    @Override
    protected HttpType httpType() {
        return HttpType.GET;
    }

    @Override
    protected int setMockDelayTime() {
        return 2;
    }

    @Override
    protected String getMockDataPath() {
        return "getDetailForWhole.json";
    }

    @Override
    protected double setMockRequestErrorProbability() {
        return 0;
    }
}
