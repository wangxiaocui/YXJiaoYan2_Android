package com.yanxiu.gphone.jiaoyan.business.mine.network;

import com.test.yanxiu.common_base.base.net.JYBaseRequest;
import com.test.yanxiu.common_base.base.net.JYBaseResponse;
import com.yanxiu.lib.yx_basic_library.network.IYXHttpCallback;
import com.yanxiu.lib.yx_basic_library.network.YXRequestBase;
import com.yanxiu.lib.yx_basic_library.util.logger.YXLogger;

import java.util.UUID;

import okhttp3.Request;

/**
 * 签到接口
 */
public final class SignRequest extends JYBaseRequest {
    @Override
    protected String urlPath() {
        return "user/sign";
    }

    @Override
    public <T> UUID startRequest(final Class<T> clazz, final IYXHttpCallback<T> callback) {
        return super.startRequest(JYBaseResponse.class, new IYXHttpCallback<JYBaseResponse>() {
            @Override
            public void onRequestCreated(Request request) {

            }

            @Override
            public void onSuccess(YXRequestBase request, JYBaseResponse ret) {
                YXLogger.d("cailei", ret.getInfo());
            }

            @Override
            public void onFail(YXRequestBase request, Error error) {
                YXLogger.d("cailei", error.getLocalizedMessage());
            }
        });
    }
}
