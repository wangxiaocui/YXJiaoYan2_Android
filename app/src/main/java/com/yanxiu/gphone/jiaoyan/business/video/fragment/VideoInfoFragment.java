package com.yanxiu.gphone.jiaoyan.business.video.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragment;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.base.ui.recycler_view.OnRecyclerViewItemClickListener;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course_detail.adapter.DirectioryAdapter;
import com.yanxiu.gphone.jiaoyan.business.course_detail.bean.DirectioryBean;
import com.yanxiu.gphone.jiaoyan.business.course_detail.bean.EvalutionBean;
import com.yanxiu.gphone.jiaoyan.business.video.adapter.VideoListAdapter;
import com.yanxiu.gphone.jiaoyan.business.video.adapter.VideoQuestionAdapter;
import com.yanxiu.gphone.jiaoyan.business.video.interfaces.VideoInfoFragmentContract;
import com.yanxiu.gphone.jiaoyan.business.video.presenter.VideoInfoFragmentPresenter;
import com.yanxiu.lib.yx_basic_library.base.bean.YXBaseBean;
import com.yanxiu.lib.yx_basic_library.customize.view.RecycleViewDivider;
import com.yanxiu.lib.yx_basic_library.util.YXToastUtil;

import java.util.ArrayList;

/**
 * VideoActivity里，播放器下面的fragment
 * Created by 戴延枫 on 2018/10/11.
 */
@Route(path = RoutePathConfig.Video_Activity_Fragment)
public class VideoInfoFragment extends BaseRecyclerFragment<VideoInfoFragmentContract.IPresenter> implements VideoInfoFragmentContract.IView, OnRecyclerViewItemClickListener {

    private YXBaseBean mockInt;
    private View mHeader;
    private RecyclerView rv_video_list;
    private VideoListAdapter mVideoListAdapter;

    /**
     * 初始化数据
     *
     * @param bundle 传递过来的 bundle
     */
    @Override
    public void initData(@NonNull Bundle bundle) {
        mockInt = (YXBaseBean) bundle.getSerializable(RoutePathConfig.Video_Activity_Fragment);
    }

    /**
     * 绑定布局
     *
     * @return 布局 Id
     */
    @Override
    public int bindLayout() {
        return super.bindLayout();
    }

    /**
     * 初始化 view
     *
     * @param savedInstanceState
     * @param contentView
     */
    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        super.initView(savedInstanceState, contentView);
        initHeaderView();
    }

    private void initHeaderView() {
        mHeader = LayoutInflater.from(getActivity()).inflate(R.layout.video_info_header, (ViewGroup) mContentView.findViewById(android.R.id.content), false);
        mRecyclerView.addHeaderView(mHeader);

        rv_video_list = mHeader.findViewById(R.id.rv_video_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_video_list.setLayoutManager(linearLayoutManager);
        mVideoListAdapter = new VideoListAdapter(getActivity());

        setVideoListData();
    }

    private void setVideoListData() {
        ArrayList<YXBaseBean> list = new ArrayList();
        if (mockInt != null) {
            for (int i = 0; i < 5; i++) {
                list.add(new YXBaseBean());
            }
        }
        mVideoListAdapter.setData(list);
        rv_video_list.setAdapter(mVideoListAdapter);
    }


    /**
     * 设置recyclerveiw的adapter
     *
     * @return
     */
    @Override
    protected BaseAdapter initAdapter() {
//        mRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL,4,getActivity().getResources().getColor(R.color.color_EBEFF2)));
        mAdapter = new VideoQuestionAdapter(getActivity());
        ArrayList<EvalutionBean> list = new ArrayList();
        if (mockInt != null) {
            for (int i = 0; i < 5; i++) {
                list.add(new EvalutionBean());
            }
        }
        mAdapter.setData(list);
        return mAdapter;
    }

    @Override
    protected boolean pullRefreshEnabled() {
        return true;
    }

    @Override
    protected boolean loadingMoreEnabled() {
        return true;
    }

    /**
     * 初始化 监听
     */
    @Override
    public void initListener() {
        mAdapter.setOnRecyclerViewItemClickListener(this);
    }

    /**
     * 业务操作
     */
    @Override
    public void doBusiness() {
    }

    /**
     * 视图点击事件
     *
     * @param view 视图
     */
    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    protected VideoInfoFragmentPresenter initPresenterImpl() {
        return new VideoInfoFragmentPresenter(this);
    }

    @Override
    public void onItemClick(View itemView, Object data, int position) {
        YXToastUtil.showToast("" + position);
    }
}
