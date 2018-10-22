package com.yanxiu.gphone.jiaoyan.business.course.presenter;

import com.test.yanxiu.common_base.base.net.JYBaseCallback;
import com.yanxiu.gphone.jiaoyan.business.base.JyBaseRecyclerFragmentPresenter;
import com.yanxiu.gphone.jiaoyan.business.course.interfaces.CourseTopicContract;
import com.yanxiu.gphone.jiaoyan.business.course.net.GetDetailForWholeRequest;
import com.yanxiu.gphone.jiaoyan.business.course.net.GetDetailForWholeResponse;
import com.yanxiu.lib.yx_basic_library.network.YXRequestBase;

import okhttp3.Request;

public class CourseTopicPresenter extends JyBaseRecyclerFragmentPresenter<CourseTopicContract.IView> implements CourseTopicContract.IPresenter {

    public CourseTopicPresenter(CourseTopicContract.IView view) {
        super(view);
    }

    @Override
    public void request(final boolean isRefresh, String offset) {
        GetDetailForWholeRequest request = new GetDetailForWholeRequest();
        addRequest(request, GetDetailForWholeResponse.class, new JYBaseCallback<GetDetailForWholeResponse>() {
            @Override
            public void onRequestCreated(Request request) {

            }

            @Override
            protected void onSuccessResponse(YXRequestBase request, GetDetailForWholeResponse ret) {
                if (isRefresh) {
                    refreshSuccess(100, ret.getData().getCourseList());
                } else {
                    loadMoreSuccess(100, ret.getData().getCourseList());
                }
            }

            @Override
            public void onFail(YXRequestBase request, Error error) {

            }
        });
    }
}
