package com.yanxiu.gphone.jiaoyan.business.course.net;

import com.test.yanxiu.common_base.base.net.JYMockRequest;

/**
 * 视频课分类
 * Created by Hu Chao on 18/10/15.
 */
public class GetCategoryRequest extends JYMockRequest {

    @Override
    protected String urlServer() {
        return "http://hbyxb.ymd.yanxiu.com/pxt/platform/data.api";
    }

    @Override
    protected HttpType httpType() {
        return HttpType.POST;
    }

    @Override
    protected String getMockDataPath() {
        return "getCategory.json";
    }

    @Override
    protected int setMockDelayTime() {
        return 2;
    }

    @Override
    protected double setMockRequestErrorProbability() {
        return 0;
    }
}