package com.yanxiu.gphone.jiaoyan.business.mine.cert_verify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

/**
 * 我的--->资质认证指南
 * Created by lufengqing on 2018/10/22.
 */
@Route(path = RoutePathConfig.Mine_My_Cert_Verify_Guide_Activity)
public class MineMyCertVerifyGuideActivity extends JYBaseActivity {
    @Override
    public int bindLayout() {
        return R.layout.mine_activity_my_cert_verify_guide;
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
