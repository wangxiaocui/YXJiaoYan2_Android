package com.test.yanxiu.common_base.db;

import android.text.TextUtils;

import com.test.yanxiu.common_base.bean.UserInfoBean;

/**
 * 用户信息管理类
 * Created by 戴延枫 on 2018/8/16.
 */

public class UserInfoManager {

    private static UserInfoManager instance;

    public static UserInfoManager getInstance() {
        if (instance == null) {
            instance = new UserInfoManager();
        }
        return instance;
    }

    private UserInfoManager() {

    }
    //region token

    private String token;
    private String passPort;

    public String getToken() {
        if (TextUtils.isEmpty(token))
            token = UserInfoSpManager.getToken();
        return token;
    }

    public void setToken(String token) {
        UserInfoSpManager.saveToken(token);
        this.token = token;
    }
    //endregion token

    //region userInfo

    private UserInfoBean mUserInfoBean;

    public UserInfoBean getUserInfo() {
        if (mUserInfoBean == null) {
            mUserInfoBean = UserInfoSpManager.getUserInfo();
        }
        return mUserInfoBean;
    }

    /**
     * 设置用户信息并保存，同时保存im信息
     * 只在登录时调用
     *
     * @param userInfoBean
     */
    public void setUserInfo(UserInfoBean userInfoBean) {
        UserInfoSpManager.saveUserInfo(userInfoBean);
        mUserInfoBean = userInfoBean;
    }


    /**
     * 更新用户信息，但不更新im相关信息
     *
     * @param mUserInfoBean
     */
    public void updateUserInfo(UserInfoBean mUserInfoBean) {
        UserInfoSpManager.saveUserInfo(mUserInfoBean);
        this.mUserInfoBean = mUserInfoBean;
    }

    /**
     * 检查用户是否完整
     *
     * @return true 完整； false: 不完整
     */
    public boolean checkUserStatus() {
        getUserInfo();
        if (mUserInfoBean == null || TextUtils.isEmpty(getToken()))
            return false;
        return true;
    }
    //endregion userinfo

    /**
     * 清空用户登陆的所有缓存信息
     * <p>
     * 调用该方法后，务必调用重新登录逻辑。
     */
    public void clearLoginInfo() {
        setUserInfo(null);
        setToken(null);
    }
    //region 上次登录账号信息

    /**
     * 记录最近成功登录的账号
     *
     * @param account 最近登录的账号
     */
    public void setLastAccount(String account) {
        UserInfoSpManager.saveLastAccount(account);
    }

    /**
     * 获取平台id
     *
     * @return platid
     */
    public String getLastAccount() {
        return UserInfoSpManager.getLastAccount();
    }
    //endregion 上次登录账号信息
}
