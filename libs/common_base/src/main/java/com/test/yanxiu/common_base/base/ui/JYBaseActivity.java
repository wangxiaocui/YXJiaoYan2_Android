package com.test.yanxiu.common_base.base.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.test.yanxiu.common_base.R;
import com.test.yanxiu.common_base.base.ui.toolbar.CommonToolbar;
import com.test.yanxiu.common_base.base.ui.toolbar.Style;
import com.test.yanxiu.common_base.customize.PublicLoadLayout;
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
        } else {
            initData(Bundle.EMPTY);
        }
        setBaseView(bindLayout());
        initTitle();
        initView(savedInstanceState, mCommonLayout);
        initListener();
        doBusiness();
        mCommonLayout.setRetryButtonOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRetryClick();
            }
        });
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
        CommonToolbar.Builder builder = new CommonToolbar.Builder(this).setStatusBarStyle(Style.DEFAULT);
        return builder;
    }

    // JY默认的左侧样式 "< 返回"
    protected CommonToolbar getJyDefaultToolbar() {
        CommonToolbar.Builder builder = new CommonToolbar.Builder(this).setStatusBarStyle(Style.DEFAULT);
        CommonToolbar toolbar = builder.apply();

        View v = this.getLayoutInflater().inflate(R.layout.common_left_navi_back, toolbar, false);
        toolbar.addLeftView(View.generateViewId(), v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        return toolbar;
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

    public void showLoadingView() {
        mCommonLayout.showLoadingView();
    }

    public void hideLoadingView() {
        mCommonLayout.hiddenLoadingView();
    }

    public void hideExceptionView() {
        mCommonLayout.finish();
    }

    public void showErrorView(String error) {
        mCommonLayout.showOtherErrorView(error);
    }

    public void showNetErrorView() {
        mCommonLayout.showNetErrorView();
    }

    public void onRetryClick() {

    }
}
