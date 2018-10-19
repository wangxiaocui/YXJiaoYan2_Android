package com.yanxiu.gphone.jiaoyan.module.message.fragment;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragment;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.module.message.adapter.MessageSystemAdapter;
import com.yanxiu.gphone.jiaoyan.module.message.interfaces.MessageSystemContract;
import com.yanxiu.gphone.jiaoyan.module.message.presenter.MessageSystemPresenter;

/**
 * 系统消息页
 * Created by Hu Chao on 18/10/18.
 */
@Route(path = RoutePathConfig.Message_System_Fragment)
public class MessageSystemFragment extends BaseRecyclerFragment<MessageSystemContract.IPresenter> implements MessageSystemContract.IView {
    @Override
    protected BaseAdapter initAdapter() {
        return new MessageSystemAdapter(getContext());
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    public void doBusiness() {
        mPresenter.refresh();
    }

    @Override
    protected MessageSystemContract.IPresenter initPresenterImpl() {
        return new MessageSystemPresenter(this);
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
     * 设置加载更多时请求偏移标志位。
     *
     * @return
     */
    protected String getLoadMoreOffset() {
        return "1234";
    }
}