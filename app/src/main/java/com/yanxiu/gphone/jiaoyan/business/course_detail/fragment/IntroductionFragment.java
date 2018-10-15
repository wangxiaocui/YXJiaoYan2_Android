package com.yanxiu.gphone.jiaoyan.business.course_detail.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.test.yanxiu.common_base.base.ui.JYBaseFragment;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course_detail.adapter.IntroductionAdapter;
import com.yanxiu.gphone.jiaoyan.business.course_detail.bean.IntroductionBean;
import com.yanxiu.gphone.jiaoyan.business.course_detail.interfaces.IntrodutionFragmentContract;
import com.yanxiu.gphone.jiaoyan.business.course_detail.presenter.IntrodutionFragmentPresenter;

import java.util.ArrayList;

/**
 * 介绍
 * Created by 戴延枫 on 2018/10/11.
 */

public class IntroductionFragment extends JYBaseFragment<IntrodutionFragmentContract.IPresenter> implements IntrodutionFragmentContract.IView {

    private RecyclerView rv;
    private IntroductionAdapter adapter;

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
        return R.layout.course_detail_introduction_fragment;
    }

    /**
     * 初始化 view
     *
     * @param savedInstanceState
     * @param contentView
     */
    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        rv = contentView.findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
        adapter = new IntroductionAdapter(getActivity());
        ArrayList<IntroductionBean> list =new ArrayList();
        for (int i = 0; i < 5; i++) {
            list.add(new IntroductionBean());
        }
        adapter.setData(list);
        rv.setAdapter(adapter);
    }

    /**
     * 初始化 监听
     */
    @Override
    public void initListener() {

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
    protected IntrodutionFragmentPresenter initPresenterImpl() {
        return new IntrodutionFragmentPresenter(this);
    }
}
