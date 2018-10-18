package com.yanxiu.gphone.jiaoyan.business.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragment;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.yanxiu.gphone.jiaoyan.R;

import com.yanxiu.gphone.jiaoyan.business.course.adapter.CourseListAdapter;
import com.yanxiu.gphone.jiaoyan.business.course.interfaces.CourseListContract;
import com.yanxiu.gphone.jiaoyan.business.mine.interfaces.MineMyCourseAttendContract;
import com.yanxiu.gphone.jiaoyan.business.mine.presenter.MineMyCourseAttendPresenter;
import com.yanxiu.gphone.jiaoyan.customize.banner.BannerGlideImageLoader;

import java.util.Arrays;
import java.util.List;

/**
 * Created By cailei on 2018/10/18
 */
public class MineMyCourseAttendFragment
        extends BaseRecyclerFragment<MineMyCourseAttendContract.IPresenter>
        implements MineMyCourseAttendContract.IView {
    protected CourseListAdapter mAdapter;



    @Override
    protected BaseAdapter initAdapter() {
        mAdapter = new CourseListAdapter(getContext());
        return mAdapter;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    public void doBusiness() {
        mPresenter.refresh();
    }

    @Override
    protected MineMyCourseAttendContract.IPresenter initPresenterImpl() {
        return new MineMyCourseAttendPresenter(this);
    }

    @Override
    protected boolean pullRefreshEnabled() {
        return true;
    }

    @Override
    protected boolean loadingMoreEnabled() {
        return true;
    }

    // region mvp
    @Override
    public void onRefreshSuccess(int total, List datas) {
        hideLoadingView();
        super.onRefreshSuccess(total, datas);
    }

    @Override
    protected String getLoadMoreOffset() {
        return String.valueOf(mAdapter.getData(mAdapter.getItemCount() - 1).getID());
    }
    // endregion mvp
}
