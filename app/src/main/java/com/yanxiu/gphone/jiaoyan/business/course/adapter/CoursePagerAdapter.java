package com.yanxiu.gphone.jiaoyan.business.course.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.yanxiu.gphone.jiaoyan.business.course.bean.CourseCategoryBean;
import com.yanxiu.gphone.jiaoyan.business.course.fragment.CourseListFragment;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Hu Chao on 18/10/15.
 */
public class CoursePagerAdapter extends FragmentStatePagerAdapter {

    private List<CourseCategoryBean> mCategorys;
    private HashMap<String, CourseListFragment> fragmentHashMap;

    public CoursePagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentHashMap = new HashMap<>();
    }

    public void setCategorys(List<CourseCategoryBean> categorys) {
        this.mCategorys = categorys;
    }

    @Override
    public CourseListFragment getItem(int position) {
        CourseListFragment fragment;
        String id = mCategorys.get(position).getId();
        if (!fragmentHashMap.containsKey(id)) {
            fragment = (CourseListFragment) RouteUtils.getFragmentByPath(RoutePathConfig.Course_List_Fragment);
            fragmentHashMap.put(id, fragment);
        } else {
            fragment = fragmentHashMap.get(id);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mCategorys == null ? 0 : mCategorys.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mCategorys.get(position).getName();
    }
}
