package com.test.yanxiu.common_base.base.net;

import com.test.yanxiu.common_base.Constants;
import com.test.yanxiu.common_base.db.UserInfoManager;
import com.test.yanxiu.common_base.utils.serverUrl.UrlRepository;
import com.yanxiu.lib.yx_basic_library.network.YXRequestBase;
import com.yanxiu.lib.yx_basic_library.network.YXRequestParamType;


/**
 * requset基类
 */
public abstract class JYBaseRequest extends YXRequestBase {

    @YXRequestParamType(YXRequestParamType.Type.GET)
    public String platform = Constants.PLATFORM;
    @YXRequestParamType(YXRequestParamType.Type.GET)
    public String versionCode = Constants.version;

    public String token = UserInfoManager.getInstance().getToken();

    @Override
    protected String urlServer() {
        return UrlRepository.getInstance().getServer() + ver();
    }

    /**
     * 后续如果某个接口改变version，需要子类重写
     * @return
     */
    protected String ver() {
        return "/v1";
    }

    @Override
    protected HttpType httpType() {
        return HttpType.POST;
    }

    @Override
    protected boolean shouldLog() {
        return true;
    }

}
