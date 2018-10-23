package com.test.yanxiu.common_base.base.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.yanxiu.common_base.customize.PublicLoadLayout;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.base.basemvp.YXBaseMvpFragment;
import com.yanxiu.lib.yx_basic_library.util.logger.YXLogger;

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
        } else {
            initData(Bundle.EMPTY);
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

    public void showEmptyView() {
        // todo : cailei 这个应该也相当通用
        Toast.makeText(getContext(), "应该添加一个空界面", Toast.LENGTH_LONG);
    }

    public void onRetryClick() {

    }
}
