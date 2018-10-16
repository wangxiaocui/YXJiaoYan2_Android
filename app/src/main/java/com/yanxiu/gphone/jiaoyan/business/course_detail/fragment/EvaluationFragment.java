package com.yanxiu.gphone.jiaoyan.business.course_detail.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragment;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.base.ui.recycler_view.OnRecyclerViewItemClickListener;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course_detail.adapter.EvaluationAdapter;
import com.yanxiu.gphone.jiaoyan.business.course_detail.bean.EvalutionBean;
import com.yanxiu.gphone.jiaoyan.business.course_detail.interfaces.EvalutionFragmentContract;
import com.yanxiu.gphone.jiaoyan.business.course_detail.presenter.EvalutionFragmentPresenter;
import com.yanxiu.lib.yx_basic_library.util.YXToastUtil;

import java.util.ArrayList;

/**
 * 评价
 * Created by 戴延枫 on 2018/10/12.
 */

public class EvaluationFragment extends BaseRecyclerFragment<EvalutionFragmentContract.IPresenter> implements EvalutionFragmentContract.IView, OnRecyclerViewItemClickListener {

    private EvaluationAdapter adapter;

    /**
     * 初始化数据
     *
     * @param bundle 传递过来的 bundle
     */
    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    /**
     * 绑定布局
     *
     * @return 布局 Id
     */
    @Override
    public int bindLayout() {
        return R.layout.course_detail_evaluation_fragment;
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
    }

    /**
     * 设置recyclerveiw的adapter
     *
     * @return
     */
    @Override
    protected BaseAdapter initAdapter() {
        adapter = new EvaluationAdapter(getActivity());
        ArrayList<EvalutionBean> list = new ArrayList();
        for (int i = 0; i < 5; i++) {
            list.add(new EvalutionBean());
        }
        adapter.setData(list);
        return adapter;
    }

    /**
     * 初始化 监听
     */
    @Override
    public void initListener() {
        adapter.setOnRecyclerViewItemClickListener(this);
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
    protected EvalutionFragmentPresenter initPresenterImpl() {
        return new EvalutionFragmentPresenter(this);
    }

    @Override
    public void onItemClick(View itemView, Object data, int position) {
        YXToastUtil.showToast("" + position);
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
    protected String getLoadMoreOffset() {
//        return String.valueOf(mAdapter.getData(mAdapter.getItemCount() - 1).getUserId());
        return "";
    }
}
