package com.yanxiu.gphone.jiaoyan.business.mine;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.base.ui.toolbar.CommonToolbar;
import com.test.yanxiu.common_base.base.ui.toolbar.Style;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

/**
 * 设置页面，没用mvp
 */
@Route(path = RoutePathConfig.Mine_Setting_Activity)
public class MineSettingActivity extends JYBaseActivity {
    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.mine_setting_activity;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

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
    protected void initTitle() {
        super.initTitle();

        // todo: cailei 需要都调试好了放到Base里去
        new CommonToolbar.Builder(this).setStatusBarStyle(Style.DEFAULT)
                .addLeftIcon(View.generateViewId(), com.test.yanxiu.common_base.R.drawable.selector_back, 20, 20, backListener)
                .addLeftText(View.generateViewId(), "返回", 18, getResources().getColor(R.color.color_007aff), backListener)
                .apply();
    }

    private View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}
