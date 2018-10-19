package com.yanxiu.gphone.jiaoyan.business.mine;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.module.signin.util.CountDownUtil;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

public abstract class MineChangePwdActivity_Base extends JYBaseActivity {
    protected final static int RESULT_ALL_STEPS_DONE = 100;

    protected TextInputLayout text_input_layout_phone;
    protected TextInputLayout text_input_layout_code;
    protected TextView tv_get_code;
    protected Button btn_submit;

    protected CountDownUtil mCountDownUtil;
    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.mine_activity_change_pwd;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        text_input_layout_phone = contentView.findViewById(R.id.text_input_layout_phone);
        text_input_layout_code = contentView.findViewById(R.id.text_input_layout_code);
        tv_get_code = contentView.findViewById(R.id.tv_get_code);
        btn_submit = contentView.findViewById(R.id.btn_submit);
    }

    @Override
    public void initListener() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                refreshSubmitButtonState();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        text_input_layout_phone.getEditText().addTextChangedListener(textWatcher);
        text_input_layout_code.getEditText().addTextChangedListener(textWatcher);

        tv_get_code.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        if (view == tv_get_code) {
            mCountDownUtil = new CountDownUtil(tv_get_code, 60 * 1000, 1000);
            mCountDownUtil.start();
        }
    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getJyDefaultToolbar();
    }

    private void refreshSubmitButtonState() {
        btn_submit.setEnabled(true);

        if (TextUtils.isEmpty(text_input_layout_phone.getEditText().getText())) {
            btn_submit.setEnabled(false);
        }

        if (TextUtils.isEmpty(text_input_layout_code.getEditText().getText())) {
            btn_submit.setEnabled(false);
        }
    }
}
