package com.test.yanxiu.common_base.base.net;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.yanxiu.common_base.JYApplication;
import com.test.yanxiu.common_base.utils.FileUtils;
import com.yanxiu.lib.yx_basic_library.network.IYXHttpCallback;
import com.yanxiu.lib.yx_basic_library.network.YXRequestBase;
import com.yanxiu.lib.yx_basic_library.util.YXNetWorkUtil;

import java.util.Random;
import java.util.UUID;

/**
 * 使用mock数据时使用的接口
 *
 * @author frc
 *         created at 17-5-22.
 */

public abstract class JYMockRequest extends JYBaseRequest {

    public String SUCCESS = "0";
    public String NET_ERROR = "当前无网络";


    @Override
    public <T> UUID startRequest(final Class<T> clazz, final IYXHttpCallback<T> callback) {
        final YXRequestBase request = this;

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (!YXNetWorkUtil.isNetworkAvailable(JYApplication.getContext().getApplicationContext())) {
                    callback.onFail(request, new Error(NET_ERROR));
                } else {
                    try {
                        String url = fullUrl();
                    } catch (IllegalAccessException e) {
                    }
                    String json = FileUtils.getFromAssets(getMockDataPath());
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    final T ret = gson.fromJson(json, clazz);
                    final JYBaseResponse response = (JYBaseResponse) ret;


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final Random random = new Random();
                            int errorMax = 0;
                            if (setMockRequestErrorProbability() <= 1 && setMockRequestErrorProbability() >= 0) {
                                errorMax = (int) (100 * setMockRequestErrorProbability());
                            } else {
                                throw new Error("setMockRequestErrorProbability can not < 0 || >1");
                            }
                            if (random.nextInt(100) < errorMax) {
                                callback.onFail(request, new Error("data error"));
                            } else {
//                                if (response.getStatus().getCode() == 0) {
                                callback.onSuccess(request, ret);
//                                } else {
//                                    callback.onFail(request, new Error(response.getStatus().getCode() + ""));
//                                }
                            }
                        }

                    });
                }
            }
        };

        int delayTime = getDelayTime();
        if (setMockDelayTime() > 10 || setMockDelayTime() < 0) {
            throw new Error("delayTime is 0~10");
        }
        delayTime = setMockDelayTime() * delayTime;
        handler.sendEmptyMessageDelayed(0,

                delayTime);
        return null;
    }

    /**
     * 设置mok接口反应时间
     * cwq
     */
    protected int getDelayTime() {
        final Random random = new Random();
        return random.nextInt(1000);
    }

    ;

    protected abstract String getMockDataPath();

    /**
     * @return 模拟网络请求的ConnectTime   数值在0~10 之间越大时间越长
     */
    protected int setMockDelayTime() {
        return 2;
    }

    /**
     * 设置网络请求失败的概率
     **/
    protected double setMockRequestErrorProbability() {
        return 0.2;
    }
}
