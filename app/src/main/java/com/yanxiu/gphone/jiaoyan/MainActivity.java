package com.yanxiu.gphone.jiaoyan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.ui.base.JYBaseActivity;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

@Route(path = RoutePathConfig.App_Main)
public class MainActivity extends JYBaseActivity {

    private NaviFragmentFactory mFragmentFactory;

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        mFragmentFactory = new NaviFragmentFactory(savedInstanceState, getSupportFragmentManager());
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getDefaultStyleToolbar().addTitleText("title").apply();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mFragmentFactory.onSaveInstanceState(outState);
    }

}
