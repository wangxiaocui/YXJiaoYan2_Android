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
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.base.JyBaseRecyclerFragment;
import com.yanxiu.gphone.jiaoyan.business.course.adapter.CourseAdapter;
import com.yanxiu.gphone.jiaoyan.business.course.interfaces.CourseTopicContract;
import com.yanxiu.gphone.jiaoyan.business.course.presenter.CourseTopicPresenter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Hu Chao on 18/10/22.
 */
@Route(path = RoutePathConfig.Course_Topic_Fragment)
public class CourseTopicFragment extends JyBaseRecyclerFragment<CourseTopicContract.IPresenter> implements CourseTopicContract.IView {

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    protected BaseQuickAdapter initAdapter() {
        return new CourseAdapter();
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
            List<String> urls = Arrays.asList(getResources().getStringArray(R.array.url3));
            for (int i = 0; i < urls.size(); i++) {
                ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.course_adjust_size_imageview, mRecyclerView, false);
                Glide.with(getContext())
                        .load(urls.get(i))
                        .into(imageView);
                mAdapter.addHeaderView(imageView);
            }
        }
    }
}
