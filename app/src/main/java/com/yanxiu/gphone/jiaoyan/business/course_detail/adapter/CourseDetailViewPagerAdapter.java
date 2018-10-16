package com.yanxiu.gphone.jiaoyan.business.course_detail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * 课程详情页的viewpager的适配器
 * Created by 戴延枫 on 2018/10/11.
 */

public class CourseDetailViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mTitleList;
    private ArrayList<Fragment> mFragmentList;

    public CourseDetailViewPagerAdapter(FragmentManager fragmentManager, ArrayList<String> titleList, ArrayList<Fragment> fragmentList) {
        super(fragmentManager);
        mTitleList = titleList;
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

}
