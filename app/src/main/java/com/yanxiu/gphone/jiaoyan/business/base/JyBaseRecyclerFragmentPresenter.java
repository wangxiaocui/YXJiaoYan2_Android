package com.yanxiu.gphone.jiaoyan.business.base;

import com.yanxiu.gphone.jiaoyan.business.course.net.GetDetailForWholeResponse;
import com.yanxiu.lib.yx_basic_library.base.basemvp.YXBasePresenterImpl;

import java.util.List;

public abstract class JyBaseRecyclerFragmentPresenter<V extends JyBaseRecyclerFragmentContract.IView> extends YXBasePresenterImpl<V> implements JyBaseRecyclerFragmentContract.IPresenter {

    private static final int PAGE_SIZE = 6;

    public JyBaseRecyclerFragmentPresenter(V view) {
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


    public void requestData(boolean isRefresh, final String offset) {
        GetDetailForWholeResponse response = new GetDetailForWholeResponse();
    }

    public void refreshSuccess(int total, List datas) {
        mView.onRequestSuccess(true, total, datas);
//        mView.loadMoreEnd(datas.size() < PAGE_SIZE);
    }

    public void loadMoreSuccess(int total, List datas) {
        mView.onRequestSuccess(false, total, datas);
//        mView.loadMoreEnd(datas.size() < PAGE_SIZE);
    }

}
