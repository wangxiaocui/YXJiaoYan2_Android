package com.yanxiu.gphone.jiaoyan.business.mine.network;

import com.test.yanxiu.common_base.utils.serverUrl.UrlRepository;
import com.yanxiu.lib.yx_basic_library.network.YXRequestBase;

public class YXGetQiNiuTokenRequest extends YXRequestBase {
    public String method = "upload.token";
    public String type;
    public String size;
    public String name;
    public String lastModifiedDate;
    public String dtype = "app";    // 必填
    public String token;            // app需要填写
    public String from = "100";     // 必填
    public String shareType;

    @Override
    protected String urlServer() {
        return UrlRepository.getInstance().getQiNiuServer();
    }
}
