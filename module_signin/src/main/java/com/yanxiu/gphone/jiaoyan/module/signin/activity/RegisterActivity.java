package com.yanxiu.gphone.jiaoyan.module.signin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.yanxiu.gphone.jiaoyan.module.signin.R;
import com.yanxiu.lib.yx_basic_library.util.YXToastUtil;

/**
 * 注册页面
 * Created by Hu Chao on 18/10/10.
 */
@Route(path = RoutePathConfig.SIGNIN_REGISTER_ACTIVITY)
public class RegisterActivity extends LoginByCodeActivity {

    private LinearLayout tv_register_notice;
    private TextView tv_agreement;
    private TextView tv_privacy;

    @Override
    public int bindLayout() {
        return R.layout.signin_activity_login;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        super.initView(savedInstanceState, contentView);
        tv_title.setText("注册");
        text_input_layout_accout.setHint("注册时使用的手机号");
        tv_login_type.setVisibility(View.GONE);
        tv_register.setVisibility(View.GONE);
        btn_login.setText("同意协议并注册");

        tv_register_notice = contentView.findViewById(R.id.tv_register_notice);
        tv_register_notice.setVisibility(View.VISIBLE);
        tv_agreement = contentView.findViewById(R.id.tv_agreement);
        tv_privacy = contentView.findViewById(R.id.tv_privacy);
    }

    @Override
    public void initListener() {
        super.initListener();
        tv_agreement.setOnClickListener(this);
        tv_privacy.setOnClickListener(this);
    }

    @Override
    public void onWidgetClick(View view) {
        super.onWidgetClick(view);
        if (view.getId() == R.id.tv_agreement) {
            YXToastUtil.showToast("跳转用户协议");
        } else if (view.getId() == R.id.tv_privacy) {
            YXToastUtil.showToast("跳转隐私政策");
        }
    }

    @Override
    protected void onLoginClick() {
        mPresenter.verifyCode(et_account.getText().toString(), et_password.getText().toString());
    }

    @Override
    public void onLoginSuccess() {
        RouteUtils.startActivity(RoutePathConfig.SIGNIN_SET_PASSWORD_ACTIVITY);
    }
}