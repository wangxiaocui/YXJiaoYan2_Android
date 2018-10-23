package com.yanxiu.gphone.jiaoyan.module.signin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.yanxiu.gphone.jiaoyan.module.signin.R;
import com.yanxiu.gphone.jiaoyan.module.signin.interfaces.LoginContract;
import com.yanxiu.gphone.jiaoyan.module.signin.presenter.LoginPresenter;
import com.yanxiu.lib.yx_basic_library.util.YXToastUtil;

/**
 * 登录页面
 * Created by Hu Chao on 18/10/9.
 */
@Route(path = RoutePathConfig.Signin_Login_Activity)
public class LoginActivity extends JYBaseActivity<LoginContract.IPresenter> implements LoginContract.IView {

    protected TextView tv_title;
    protected TextInputLayout text_input_layout_accout;
    protected TextInputLayout text_input_layout_password;
    protected EditText et_account;
    protected EditText et_password;
    protected TextView tv_get_code;
    protected Button btn_login;
    protected TextView tv_login_type;
    protected TextView tv_register;

    @Override
    protected LoginContract.IPresenter initPresenterImpl() {
        return new LoginPresenter(this);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.signin_activity_login;
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getJyDefaultToolbar();
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        tv_title = contentView.findViewById(R.id.tv_title);
        text_input_layout_accout = contentView.findViewById(R.id.text_input_layout_accout);
        text_input_layout_password = contentView.findViewById(R.id.text_input_layout_password);
        et_account = contentView.findViewById(R.id.et_account);
        et_password = contentView.findViewById(R.id.et_password);
        tv_get_code = contentView.findViewById(R.id.tv_get_code);
        btn_login = contentView.findViewById(R.id.btn_login);
        tv_login_type = contentView.findViewById(R.id.tv_login_type);
        tv_register = contentView.findViewById(R.id.tv_register);
        tv_get_code.setVisibility(View.GONE);
        text_input_layout_password.setPasswordVisibilityToggleEnabled(true);
    }

    @Override
    public void initListener() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                refreshSignBtnState();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        et_account.addTextChangedListener(textWatcher);
        et_password.addTextChangedListener(textWatcher);
        tv_get_code.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        tv_login_type.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {
        refreshSignBtnState();
    }

    @Override
    public void onWidgetClick(View view) {
        if (view.getId() == R.id.btn_login) {
            onLoginClick();
        } else if (view.getId() == R.id.tv_login_type) {
            onLoginTypeClick();
        } else if (view.getId() == R.id.tv_register) {
            onRegisterClick();
        }
    }

    protected void refreshSignBtnState() {
        if (TextUtils.isEmpty(et_account.getText()) || TextUtils.isEmpty(et_password.getText())) {
            btn_login.setEnabled(false);
        } else {
            btn_login.setEnabled(true);
        }
    }

    @Override
    public void onLoginSuccess() {
        //todo 判断个人信息必填项是否填写了，未填写，则跳转必填项页面
        if (true) {
            RouteUtils.startActivity(RoutePathConfig.Signin_Set_Required_Info_Activity);
        } else {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void onLoginFail(String errorMsg) {
        YXToastUtil.showToast(errorMsg);
    }

    protected void onLoginClick() {
        mPresenter.login(et_account.getText().toString(), et_password.getText().toString());
    }

    protected void onLoginTypeClick() {
        RouteUtils.startActivity(RoutePathConfig.Signin_Login_By_Code_Activity);
    }

    protected void onRegisterClick() {
        RouteUtils.startActivity(RoutePathConfig.Signin_Register_Activity);
    }
}