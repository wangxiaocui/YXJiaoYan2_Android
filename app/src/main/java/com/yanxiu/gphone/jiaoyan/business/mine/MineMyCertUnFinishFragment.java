package com.yanxiu.gphone.jiaoyan.business.mine;

import android.view.View;

import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragment;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.yanxiu.gphone.jiaoyan.business.mine.adapter.MyCertAdapter;
import com.yanxiu.gphone.jiaoyan.business.mine.adapter.MyCertFinishAdapter;
import com.yanxiu.gphone.jiaoyan.business.mine.adapter.MyCertUnFinishAdapter;
import com.yanxiu.gphone.jiaoyan.business.mine.interfaces.MineMyCertUnFinishContract;
import com.yanxiu.gphone.jiaoyan.business.mine.presenter.MineMyCertUnFinishPresenter;

import java.util.List;

/**
 * Created By cailei on 2018/10/19
 */
public class MineMyCertUnFinishFragment
        extends BaseRecyclerFragment<MineMyCertUnFinishContract.IPresenter>
        implements MineMyCertUnFinishContract.IView
{
    protected MyCertUnFinishAdapter mAdapter;

    @Override
    protected BaseAdapter initAdapter() {
        mAdapter = new MyCertUnFinishAdapter(getContext());
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
    protected MineMyCertUnFinishContract.IPresenter initPresenterImpl() {
        return new MineMyCertUnFinishPresenter(this);
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
    }
    // endregion mvp
}
