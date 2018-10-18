package com.yanxiu.gphone.jiaoyan.business.mine.presenter;

import com.test.yanxiu.common_base.base.net.JYBaseCallback;
import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragmentPresenter;
import com.yanxiu.gphone.jiaoyan.business.course.net.ClassStudyScoreRankingRequest;
import com.yanxiu.gphone.jiaoyan.business.course.net.ClassStudyScoreRankingResponse;
import com.yanxiu.gphone.jiaoyan.business.mine.interfaces.MineMyCourseAttendContract;
import com.yanxiu.gphone.jiaoyan.business.mine.mock.MockListRequest;
import com.yanxiu.gphone.jiaoyan.business.mine.mock.MockListResponse;
import com.yanxiu.lib.yx_basic_library.network.IYXHttpCallback;
import com.yanxiu.lib.yx_basic_library.network.IYXResponseBodyDealer;
import com.yanxiu.lib.yx_basic_library.network.YXRequestBase;
import com.yanxiu.lib.yx_basic_library.util.logger.YXLogger;

import okhttp3.Request;

/**
 * Created By cailei on 2018/10/18
 */
public class MineMyCourseAttendPresenter
        extends BaseRecyclerFragmentPresenter<MineMyCourseAttendContract.IView>
        implements MineMyCourseAttendContract.IPresenter {

    public MineMyCourseAttendPresenter(MineMyCourseAttendContract.IView view) {
        super(view);
    }

    @Override
    public void request(final String offset) {
        MockListRequest request = new MockListRequest();
        addRequest(request, MockListResponse.class, new IYXHttpCallback<MockListResponse>() {
            @Override
            public void onRequestCreated(Request request) {

            }

            @Override
            public void onSuccess(YXRequestBase request, MockListResponse ret) {
                if (offset == null) {
                    mView.onRefreshSuccess(100, ret.data);
                } else {
                    mView.onLoadMoreSuccess(100, ret.data);
                }
            }

            @Override
            public void onFail(YXRequestBase request, Error error) {

            }
        });
    }
}
