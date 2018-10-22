package com.yanxiu.gphone.jiaoyan.business.course.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.data.RouteCourseCategoryData;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.base.JyBaseRecyclerFragment;
import com.yanxiu.gphone.jiaoyan.business.course.adapter.CourseAdapter;
import com.yanxiu.gphone.jiaoyan.business.course.interfaces.CourseTopicContract;
import com.yanxiu.gphone.jiaoyan.business.course.presenter.CourseTopicPresenter;

import java.util.List;

/**
 * Created by Hu Chao on 18/10/22.
 */
@Route(path = RoutePathConfig.Course_Category_Fragment)
public class CourseCategoryFragment extends JyBaseRecyclerFragment<CourseTopicContract.IPresenter> implements CourseTopicContract.IView {

    private String mId;

    @Override
    public void initData(@NonNull Bundle bundle) {
        RouteCourseCategoryData data = (RouteCourseCategoryData) bundle.getSerializable(RoutePathConfig.Course_Category_Fragment);
        if (data != null) {
            mId = data.getID();
        }
    }

    @Override
    protected BaseQuickAdapter initAdapter() {
        mAdapter = new CourseAdapter();
        return mAdapter;
    }

    @Override
    protected CourseTopicContract.IPresenter initPresenterImpl() {
        return new CourseTopicPresenter(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    protected boolean pullRefreshEnabled() {
        return true;
    }

    @Override
    protected boolean loadingMoreEnabled() {
        return true;
    }

    @Override
    public void onRequestSuccess(boolean isRefresh, int total, List datas) {
        super.onRequestSuccess(isRefresh, total, datas);
        if (isRefresh) {
            mAdapter.removeAllHeaderView();
            String[] urls = getResources().getStringArray(R.array.url1);
            int index = (Integer.parseInt(mId) - 1) % urls.length;
            ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.course_adjust_size_imageview, mRecyclerView, false);
            Glide.with(getContext())
                    .load(urls[index])
                    .into(imageView);
            mAdapter.addHeaderView(imageView);
        }
    }

}
