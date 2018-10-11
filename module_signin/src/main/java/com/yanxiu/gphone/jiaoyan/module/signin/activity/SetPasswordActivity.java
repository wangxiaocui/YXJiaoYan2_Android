package com.yanxiu.gphone.jiaoyan.module.signin.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.yanxiu.gphone.jiaoyan.module.signin.R;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.util.YXToastUtil;

/**
 * 设置密码页面
 * Created by Hu Chao on 18/10/10.
 */
@Route(path = RoutePathConfig.SIGNIN_SET_PASSWORD_ACTIVITY)
public class SetPasswordActivity extends JYBaseActivity {

    protected EditText et_password1;
    protected EditText et_password2;
    protected Button btn_ok;

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.signin_activity_set_password;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        et_password1 = contentView.findViewById(R.id.et_password1);
        et_password2 = contentView.findViewById(R.id.et_password2);
        btn_ok = contentView.findViewById(R.id.btn_ok);
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
        et_password1.addTextChangedListener(textWatcher);
        et_password2.addTextChangedListener(textWatcher);
        btn_ok.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        if (view.getId() == R.id.btn_ok) {
            RouteUtils.startActivity(RoutePathConfig.SIGNIN_SET_SUBJECT_ACTIVITY);
        }
    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }

    protected void refreshSignBtnState() {
        if (TextUtils.isEmpty(et_password1.getText()) || TextUtils.isEmpty(et_password2.getText())) {
            btn_ok.setEnabled(false);
        } else {
            btn_ok.setEnabled(true);
        }
    }
}