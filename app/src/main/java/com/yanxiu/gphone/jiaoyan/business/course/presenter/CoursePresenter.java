package com.yanxiu.gphone.jiaoyan.business.course.presenter;

import com.test.yanxiu.common_base.base.net.JYBaseCallback;
import com.yanxiu.gphone.jiaoyan.business.course.interfaces.CourseContract;
import com.yanxiu.gphone.jiaoyan.business.course.net.GetCategoryRequest;
import com.yanxiu.gphone.jiaoyan.business.course.net.GetCategoryResponse;
import com.yanxiu.lib.yx_basic_library.base.basemvp.YXBasePresenterImpl;
import com.yanxiu.lib.yx_basic_library.network.IYXResponseBodyDealer;
import com.yanxiu.lib.yx_basic_library.network.YXRequestBase;

import okhttp3.Request;

/**
 * Created by Hu Chao on 18/10/15.
 */
public class CoursePresenter extends YXBasePresenterImpl<CourseContract.IView> implements CourseContract.IPresenter {

    public CoursePresenter(CourseContract.IView view) {
        super(view);
    }

    @Override
    public void getCategory() {
        mView.showLoadingView();
        GetCategoryRequest request = new GetCategoryRequest();
        addRequest(request, GetCategoryResponse.class, new JYBaseCallback<GetCategoryResponse>() {
            @Override
            protected void onSuccessResponse(YXRequestBase request, GetCategoryResponse ret) {
                mView.hideLoadingView();
                mView.onSuccess(ret.getData());
            }

            @Override
            public void onRequestCreated(Request request) {

            }

            @Override
            public void onFail(YXRequestBase request, Error error) {
                mView.hideLoadingView();
            }
        });
    }
}
