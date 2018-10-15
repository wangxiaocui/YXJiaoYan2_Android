package com.yanxiu.gphone.jiaoyan.business.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.test.yanxiu.common_base.utils.checkLogin.AvoidOnResult;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.lib.yx_basic_library.util.logger.YXLogger;

/**
 * Created by Cai Lei on 2018/10/12.
 */
@Route(path = RoutePathConfig.Mine_Change_Pwd_Step1_Activity)
public class MineChangePwdStep1Activity extends MineChangePwdActivity_Base {
    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        super.initView(savedInstanceState, contentView);

        text_input_layout_phone.setHint("当前绑定手机号");
        text_input_layout_phone.getEditText().setText("130****0268");
        text_input_layout_phone.getEditText().setEnabled(false);

        text_input_layout_code.setHint("旧手机号验证码");

        btn_submit.setText("下一步");
    }

    @Override
    public void initListener() {
        super.initListener();
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {
        super.doBusiness();
    }

    @Override
    public void onWidgetClick(View view) {
        super.onWidgetClick(view);
        if (view == btn_submit) {
            goNextStep();
        }
    }

    private void goNextStep() {
        // 1, 校验验证码
        RouteUtils.startActivityForResult(this, RoutePathConfig.Mine_Change_Pwd_Step2_Activity, new AvoidOnResult.Callback() {
            @Override
            public void onActivityResult(int resultCode, Intent data) {
                switch (resultCode) {
                    case RESULT_ALL_STEPS_DONE:
                        finish();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
