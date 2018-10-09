package com.yanxiu.gphone.jiaoyan;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;

import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.base.ui.toolbar.AppBarHelper;
import com.test.yanxiu.common_base.base.ui.toolbar.Style;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

public class WelcomeActivity extends JYBaseActivity {

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

    }

    @Override
    protected void initTitle() {
        super.initTitle();
        AppBarHelper.with(this).setStatusBarStyle(Style.TRANSPARENT);
    }

    @Override
    public void initListener() {
    }

    @Override
    public void doBusiness() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RouteUtils.startActivity(RoutePathConfig.App_Main);
                finish();
            }
        }, 1000);
    }

    @Override
    public void onWidgetClick(View view) {

    }

}
