package com.yanxiu.gphone.jiaoyan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.base.ui.toolbar.AppBarHelper;
import com.test.yanxiu.common_base.base.ui.toolbar.Style;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

@Route(path = RoutePathConfig.App_Main)
public class MainActivity extends JYBaseActivity {

    private NaviFragmentFactory mFragmentFactory;
    private BottomNavigationView navigationView;

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
        navigationView = contentView.findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        mFragmentFactory.bindBottomNavigationView(navigationView);

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
