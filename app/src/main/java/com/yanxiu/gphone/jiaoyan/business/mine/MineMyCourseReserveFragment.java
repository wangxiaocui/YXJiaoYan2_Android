package com.yanxiu.gphone.jiaoyan.business.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.test.yanxiu.common_base.base.ui.JYBaseFragment;
import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragment;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.mine.adapter.MineMyCourseAttendAdapter;
import com.yanxiu.gphone.jiaoyan.business.mine.adapter.MineMyCourseReserveAdapter;
import com.yanxiu.gphone.jiaoyan.business.mine.interfaces.MineMyCourseAttendContract;
import com.yanxiu.gphone.jiaoyan.business.mine.interfaces.MineMyCourseReserveContract;
import com.yanxiu.gphone.jiaoyan.business.mine.presenter.MineMyCourseReservePresenter;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

import java.util.List;

/**
 * Created By cailei on 2018/10/18
 */
public class MineMyCourseReserveFragment
        extends BaseRecyclerFragment<MineMyCourseReserveContract.IPresenter>
        implements MineMyCourseReserveContract.IView {
    protected MineMyCourseReserveAdapter mAdapter;

    @Override
    protected BaseAdapter initAdapter() {
        mAdapter = new MineMyCourseReserveAdapter(getContext());
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
    protected MineMyCourseReserveContract.IPresenter initPresenterImpl() {
        return new MineMyCourseReservePresenter(this);
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
