package com.yanxiu.gphone.jiaoyan.business.course.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseFragment;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course.adapter.CoursePagerAdapter;
import com.yanxiu.gphone.jiaoyan.business.course.bean.CourseCategoryBean;
import com.yanxiu.gphone.jiaoyan.business.course.interfaces.CourseContract;
import com.yanxiu.gphone.jiaoyan.business.course.presenter.CoursePresenter;

import java.util.List;

/**
 * Created by Hu Chao on 18/10/11.
 */
@Route(path = RoutePathConfig.Course_Fragment)
public class CourseFragment extends JYBaseFragment<CourseContract.IPresenter> implements CourseContract.IView {


    private TextView tv_search;
    private TabLayout tab_layout_category;
    private ViewPager view_pager_content;
    private CoursePagerAdapter mCoursePagerAdapter;

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.course_fragment;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        tv_search = contentView.findViewById(R.id.tv_search);
        tab_layout_category = contentView.findViewById(R.id.tab_layout_category);
        view_pager_content = contentView.findViewById(R.id.view_pager_content);
    }

    @Override
    public void initListener() {
        tv_search.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {
        mPresenter.getCategory();
    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                RouteUtils.startActivity(RoutePathConfig.Search_Activity);
                break;
        }
    }

    @Override
    protected CourseContract.IPresenter initPresenterImpl() {
        return new CoursePresenter(this);
    }

    @Override
    public void onSuccess(List<CourseCategoryBean> categoryBeanList) {
        mCoursePagerAdapter = new CoursePagerAdapter(getFragmentManager());
        mCoursePagerAdapter.setCategorys(categoryBeanList);
        view_pager_content.setAdapter(mCoursePagerAdapter);
        tab_layout_category.setupWithViewPager(view_pager_content);
    }

    @Override
    public void onFail(String errorMsg) {

    }
}
