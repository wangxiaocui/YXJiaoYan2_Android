package com.yanxiu.gphone.jiaoyan.business.search.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragment;
import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragmentContract;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.base.ui.recycler_view.OnRecyclerViewItemClickListener;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.test.yanxiu.common_base.route.data.RouteSearchData;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course.adapter.CourseListAdapter;
import com.yanxiu.gphone.jiaoyan.business.search.presenter.SearchResultPresenter;

import java.util.List;

/**
 * Created by Hu Chao on 18/10/17.
 */
@Route(path = RoutePathConfig.Search_Result_Fragment)
public class SearchResultFragment extends BaseRecyclerFragment<BaseRecyclerFragmentContract.IPresenter> {

    protected CourseListAdapter mAdapter;
    private String searchKey;

    @Override
    public void initData(@NonNull Bundle bundle) {
        RouteSearchData data = (RouteSearchData) bundle.getSerializable(RoutePathConfig.Search_Result_Fragment);
        if (data != null) {
            searchKey = data.getSearchKey();
        }
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        super.initView(savedInstanceState, contentView);
        View header = LayoutInflater.from(getContext()).inflate(R.layout.search_result_header, mRecyclerView, false);
        mRecyclerView.addHeaderView(header);
    }

    @Override
    protected BaseAdapter initAdapter() {
        mAdapter = new CourseListAdapter(getContext());
        return mAdapter;
    }

    @Override
    protected BaseRecyclerFragmentContract.IPresenter initPresenterImpl() {
        return new SearchResultPresenter(this);
    }

    @Override
    public void initListener() {
        mAdapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View itemView, Object data, int position) {
                RouteUtils.startActivity(RoutePathConfig.App_Course_Detail);
            }
        });
    }

    @Override
    public void doBusiness() {
        search(searchKey);
    }

    @Override
    public void onWidgetClick(View view) {

    }

    public void search(String searchKey) {
        showLoadingView();
        mPresenter.refresh();
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
