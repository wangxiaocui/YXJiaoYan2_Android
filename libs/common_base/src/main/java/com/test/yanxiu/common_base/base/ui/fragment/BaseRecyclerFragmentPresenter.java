package com.test.yanxiu.common_base.base.ui.fragment;

import com.yanxiu.lib.yx_basic_library.base.basemvp.YXBasePresenterImpl;

public abstract class BaseRecyclerFragmentPresenter<V extends BaseRecyclerFragmentContract.IView> extends YXBasePresenterImpl<V> implements BaseRecyclerFragmentContract.IPresenter {

    public BaseRecyclerFragmentPresenter(V view) {
        super(view);
    }

    @Override
    public void refresh() {
        request(true, null);
    }

    @Override
    public void loadMore(final String offset) {
        request(false, offset);
    }

    /**
     * @param isRefresh 区分刷新请求还是加载更多请求
     * @param offset    加载更多的偏移
     */
    public abstract void request(boolean isRefresh, final String offset);


}
