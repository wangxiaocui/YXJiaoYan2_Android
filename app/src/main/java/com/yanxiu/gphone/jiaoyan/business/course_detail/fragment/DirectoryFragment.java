package com.yanxiu.gphone.jiaoyan.business.course_detail.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.test.yanxiu.common_base.base.ui.JYBaseFragment;
import com.test.yanxiu.common_base.base.ui.recycler_view.OnRecyclerViewItemClickListener;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course_detail.adapter.DirectioryAdapter;
import com.yanxiu.gphone.jiaoyan.business.course_detail.bean.DirectioryBean;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.util.YXToastUtil;

import java.util.ArrayList;

/**
 * 目录
 * Created by 戴延枫 on 2018/10/11.
 */

public class DirectoryFragment extends JYBaseFragment implements OnRecyclerViewItemClickListener {

    private RecyclerView rv;
    private DirectioryAdapter adapter;

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
        return R.layout.course_detail_directory_fragment;
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
        adapter = new DirectioryAdapter(getActivity());
        ArrayList<DirectioryBean> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(new DirectioryBean());
        }
        adapter.setData(list);
        rv.setAdapter(adapter);
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
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }

    @Override
    public void onItemClick(View itemView, Object data, int position) {
        YXToastUtil.showToast(""+position);
    }
}
