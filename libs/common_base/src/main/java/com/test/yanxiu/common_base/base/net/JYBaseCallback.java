package com.test.yanxiu.common_base.base.net;

import com.yanxiu.lib.yx_basic_library.network.IYXHttpCallback;
import com.yanxiu.lib.yx_basic_library.network.YXRequestBase;

/**
 * Created by Hu Chao on 18/10/15.
 */
public abstract class JYBaseCallback<T extends JYBaseResponse> implements IYXHttpCallback<T> {

    @Override
    public final void onSuccess(YXRequestBase request, T ret) {
        onSuccessResponse(request, ret);
//        if (ret.getCode() == 200) {
//            onSuccessResponse(request, ret);
//        } else {
//            onFail(request, new Error(ret.getInfo()));
//        }
    }

    protected abstract void onSuccessResponse(YXRequestBase request, T ret);

}
