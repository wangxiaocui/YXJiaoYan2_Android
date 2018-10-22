package com.yanxiu.gphone.jiaoyan.business.course.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course.fragment.CourseTopicFragment;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

/**
 * Created by Hu Chao on 18/10/22.
 */
@Route(path = RoutePathConfig.Course_Topic_Activity)
public class CourseTopicActivity extends JYBaseActivity {
    @Override
    public int bindLayout() {
        return R.layout.course_topic_activity;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getJyDefaultToolbar();
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        CourseTopicFragment fragment = new CourseTopicFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_topic_container, fragment).commitAllowingStateLoss();
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
