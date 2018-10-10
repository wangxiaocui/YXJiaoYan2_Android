package com.yanxiu.gphone.jiaoyan.module.signin.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.module.signin.R;
import com.yanxiu.gphone.jiaoyan.module.signin.util.CountDownUtil;

/**
 * 验证码登录
 * Created by Hu Chao on 18/10/9.
 */
@Route(path = RoutePathConfig.SIGNIN_LOGIN_BY_CODE_ACTIVITY)
public class LoginByCodeActivity extends LoginActivity {

    private CountDownUtil mCountDownUtil;
    private TextView tv_title;
    private TextInputLayout text_input_layout_accout;
    private TextInputLayout text_input_layout_password;

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        super.initView(savedInstanceState, contentView);
        tv_title = contentView.findViewById(R.id.tv_title);
        tv_title.setText("手机登录");

        text_input_layout_accout = contentView.findViewById(R.id.text_input_layout_accout);
        text_input_layout_accout.setHint("注册时留下的手机号");
        text_input_layout_password = contentView.findViewById(R.id.text_input_layout_password);
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
            mCountDownUtil = new CountDownUtil(tv_get_code, 10 * 1000, 1000);
            mCountDownUtil.start();
        } else if (view.getId() == R.id.tv_login_type) {
            finish();
        } else if (view.getId() == R.id.tv_register) {

        }
    }

    @Override
    protected void refreshSignBtnState() {
        super.refreshSignBtnState();
        tv_get_code.setEnabled(!TextUtils.isEmpty(et_account.getText()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownUtil != null) {
            mCountDownUtil.cancel();
        }
    }

}