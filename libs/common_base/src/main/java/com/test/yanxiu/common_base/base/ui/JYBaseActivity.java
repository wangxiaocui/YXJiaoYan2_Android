package com.test.yanxiu.common_base.base.ui;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.yanxiu.common_base.R;
import com.test.yanxiu.common_base.base.ui.toolbar.CommonToolbar;
import com.test.yanxiu.common_base.base.ui.toolbar.Style;
import com.test.yanxiu.common_base.customize.PublicLoadLayout;
import com.yanxiu.lib.yx_basic_library.YXBaseActivity;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

import java.lang.reflect.Field;

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

    // region 配置特定的tablayout样式

    // 用于 "我的课程"， "我的证书"
    public void configJyTablayout001(TabLayout tablayout) {
        tablayout.setSelectedTabIndicator(R.drawable.common_indicator_horizontal);
        wrapTabIndicatorToTitle(tablayout, 0, 25);
        setDivider(tablayout);
    }

    public void wrapTabIndicatorToTitle(TabLayout tabLayout, int externalMargin, int internalMargin) {
        View tabStrip = tabLayout.getChildAt(0);
        if (tabStrip instanceof ViewGroup) {
            ViewGroup tabStripGroup = (ViewGroup) tabStrip;
            tabStripGroup.setClipChildren(false);
            int childCount = ((ViewGroup) tabStrip).getChildCount();
            for (int i = 0; i < childCount; i++) {
                ViewGroup tabView = (ViewGroup) tabStripGroup.getChildAt(i);
                TextView tv = (TextView)tabView.getChildAt(1);

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tv.getLayoutParams();
                params.leftMargin = 0;
                params.rightMargin = 0;
                tv.setLayoutParams(params);
                tv.setPadding(0,0,0,0);


                //set minimum width to 0 for instead for small texts, indicator is not wrapped as expected
                tabView.setMinimumWidth(0);
                tabView.setClipChildren(false);

                // set padding to 0 for wrapping indicator as title
                tabView.setPadding(0, tabView.getPaddingTop(), 0, tabView.getPaddingBottom());
                // setting custom margin between tabs
                if (tabView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) tabView.getLayoutParams();
                    if (i == 0) {
                        // left
                        settingMargin(layoutParams, externalMargin, internalMargin);
                    } else if (i == childCount - 1) {
                        // right
                        settingMargin(layoutParams, internalMargin, externalMargin);
                    } else {
                        // internal
                        settingMargin(layoutParams, internalMargin, internalMargin);
                    }
                }
            }

            tabLayout.requestLayout();
        }
    }

    private void settingMargin(ViewGroup.MarginLayoutParams layoutParams, int start, int end) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layoutParams.setMarginStart(start);
            layoutParams.setMarginEnd(end);
            layoutParams.leftMargin = start;
            layoutParams.rightMargin = end;
        } else {
            layoutParams.leftMargin = start;
            layoutParams.rightMargin = end;
        }
    }

    private void setDivider(TabLayout tabLayout) {
        LinearLayout ll = (LinearLayout) tabLayout.getChildAt(0);
        ll.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        ll.setDividerPadding(25); // (tablayout height - divider height) * 0.5
        ll.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.common_divider_vertical));
    }
    // endregion

}
