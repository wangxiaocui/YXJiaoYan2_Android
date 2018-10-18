package com.yanxiu.gphone.jiaoyan.module.message.fragment;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragment;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.module.message.adapter.MessageUserAdapter;
import com.yanxiu.gphone.jiaoyan.module.message.interfaces.MessageUserContract;
import com.yanxiu.gphone.jiaoyan.module.message.presenter.MessageUserPresenter;

import java.util.Arrays;

/**
 * 系统消息页
 * Created by Hu Chao on 18/10/18.
 */
@Route(path = RoutePathConfig.Message_System_Fragment)
public class MessageSystemFragment extends BaseRecyclerFragment<MessageUserContract.IPresenter> implements MessageUserContract.IView {
    @Override
    protected BaseAdapter initAdapter() {
        return new MessageUserAdapter(getContext());
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    public void doBusiness() {
        mAdapter.setData(Arrays.asList("1", "2", "3", "4", "5", "6", "1", "2", "3", "4", "5", "6"));
    }

    @Override
    protected MessageUserContract.IPresenter initPresenterImpl() {
        return new MessageUserPresenter(this);
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