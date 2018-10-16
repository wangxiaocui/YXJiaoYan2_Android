package com.test.yanxiu.common_base.base.ui.fragment;

import com.yanxiu.lib.yx_basic_library.base.basemvp.YXBasePresenterImpl;

public abstract class BaseRecyclerFragmentPresenter<V extends BaseRecyclerFragmentContract.IView> extends YXBasePresenterImpl<V> implements BaseRecyclerFragmentContract.IPresenter {

    public BaseRecyclerFragmentPresenter(V view) {
        super(view);
    }

    @Override
    public void refresh() {
        request(null);
    }

    @Override
    public void loadMore(final String offset) {
        request(offset);
    }

    public abstract void request(final String offset);


}
