package com.yanxiu.gphone.jiaoyan.business.course_detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.customize.view.StarProgressBar;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.main.NaviFragmentFactory;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.util.logger.YXLogger;

@Route(path = RoutePathConfig.App_Course_Detail)
public class CourseDetailActivity extends JYBaseActivity {

    private NaviFragmentFactory mFragmentFactory;

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.course_detail_activity;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        mFragmentFactory = new NaviFragmentFactory(savedInstanceState, getSupportFragmentManager());
        final TextView text = findViewById(R.id.text);
        StarProgressBar bar = findViewById(R.id.good_progress_view2);
        bar.setProgressIntValue(60);
        bar.setOnStarChangeListener(new StarProgressBar.OnStarChangeListener() {

            @Override
            public void onStarChange(float mark) {
//                text.setText(mark + "分");
                YXLogger.e("dyf",mark + "分");
            }
        });
    }

    @Override
    protected void initTitle() {
        super.initTitle();
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mFragmentFactory.onSaveInstanceState(outState);
    }

}
