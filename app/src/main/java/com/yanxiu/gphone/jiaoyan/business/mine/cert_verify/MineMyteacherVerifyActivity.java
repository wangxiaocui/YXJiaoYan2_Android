package com.yanxiu.gphone.jiaoyan.business.mine.cert_verify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

/**
 * "教师资格证"页
 * Created by lufengqing on 2018/10/23.
 */
@Route(path = RoutePathConfig.Mine_My_teacher_Verify_Activity)
public class MineMyteacherVerifyActivity extends JYBaseActivity {
    @Override
    public int bindLayout() {
        return 0;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }
}
