package com.yanxiu.gphone.jiaoyan.business.course;

import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragmentPresenter;
import com.yanxiu.lib.yx_basic_library.network.IYXHttpCallback;
import com.yanxiu.lib.yx_basic_library.network.YXRequestBase;

import okhttp3.Request;

public class CourseListPresenter extends BaseRecyclerFragmentPresenter<CourseListContract.IView> implements CourseListContract.IPresenter {

    public CourseListPresenter(CourseListContract.IView view) {
        super(view);
    }

    @Override
    public void request(final String offset) {
        ClassStudyScoreRankingRequest request = new ClassStudyScoreRankingRequest();
        addRequest(request, ClassStudyScoreRankingResponse.class, new IYXHttpCallback<ClassStudyScoreRankingResponse>() {
            @Override
            public void onRequestCreated(Request request) {

            }

            @Override
            public void onSuccess(YXRequestBase request, ClassStudyScoreRankingResponse ret) {
                if (ret.getCode() == 0) {
                    if (offset == null) {
                        mView.onRefreshSuccess(ret.getData().getUserRank().getTotalElements(),
                                ret.getData().getUserRank().getElements());
                    } else {
                        mView.onLoadMoreSuccess(ret.getData().getUserRank().getTotalElements(),
                                ret.getData().getUserRank().getElements());
                    }
                } else {

                }
            }

            @Override
            public void onFail(YXRequestBase request, Error error) {
            }
        });
    }
}
