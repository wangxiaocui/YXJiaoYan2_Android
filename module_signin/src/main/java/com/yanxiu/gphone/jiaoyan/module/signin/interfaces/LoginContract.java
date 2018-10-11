package com.yanxiu.gphone.jiaoyan.module.signin.interfaces;

import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBaseView;

/**
 * Created by Hu Chao on 18/10/10.
 */
public class LoginContract {

    public interface IView extends IYXBaseView<IPresenter> {
        void showLoading();

        void hideLoading();

        void onLoginSuccess();

        void onLoginFail(String errorMsg);
    }

    public interface IPresenter extends IYXBasePresenter {
        void login(String username, String password);

        void verifyCode(String phone, String code);

    }
}
