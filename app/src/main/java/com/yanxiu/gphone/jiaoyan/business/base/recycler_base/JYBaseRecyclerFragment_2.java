package com.yanxiu.gphone.jiaoyan.business.base.recycler_base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.yanxiu.common_base.base.ui.JYBaseFragment;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

/**
 * Created By cailei on 2018/10/23
 */
public class JYBaseRecyclerFragment_2 extends JYBaseFragment
    implements IDataFetcherCallback
{
    @Override
    public int bindLayout() {
        return R.layout.common_recycler_view_fragment;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        mSwipeRefreshLayout = contentView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwipeRefreshLayout.setEnabled(true);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 下拉刷新，取First Page数据
                mFetcher.fetchFirstPage();
            }
        });

        mRecyclerView = contentView.findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                // 上拉加载，取Next Page数据
                mFetcher.fetchNextPage();
            }
        }, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    public void doBusiness() {
        mSwipeRefreshLayout.setRefreshing(true);
        mFetcher.fetchFirstPage();
    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }

    // region recycler

    // 数据
    protected IDataFetcher mFetcher;
    // 界面
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected RecyclerView mRecyclerView;
    protected BaseQuickAdapter mAdapter;

    @Override
    public void onFirstPageSuccess() {
        mSwipeRefreshLayout.setRefreshing(false);
        if (mFetcher.getData().size() == 0) {
            // 第一页就没有数据
            showEmptyView();
            return;
        }

        mAdapter.setNewData(mFetcher.getData());
        checkIsLastPage();
    }

    @Override
    public void onFirstPageError(Error error) {
        mSwipeRefreshLayout.setRefreshing(false);
        showErrorView(error.getLocalizedMessage());
    }

    @Override
    public void onNextPageSuccess() {
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.loadMoreComplete();
        mAdapter.setNewData(mFetcher.getData());
        checkIsLastPage();
    }

    @Override
    public void onNextPageError(Error error) {
        mAdapter.loadMoreFail();
        // todo : cailei 想要load more view消失呢？？？
        Toast.makeText(getContext(),error.getLocalizedMessage(), Toast.LENGTH_LONG);
    }

    private void checkIsLastPage() {
        if (!mFetcher.hasMoreData()) {
            mAdapter.loadMoreEnd(true);
            View footer = getLayoutInflater().inflate(R.layout.common_recycler_footer_layout, null);
            mAdapter.addFooterView(footer);
        } else {
            mAdapter.removeAllFooterView();
        }
    }
    // endregion recycler
}
