package com.test.yanxiu.common_base.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.yanxiu.common_base.ui.customize.PublicLoadLayout;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.base.basemvp.YXBaseMvpFragment;

/**
 * Created by Hu Chao on 18/9/28.
 */
public abstract class JYBaseFragment<P extends IYXBasePresenter> extends YXBaseMvpFragment<P> implements JYBaseBusiness {

    protected PublicLoadLayout mCommonLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            initData(bundle);
        }
        mCommonLayout = new PublicLoadLayout(getContext());
        mCommonLayout.setErrorLayoutFullScreen();
        mCommonLayout.setContentView(bindLayout());
        initView(savedInstanceState, mCommonLayout);
        initListener();
        doBusiness();
        mCommonLayout.setRetryButtonOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRetryClick();
            }
        });
        return mCommonLayout;
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
    private boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 200) {
            lastClick = now;
            return false;
        }
        return true;
    }

    @Override
    public void onClick(final View view) {
        if (!isFastClick()) onWidgetClick(view);
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
