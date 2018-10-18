package com.test.yanxiu.common_base.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.test.yanxiu.common_base.R;
import com.test.yanxiu.common_base.base.ui.JYBaseFragment;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;

import java.util.List;

/**
 * 包含RecyclerView的baseFragment, 支持上拉下拉，默认禁止
 * Created by Hu Chao on 18/10/11.
 */
public abstract class BaseRecyclerFragment<P extends BaseRecyclerFragmentContract.IPresenter> extends JYBaseFragment<P> implements BaseRecyclerFragmentContract.IView<P> {

    protected XRecyclerView xrecycler_view;

    protected BaseAdapter mAdapter;

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.common_recycler_fragment;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        xrecycler_view = contentView.findViewById(R.id.rv);
        xrecycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        xrecycler_view.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh();
            }

            @Override
            public void onLoadMore() {
                mPresenter.loadMore(getLoadMoreOffset());
            }
        });
        mAdapter = initAdapter();
        xrecycler_view.setAdapter(mAdapter);
        xrecycler_view.setLoadingMoreEnabled(loadingMoreEnabled());
        xrecycler_view.setPullRefreshEnabled(pullRefreshEnabled());
    }

    @Override
    public void onRefreshSuccess(int total, List datas) {
        xrecycler_view.refreshComplete();
        mAdapter.setData(datas);
        xrecycler_view.setNoMore(mAdapter.getItemCount() >= total);
    }

    @Override
    public void onLoadMoreSuccess(int total, List datas) {
        xrecycler_view.loadMoreComplete();
        mAdapter.addData(datas);
        xrecycler_view.setNoMore(mAdapter.getItemCount() >= total);
    }

    /**
     * 设置是否可上拉加载更多，默认禁止
     *
     * @return
     */
    protected boolean loadingMoreEnabled() {
        return false;
    }

    /**
     * 设置是否可下拉刷新，默认禁止
     *
     * @return
     */
    protected boolean pullRefreshEnabled() {
        return false;
    }

    /**
     * 设置recyclerveiw的adapter
     *
     * @return
     */
    protected abstract BaseAdapter initAdapter();

    /**
     * 设置加载更多时请求偏移标志位。
     *
     * @return
     */
    protected String getLoadMoreOffset() {
        return null;
    }

}
