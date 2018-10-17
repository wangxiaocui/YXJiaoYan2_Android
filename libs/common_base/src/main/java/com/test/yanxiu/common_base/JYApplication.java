package com.test.yanxiu.common_base;

import android.app.Application;
import android.os.StrictMode;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.test.yanxiu.common_base.utils.FileUtils;
import com.test.yanxiu.common_base.utils.serverUrl.EnvConfigBean;
import com.test.yanxiu.common_base.utils.serverUrl.UrlBean;
import com.test.yanxiu.common_base.utils.serverUrl.UrlRepository;
import com.yanxiu.lib.yx_basic_library.YXApplication;


public class JYApplication extends YXApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        initRouter(this);
        initUrlServer();
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

    private void initUrlServer() {
        UrlBean urlBean;
        Gson gson = new Gson();
        String urlJson = FileUtils.getFromAssets(Constants.URL_SERVER_FILE_NAME);
        if (urlJson.contains(Constants.MULTICONFIG)) {
            EnvConfigBean envConfigBean = gson.fromJson(urlJson, EnvConfigBean.class);
            urlBean = chooseTheOne(envConfigBean, envConfigBean.getCurrentIndex());
        } else {
            urlBean = gson.fromJson(urlJson, UrlBean.class);
        }
        UrlRepository.getInstance().setUrlBean(urlBean);

    }

    // 取第一个和设置的currentIndex值相同的bean，没有则返回null
    private UrlBean chooseTheOne(EnvConfigBean configBean, int index) {
        UrlBean ret = null;
        for (UrlBean bean : configBean.getData()) {
            if (bean.index == index) {
                ret = bean;
                break;
            }
        }

        return ret;
    }

}
