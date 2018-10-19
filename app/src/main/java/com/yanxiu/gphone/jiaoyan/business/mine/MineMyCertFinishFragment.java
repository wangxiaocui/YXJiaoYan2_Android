package com.yanxiu.gphone.jiaoyan.business.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragment;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.yanxiu.gphone.jiaoyan.business.mine.adapter.MyCertFinishAdapter;
import com.yanxiu.gphone.jiaoyan.business.mine.interfaces.MineMyCertFinishContract;
import com.yanxiu.gphone.jiaoyan.business.mine.presenter.MineMyCertFinishPresenter;

import java.util.List;

/**
 * Created By cailei on 2018/10/19
 */
public class MineMyCertFinishFragment extends BaseRecyclerFragment<MineMyCertFinishContract.IPresenter>
        implements MineMyCertFinishContract.IView
{
    protected MyCertFinishAdapter mAdapter;

    @Override
    protected BaseAdapter initAdapter() {
        mAdapter = new MyCertFinishAdapter(getContext());
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
    protected MineMyCertFinishContract.IPresenter initPresenterImpl() {
        return new MineMyCertFinishPresenter(this);
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
