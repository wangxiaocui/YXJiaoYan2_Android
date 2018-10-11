package com.test.yanxiu.common_base.utils.checkLogin;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;

import java.io.Serializable;

/**
 * Created by Hu Chao on 18/10/10.
 */
public class CheckLoginUtil {

    public static boolean hasLogin;

    public static void checkLogin(Activity activity, final OnLoginStateCallback callback) {
        if (!hasLogin) {
            RouteUtils.startActivityForResult(activity, RoutePathConfig.SIGNIN_LOGIN_ACTIVITY, new AvoidOnResult.Callback() {
                @Override
                public void onActivityResult(int resultCode, Intent data) {
                    if (resultCode == Activity.RESULT_OK) {
                        hasLogin = true;
                        callback.onLoginSuccess();
                    } else {
                        callback.onLoginFail();
                    }
                }
            });
        } else {
            callback.onLoginSuccess();
        }
    }

    public static void checkLogin(Fragment fragment, final OnLoginStateCallback callback) {
        checkLogin(fragment.getActivity(), callback);
    }

    public interface OnLoginStateCallback extends Serializable {

        void onLoginSuccess();

        void onLoginFail();
    }

}
