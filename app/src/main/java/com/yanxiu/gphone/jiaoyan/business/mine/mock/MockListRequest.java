package com.yanxiu.gphone.jiaoyan.business.mine.mock;

import com.yanxiu.gphone.jiaoyan.business.course.bean.CourseBean;
import com.yanxiu.lib.yx_basic_library.network.IYXHttpCallback;
import com.yanxiu.lib.yx_basic_library.network.YXRequestBase;
import com.yanxiu.lib.yx_basic_library.util.logger.YXLogger;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * Created By cailei on 2018/10/18
 */
public class MockListRequest extends YXRequestBase {
    public int pageIndex = 0;
    public int pageSize = 10;

    @Override
    protected String urlServer() {
        return null;
    }

    @Override
    public <T> UUID startRequest(Class<T> clazz, final IYXHttpCallback<T> callback) {
        Random random = new Random();
        Integer delay = random.nextInt(4000) + 1000;

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                response(callback);
            }
        }, delay);

        return UUID.randomUUID();
    }

    private <T> void response(IYXHttpCallback<T> callback) {
        // 随机决定接口是否成功 10%失败
        int rand = new Random().nextInt(100 + 1);
        if (rand < 50) {
        //if (rand < 0) {
            YXLogger.e("cailei", "mock 网络错误");
            callback.onFail(this, new Error());
            return;
        }

        // 决定是否还有更多数据 10%没有有更多数据了
        rand = new Random().nextInt(100 + 1);
        if (rand < 10) {
        //if (rand < 0) {
            // 没有更多数据了,返回空data
            MockListResponse response = new MockListResponse();
            response.code = 0;
            response.data = new ArrayList<>();
            callback.onSuccess(this, (T) response);
            YXLogger.e("cailei", "mock 最后一页数据");
            return;
        }

        MockListResponse response = new MockListResponse();
        response.code = 0;
        response.data = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            CourseBean bean = new CourseBean();
            response.data.add(bean);
        }
        callback.onSuccess(this, (T) response);
        YXLogger.e("cailei", "mock 成功获取一页数据");
    }
}
