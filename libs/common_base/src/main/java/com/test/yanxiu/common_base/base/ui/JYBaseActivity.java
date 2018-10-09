package com.test.yanxiu.common_base.base.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.test.yanxiu.common_base.R;
import com.test.yanxiu.common_base.customize.PublicLoadLayout;
import com.test.yanxiu.common_base.base.ui.toolbar.CommonToolbar;
import com.test.yanxiu.common_base.base.ui.toolbar.Style;
import com.yanxiu.lib.yx_basic_library.YXBaseActivity;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

/**
 * Created by Hu Chao on 18/9/28.
 */
public abstract class JYBaseActivity<P extends IYXBasePresenter> extends YXBaseActivity<P> implements JYBaseBusiness {

    protected PublicLoadLayout mCommonLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            initData(bundle);
        }
        setBaseView(bindLayout());
        initTitle();
        initView(savedInstanceState, mCommonLayout);
        initListener();
        doBusiness();
    }

    private void setBaseView(@LayoutRes int layoutId) {
        mCommonLayout = new PublicLoadLayout(this);
        mCommonLayout.setContentView(layoutId);
        mCommonLayout.setErrorLayoutFullScreen();
        setContentView(mCommonLayout);
    }

    protected void initTitle() {

    }

    /**
     * 获取一个默认样式颜色的toolbar
     */
    protected CommonToolbar.Builder getDefaultStyleToolbar() {
        return new CommonToolbar.Builder(this)
                .setStatusBarStyle(Style.DEFAULT)
                .setBackgroundColorRes(R.color.colorPrimary);
    }

    /**
     * 上次点击时间
     */
    private long lastClick = 0;

    /**
     * 判断是否快速点击
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    protected boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 200) {
            lastClick = now;
            return false;
        }
        return true;
    }

    @Override
    public final void onClick(final View view) {
        if (!isFastClick()) {
            onWidgetClick(view);
        }
    }

    public void showLoading() {
        mCommonLayout.showLoadingView();
    }

    public void hideLoading() {
        mCommonLayout.hiddenLoadingView();
    }

    public void hideExceptionView() {
        mCommonLayout.finish();
    }

    public void showError(String error) {
        mCommonLayout.showOtherErrorView(error);
    }

    public void showNetError() {
        mCommonLayout.showNetErrorView();
    }

}