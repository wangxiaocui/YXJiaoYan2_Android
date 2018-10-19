package com.test.yanxiu.common_base.base.ui.fragment;

import android.os.Bundle;
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

    protected View mContentView;

    protected XRecyclerView mRecyclerView;

    protected BaseAdapter mAdapter;

    @Override
    public int bindLayout() {
        return R.layout.common_recycler_fragment;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        mContentView = contentView;
        mRecyclerView = contentView.findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
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
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLoadingMoreEnabled(loadingMoreEnabled());
        mRecyclerView.setPullRefreshEnabled(pullRefreshEnabled());
    }

    @Override
    public void onRefreshSuccess(int total, List datas) {
        mRecyclerView.refreshComplete();
        mRecyclerView.setNoMore(mAdapter.getItemCount() >= total);
        mAdapter.setData(datas);
    }

    @Override
    public void onLoadMoreSuccess(int total, List datas) {
        mRecyclerView.loadMoreComplete();
        mRecyclerView.setNoMore(mAdapter.getItemCount() >= total);
        mAdapter.addData(datas);
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
