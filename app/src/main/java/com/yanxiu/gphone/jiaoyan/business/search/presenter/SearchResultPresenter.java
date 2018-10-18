package com.yanxiu.gphone.jiaoyan.business.search.presenter;

import com.test.yanxiu.common_base.base.net.JYBaseCallback;
import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragmentContract;
import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragmentPresenter;
import com.yanxiu.gphone.jiaoyan.business.course.net.ClassStudyScoreRankingRequest;
import com.yanxiu.gphone.jiaoyan.business.course.net.ClassStudyScoreRankingResponse;
import com.yanxiu.lib.yx_basic_library.network.IYXResponseBodyDealer;
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
    public void request(final String offset) {
        ClassStudyScoreRankingRequest request = new ClassStudyScoreRankingRequest();
        request.setmBodyDealer(new IYXResponseBodyDealer() {
            @Override
            public String dealWithBody(String body) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return body;
            }
        });
        addRequest(request, ClassStudyScoreRankingResponse.class, new JYBaseCallback<ClassStudyScoreRankingResponse>() {
            @Override
            public void onRequestCreated(Request request) {

            }

            @Override
            protected void onSuccessResponse(YXRequestBase request, ClassStudyScoreRankingResponse ret) {
                if (offset == null) {
                    mView.onRefreshSuccess(ret.getData().getUserRank().getTotalElements(),
                            ret.getData().getUserRank().getElements());
                } else {
                    mView.onLoadMoreSuccess(ret.getData().getUserRank().getTotalElements(),
                            ret.getData().getUserRank().getElements());
                }
            }

            @Override
            public void onFail(YXRequestBase request, Error error) {
            }
        });
    }
}
