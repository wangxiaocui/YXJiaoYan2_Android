package com.yanxiu.gphone.jiaoyan.module.course;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.ui.base.JYBaseFragment;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;


@Route(path = RoutePathConfig.Course_Fragment)
public class CourseFragment extends JYBaseFragment {
    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.course_fragment;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void doBusiness() {
        showNetErrorView();
    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }
}
