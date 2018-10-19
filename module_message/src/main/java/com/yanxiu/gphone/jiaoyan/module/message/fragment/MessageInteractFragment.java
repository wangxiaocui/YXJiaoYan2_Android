package com.yanxiu.gphone.jiaoyan.module.message.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragment;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.module.message.adapter.MessageInteractAdapter;
import com.yanxiu.gphone.jiaoyan.module.message.interfaces.MessageInteractContract;
import com.yanxiu.gphone.jiaoyan.module.message.presenter.MessageInteractPresenter;
import com.yanxiu.lib.yx_basic_library.util.YXScreenUtil;

/**
 * 互动消息页
 * Created by Hu Chao on 18/10/18.
 */
@Route(path = RoutePathConfig.Message_Interact_Fragment)
public class MessageInteractFragment extends BaseRecyclerFragment<MessageInteractContract.IPresenter> implements MessageInteractContract.IView {
    @Override
    protected BaseAdapter initAdapter() {
        View view = new View(getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, YXScreenUtil.dpToPxInt(getContext(), 35)));
        xrecycler_view.addHeaderView(view);
        return new MessageInteractAdapter(getContext());
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
    protected MessageInteractContract.IPresenter initPresenterImpl() {
        return new MessageInteractPresenter(this);
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