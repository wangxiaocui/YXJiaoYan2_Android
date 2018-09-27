package com.yanxiu.gphone.jiaoyan;

import android.os.StrictMode;

import com.yanxiu.lib.yx_basic_library.YXApplication;


public class JYApplication extends YXApplication {

    @Override
    public void onCreate() {
        super.onCreate();
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

}
