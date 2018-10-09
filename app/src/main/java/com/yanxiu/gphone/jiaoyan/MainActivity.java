package com.yanxiu.gphone.jiaoyan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.base.ui.toolbar.AppBarHelper;
import com.test.yanxiu.common_base.base.ui.toolbar.Style;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

@Route(path = RoutePathConfig.App_Main)
public class MainActivity extends JYBaseActivity {

    private View root_view;
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

        root_view = contentView.findViewById(R.id.root_view);

        mFragmentFactory = new NaviFragmentFactory(savedInstanceState, getSupportFragmentManager());

        navigationView = contentView.findViewById(R.id.bottom_navigation_view);
        navigationView.setItemIconTintList(null);

        //消息添加红点
//        BottomNavigationItemView itemView = (BottomNavigationItemView) ((BottomNavigationMenuView) navigationView.getChildAt(0)).getChildAt(2);
//        messageRedPoint = LayoutInflater.from(navigationView.getContext()).inflate(R.layout.red_point_layout, itemView, false);
//        itemView.addView(messageRedPoint);
//        messageRedPoint.setVisibility(View.VISIBLE);
    }

    public View getRoot_view() {
        return root_view;
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        AppBarHelper.with(this).setStatusBarStyle(Style.TRANSPARENT);
    }

    @Override
    public void initListener() {
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_course:
                        root_view.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.color_ccff00));
                        AppBarHelper.with(MainActivity.this).setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.color_ccff00)).apply();
                        mFragmentFactory.showFragment(0);
                        return true;
                    case R.id.navigation_live:
                        root_view.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.color_ccff00));
                        AppBarHelper.with(MainActivity.this).setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.color_ccff00)).apply();
                        mFragmentFactory.showFragment(1);
                        return true;
                    case R.id.navigation_message:
                        root_view.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.color_ccff00));
                        AppBarHelper.with(MainActivity.this).setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.color_ccff00)).apply();
                        mFragmentFactory.showFragment(2);
                        return true;
                    case R.id.navigation_mine:
                        root_view.setBackground(null);
                        AppBarHelper.with(MainActivity.this).setStatusBarColor(ContextCompat.getColor(MainActivity.this, android.R.color.transparent)).apply();
                        mFragmentFactory.showFragment(3);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void doBusiness() {
        navigationView.setSelectedItemId(R.id.navigation_course + mFragmentFactory.getCurrentIndex());
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
