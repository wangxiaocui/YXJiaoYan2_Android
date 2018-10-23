package com.yanxiu.gphone.jiaoyan.business.my_wealth.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

/**
 * Created By cailei on 2018/10/23
 */
@Route(path = RoutePathConfig.My_Wealth_Activity)
public class MyWealthActivity extends JYBaseActivity {
    @Override
    public int bindLayout() {
        return R.layout.my_wealth_activity;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new MyWealthFragment())
                .commitAllowingStateLoss();
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

    @Override
    protected void initTitle() {
        super.initTitle();
        getJyDefaultToolbar();
    }
}
