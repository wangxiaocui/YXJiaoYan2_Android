package com.test.yanxiu.common_base;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yanxiu.lib.yx_basic_library.YXApplication;


public class JYApplication extends YXApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        initRouter(this);
        try {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error error) {
            error.printStackTrace();
        }
    }

    private void initRouter(Application application) {
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        //打印日志
        ARouter.openLog();
        //开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！
        //线上版本需要关闭,否则有安全风险)
        ARouter.openDebug();
        // 尽可能早，推荐在Application中初始化
        ARouter.init(application);
    }

}
