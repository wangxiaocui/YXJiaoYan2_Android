package com.yanxiu.gphone.jiaoyan.business.mine;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Created By cailei on 2018/10/18
 */
@Route(path = RoutePathConfig.Mine_My_Course_Activity)
public class MineMyCourseActivity extends JYBaseActivity {
    private List<String> mTabNames = Arrays.asList("已加入", "已预约");
    private List<Fragment> mFragments =
            Arrays.<Fragment>asList(new MineMyCourseAttendFragment(), new MineMyCourseReserveFragment());

    private TabLayout tablayout;
    private ViewPager viewpager;

    @Override
    public int bindLayout() {
        return R.layout.mine_activity_my_course;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        tablayout = findViewById(R.id.tablayout);
        for (int i = 0; i < mTabNames.size(); i++) {
            TabLayout.Tab tab = tablayout.newTab();
            tab.setText(mTabNames.get(i));
            tablayout.addTab(tab);
        }

        viewpager = findViewById(R.id.viewpager);
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTabNames.get(position);
            }
        };

        viewpager.setAdapter(fragmentPagerAdapter);
        tablayout.setupWithViewPager(viewpager);
        tablayout.getTabAt(0).select();
        tablayout.setTabIndicatorFullWidth(true);
        //tablayout.getTabSelectedIndicator();
        configJyTablayout001(tablayout);
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

    @Override
    protected void initTitle() {
        super.initTitle();
        getJyDefaultToolbar();
    }
}
