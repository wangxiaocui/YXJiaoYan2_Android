package com.yanxiu.gphone.jiaoyan.business.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.yanxiu.common_base.base.ui.JYBaseFragment;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.base.JyBaseRecyclerFragmentContract;

import java.util.List;

/**
 * 包含RecyclerView的baseFragment, 支持上拉下拉，默认禁止
 * Created by Hu Chao on 18/10/11.
 */
public abstract class JyBaseRecyclerFragment<P extends JyBaseRecyclerFragmentContract.IPresenter> extends JYBaseFragment<P> implements JyBaseRecyclerFragmentContract.IView<P> {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    protected RecyclerView mRecyclerView;
    protected BaseQuickAdapter mAdapter;

    @Override
    public int bindLayout() {
        return R.layout.common_recycler_view_fragment;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        mSwipeRefreshLayout = contentView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwipeRefreshLayout.setEnabled(pullRefreshEnabled());
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        mRecyclerView = contentView.findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = initAdapter();
        mAdapter.setEnableLoadMore(loadingMoreEnabled());
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void doBusiness() {
        mSwipeRefreshLayout.setRefreshing(true);
        refresh();
    }

    @Override
    public void onRequestSuccess(boolean isRefresh, int total, List datas) {
        if (isRefresh) {
            mAdapter.setNewData(datas);
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            mAdapter.addData(datas);
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void loadMoreEnd(boolean end) {
        mAdapter.loadMoreEnd(end);
    }

    /**
     * 设置是否可上拉加载更多，默认禁止
     * 如果设置可加载更多，则必须重写{@link #getLoadMoreOffset()}方法，设置加载更多的偏移
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
    protected abstract BaseQuickAdapter initAdapter();

    /**
     * 设置加载更多时请求偏移标志位。
     *
     * @return
     */
    protected String getLoadMoreOffset() {
        return null;
    }

    private void loadMore() {
        mPresenter.loadMore(getLoadMoreOffset());
    }

    private void refresh() {
        mPresenter.refresh();
    }

}
