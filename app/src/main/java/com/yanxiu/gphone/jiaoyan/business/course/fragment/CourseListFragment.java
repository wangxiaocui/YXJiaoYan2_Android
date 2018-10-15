package com.yanxiu.gphone.jiaoyan.business.course.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragment;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course.adapter.CourseListAdapter;
import com.yanxiu.gphone.jiaoyan.business.course.interfaces.CourseListContract;
import com.yanxiu.gphone.jiaoyan.business.course.presenter.CourseListPresenter;
import com.yanxiu.gphone.jiaoyan.customize.banner.BannerGlideImageLoader;
import com.yanxiu.lib.yx_basic_library.util.YXToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Hu Chao on 18/10/11.
 */
@Route(path = RoutePathConfig.Course_List_Fragment)
public class CourseListFragment extends BaseRecyclerFragment<CourseListContract.IPresenter> implements CourseListContract.IView, OnBannerListener {

    protected CourseListAdapter mAdapter;
    protected Banner mBanner;

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        super.initView(savedInstanceState, contentView);
        View bannerHead = LayoutInflater.from(getContext()).inflate(R.layout.course_banner_layout, xrecycler_view, false);
        mBanner = bannerHead.findViewById(R.id.banner);
//        mBanner.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, YXScreenUtil.dpToPxInt(getContext(), 335)));
        xrecycler_view.addHeaderView(bannerHead);
        mBanner.setDelayTime(3000);
    }

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
        mBanner.setImages(Arrays.asList(getResources().getStringArray(R.array.url)))
                .setImageLoader(new BannerGlideImageLoader())
                .setOnBannerListener(this)
                .start();
    }

    @Override
    protected String getLoadMoreOffset() {
        return String.valueOf(mAdapter.getData(mAdapter.getItemCount() - 1).getUserId());
    }

    @Override
    public void OnBannerClick(int position) {
        YXToastUtil.showToast("点击banner : " + position);
    }
}
