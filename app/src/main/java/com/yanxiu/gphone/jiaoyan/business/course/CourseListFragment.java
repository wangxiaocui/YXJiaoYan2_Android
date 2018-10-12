package com.yanxiu.gphone.jiaoyan.business.course;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragment;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.route.RoutePathConfig;

import java.util.List;

/**
 * Created by Hu Chao on 18/10/11.
 */
@Route(path = RoutePathConfig.Course_Fragment)
public class CourseListFragment extends BaseRecyclerFragment<CourseListContract.IPresenter> implements CourseListContract.IView {

    protected CourseListAdapter mAdapter;

    @Override
    protected BaseAdapter initAdapter() {
        mAdapter = new CourseListAdapter(getContext());
        return mAdapter;
    }

    @Override
    protected CourseListContract.IPresenter initPresenterImpl() {
        return new CourseListPresenter(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void doBusiness() {
        showLoadingView();
        mPresenter.refresh();
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
    public void onRefreshSuccess(int total, List datas) {
        hideLoadingView();
        super.onRefreshSuccess(total, datas);
    }

    @Override
    protected String getLoadMoreOffset() {
        return String.valueOf(mAdapter.getData(mAdapter.getItemCount() - 1).getUserId());
    }

}
