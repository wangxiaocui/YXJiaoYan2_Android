package com.test.yanxiu.common_base.base.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by Hu Chao on 18/9/28.
 */
public interface JYBaseBusiness extends View.OnClickListener {

    /**
     * 绑定布局
     *
     * @return 布局 Id
     */
    int bindLayout();

    /**
     * 初始化数据
     *
     * @param bundle 传递过来的 bundle
     */
    void initData(@NonNull final Bundle bundle);

    /**
     * 初始化 view
     */
    void initView(final Bundle savedInstanceState, final View contentView);

    /**
     * 初始化 监听
     */
    void initListener();

    /**
     * 视图点击事件
     *
     * @param view 视图
     */
    void onWidgetClick(final View view);

    /**
     * 业务操作
     */
    void doBusiness();

}
