package com.yanxiu.gphone.jiaoyan.business.mine;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.lib.yx_basic_library.util.logger.YXLogger;

/**
 * Created by Cai Lei on 2018/10/12.
 */
@Route(path = RoutePathConfig.Mine_Change_Pwd_Step2_Activity)
public class MineChangePwdStep2Activity extends MineChangePwdActivity_Base {
    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        super.initView(savedInstanceState, contentView);

        text_input_layout_phone.setHint("新的手机号");
        text_input_layout_code.setHint("新手机号验证码");

        btn_submit.setText("提交");
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
            setResult(RESULT_ALL_STEPS_DONE);
            finish();
        }
    }
}
