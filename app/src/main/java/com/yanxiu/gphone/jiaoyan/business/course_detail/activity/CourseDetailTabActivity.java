package com.yanxiu.gphone.jiaoyan.business.course_detail.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.data.CourseDetailTabData;
import com.test.yanxiu.common_base.utils.TabLayoutUtil;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course_detail.adapter.CourseDetailViewPagerAdapter;
import com.yanxiu.gphone.jiaoyan.business.course_detail.fragment.DirectoryFragment;
import com.yanxiu.gphone.jiaoyan.business.course_detail.fragment.EvaluationFragment;
import com.yanxiu.gphone.jiaoyan.business.course_detail.fragment.IntroductionFragment;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

import java.util.ArrayList;

@Route(path = RoutePathConfig.App_Course_Detail_Tab)
public class CourseDetailTabActivity extends JYBaseActivity {

    private CourseDetailTabData mData;

    private TabLayout tablayout;
    private ViewPager viewpager;
    private IntroductionFragment introductionFragment;
    private DirectoryFragment directoryFragment;
    private EvaluationFragment evaluationFragment;

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {
        mData = (CourseDetailTabData) bundle.getSerializable(RoutePathConfig.App_Course_Detail_Tab);

    }

    @Override
    public int bindLayout() {
        return R.layout.course_detail_tab_activity;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        initTab();
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getJyDefaultToolbar();
    }

    private void initTab() {
        tablayout = findViewById(R.id.tablayout);
        viewpager = findViewById(R.id.viewpager);

        introductionFragment = new IntroductionFragment();
        directoryFragment = new DirectoryFragment();
        evaluationFragment = new EvaluationFragment();

        ArrayList<String> titleDatas = new ArrayList<>();
        titleDatas.add("介绍");
        titleDatas.add("目录");
        titleDatas.add("评价");
        ArrayList<Fragment> fragmentList = new ArrayList();
        fragmentList.add(introductionFragment);
        fragmentList.add(directoryFragment);
        fragmentList.add(evaluationFragment);
        CourseDetailViewPagerAdapter adapter = new CourseDetailViewPagerAdapter(getSupportFragmentManager(), titleDatas, fragmentList);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
        TabLayoutUtil.setTabLayoutDrivider(tablayout);

        if (mData != null && mData.getTabposition() > 0) {
            if (viewpager.getChildCount() <= (mData.getTabposition() - 1)) {
                viewpager.setCurrentItem(mData.getTabposition() - 1);
            }
        }
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


}
