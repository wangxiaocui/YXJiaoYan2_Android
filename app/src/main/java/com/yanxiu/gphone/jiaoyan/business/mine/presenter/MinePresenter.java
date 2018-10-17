package com.yanxiu.gphone.jiaoyan.business.mine.presenter;

import android.view.View;

import com.test.yanxiu.common_base.base.net.JYBaseResponse;
import com.test.yanxiu.common_base.base.ui.JYBaseFragment;
import com.yanxiu.gphone.jiaoyan.business.mine.interfaces.MineContract;
import com.yanxiu.gphone.jiaoyan.business.mine.mock.MockAsyncTask;
import com.yanxiu.gphone.jiaoyan.business.mine.network.SignRequest;
import com.yanxiu.lib.yx_basic_library.base.basemvp.YXBasePresenterImpl;
import com.yanxiu.lib.yx_basic_library.network.IYXHttpCallback;
import com.yanxiu.lib.yx_basic_library.network.YXRequestBase;

import okhttp3.Request;

/**
 * Created by Cai Lei on 2018/10/10.
 */
public class MinePresenter
        extends YXBasePresenterImpl<MineContract.IView>
        implements MineContract.IPresenter {

    public MinePresenter(MineContract.IView view) {
        super(view);
    }

    @Override
    public void doCheckIn() {
        // todo: cailei
        ((JYBaseFragment)mView).showLoadingView();
        SignRequest signRequest = new SignRequest();
        signRequest.token = "test 123456";
        addRequest(signRequest, JYBaseResponse.class, new IYXHttpCallback<JYBaseResponse>() {
            @Override
            public void onRequestCreated(Request request) {

            }

            @Override
            public void onSuccess(YXRequestBase request, JYBaseResponse ret) {
                ((JYBaseFragment)mView).hideLoadingView();
                mView.onCheckInDone();
            }

            @Override
            public void onFail(YXRequestBase request, Error error) {
                ((JYBaseFragment)mView).hideLoadingView();
                mView.onError(error);
            }
        });

        MockAsyncTask.doTask(new MockAsyncTask.Callback() {
            @Override
            public void done() {
                ((JYBaseFragment)mView).hideLoadingView();
                mView.onCheckInDone();
            }
        });
    }

    @Override
    public void doUploadPortrait(String path) {
        // todo: cailei
    }
}
