package com.yanxiu.gphone.jiaoyan.business.search.presenter;

import com.test.yanxiu.common_base.base.net.JYBaseCallback;
import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragmentContract;
import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragmentPresenter;
import com.yanxiu.gphone.jiaoyan.business.course.net.GetDetailForWholeRequest;
import com.yanxiu.gphone.jiaoyan.business.course.net.GetDetailForWholeResponse;
import com.yanxiu.lib.yx_basic_library.network.YXRequestBase;

import okhttp3.Request;

/**
 * Created by Hu Chao on 18/10/17.
 */
public class SearchResultPresenter extends BaseRecyclerFragmentPresenter<BaseRecyclerFragmentContract.IView> {

    public SearchResultPresenter(BaseRecyclerFragmentContract.IView view) {
        super(view);
    }

    @Override
    public void request(final boolean isRefresh, final String offset) {
        GetDetailForWholeRequest request = new GetDetailForWholeRequest();
        addRequest(request, GetDetailForWholeResponse.class, new JYBaseCallback<GetDetailForWholeResponse>() {
            @Override
            public void onRequestCreated(Request request) {

            }

            @Override
            protected void onSuccessResponse(YXRequestBase request, GetDetailForWholeResponse ret) {
                if (isRefresh) {
                    mView.onRefreshSuccess(ret.getData().getTotal(),
                            ret.getData().getCourseList());
                } else {
                    mView.onLoadMoreSuccess(ret.getData().getTotal(),
                            ret.getData().getCourseList());
                }
            }

            @Override
            public void onFail(YXRequestBase request, Error error) {
            }
        });
    }
}
