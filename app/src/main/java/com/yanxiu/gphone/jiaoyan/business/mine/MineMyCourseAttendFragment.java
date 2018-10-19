package com.yanxiu.gphone.jiaoyan.business.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragment;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.yanxiu.gphone.jiaoyan.business.mine.adapter.MineMyCourseAttendAdapter;
import com.yanxiu.gphone.jiaoyan.business.mine.interfaces.MineMyCourseAttendContract;
import com.yanxiu.gphone.jiaoyan.business.mine.presenter.MineMyCourseAttendPresenter;

import java.util.List;

/**
 * Created By cailei on 2018/10/18
 */
public class MineMyCourseAttendFragment extends BaseRecyclerFragment<MineMyCourseAttendContract.IPresenter> implements MineMyCourseAttendContract.IView {

    protected MineMyCourseAttendAdapter mAdapter;

    @Override
    protected BaseAdapter initAdapter() {
        mAdapter = new MineMyCourseAttendAdapter(getContext());
        return mAdapter;
    }

    /**
     * 初始化数据
     *
     * @param bundle 传递过来的 bundle
     */
    @Override
    public void initData(@NonNull Bundle bundle) {

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
        return "10";
        // return String.valueOf(mAdapter.getData(mAdapter.getItemCount() - 1).getID());
    }
    // endregion mvp
}
