package com.yanxiu.gphone.jiaoyan.module.signin.presenter;

import com.yanxiu.gphone.jiaoyan.module.signin.interfaces.LoginContract;
import com.yanxiu.lib.yx_basic_library.base.basemvp.YXBasePresenterImpl;

/**
 * 登录逻辑处理
 * Created by Hu Chao on 18/10/10.
 */
public class LoginPresenter extends YXBasePresenterImpl<LoginContract.IView> implements LoginContract.IPresenter {
    public LoginPresenter(LoginContract.IView view) {
        super(view);
    }

    @Override
    public void login(String username, String password) {
        mView.showLoading();
        signIn(username, password);
    }

    @Override
    public void verifyCode(String phone, String code) {
        mView.onLoginSuccess();
    }

    /**
     * 1.请求token
     *
     * @param username
     * @param password
     */
    private void signIn(String username, String password) {
        mView.onLoginSuccess();
//        mLoginName = username;
//        SignInRequest request = new SignInRequest();
//        request.loginName = username;
//        request.password = YXCryptoDecryptUtil.md5(password);
//        addRequest(request, SignInResponse.class, new IYXHttpCallback<SignInResponse>() {
//            @Override
//            public void onRequestCreated(Request request) {
//
//            }
//
//            @Override
//            public void onSuccess(YXRequestBase request, SignInResponse ret) {
//                if (ret.getCode() == 0) {
//                    //save token;
//                    UserInfoManager.getInstance().setToken(ret.getToken());
//                    UserInfoManager.getInstance().setPassPort(ret.getPassport());
//                    requestClassData();
//                } else {
//                    showLoginFial(getErrorMsgFromResponse(ret));
//                }
//            }
//
//            @Override
//            public void onFail(YXRequestBase request, Error error) {
//                showLoginFial(error.getMessage());
//            }
//        });
    }


    /**
     * 3.获取用户信息
     */
    private void getUserInfo() {
    }

    /**
     * 登录完成，执行登录完成之后的逻辑
     */
    private void checkLoginComplete() {
//        mView.hideLoading();
//        //处理 im 信息不全时的登录
//        if (!ImConfig.isHasInitialazed()) {
//            showLoginFial(null);
//            return;
//        }
//        PushManager.getInstance().bindAlias(FaceShowApplication.getInstance(), String.valueOf(UserInfoManager.getInstance().getUserInfo().getUserId()));
//        //开启mqtt
//        UserInfoManager.getInstance().setLastAccount(mLoginName);
//        mView.onLoginSuccess();
//        appUsedDetailRequest();
    }


//    private String getErrorMsgFromResponse(FaceShowBaseResponse ret) {
//        if (ret != null && ret.getError() != null) {
//            return ret.getError().getMessage();
//        } else {
//            return null;
//        }
//    }

    private void showLoginFial(String errorMsg) {
//        mView.hideLoading();
//        UserInfoManager.getInstance().clearLoginInfo();
//        if (!TextUtils.isEmpty(errorMsg)) {
//            mView.onLoginFail(errorMsg);
//        } else {
//            mView.onLoginFail("登录失败");
//        }
    }


}
