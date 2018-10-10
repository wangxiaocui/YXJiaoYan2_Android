package com.yanxiu.gphone.jiaoyan.module.signin.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.yanxiu.gphone.jiaoyan.module.signin.R;
import com.yanxiu.gphone.jiaoyan.module.signin.util.CountDownUtil;

/**
 * 验证码登录
 * Created by Hu Chao on 18/10/9.
 */
@Route(path = RoutePathConfig.SIGNIN_LOGIN_BY_CODE_ACTIVITY)
public class LoginByCodeActivity extends LoginActivity {

    protected CountDownUtil mCountDownUtil;

    @Override
    protected void initTitle() {
        super.initTitle();
        getDefaultBackStyleToolbar().apply();
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        super.initView(savedInstanceState, contentView);
        tv_title.setText("手机登录");
        text_input_layout_accout.setHint("注册时留下的手机号");
//        text_input_layout_password.setDefaultHintTextColor(ContextCompat.getColorStateList(this, R.color.signin_edit_text_color_selector));
//        text_input_layout_password.setEnabled(false);
        text_input_layout_password.setHint("4位验证码");
        tv_get_code.setVisibility(View.VISIBLE);
        tv_login_type.setText("用密码登录");

    }

    @Override
    public void onWidgetClick(View view) {
        if (view.getId() == R.id.btn_login) {

        } else if (view.getId() == R.id.tv_get_code) {
            getCode();
        } else if (view.getId() == R.id.tv_login_type) {
            finish();
        } else if (view.getId() == R.id.tv_register) {
            RouteUtils.startActivity(RoutePathConfig.SIGNIN_REGISTER_ACTIVITY);
        }
    }

    @Override
    protected void refreshSignBtnState() {
        super.refreshSignBtnState();
        tv_get_code.setEnabled(!TextUtils.isEmpty(et_account.getText()));
    }

    protected void getCode() {
        mCountDownUtil = new CountDownUtil(tv_get_code, 10 * 1000, 1000);
        mCountDownUtil.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownUtil != null) {
            mCountDownUtil.cancel();
        }
    }

}