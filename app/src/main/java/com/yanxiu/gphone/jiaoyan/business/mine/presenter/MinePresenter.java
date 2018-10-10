package com.yanxiu.gphone.jiaoyan.business.mine.presenter;

import android.view.View;

import com.yanxiu.gphone.jiaoyan.business.mine.interfaces.MineContract;
import com.yanxiu.gphone.jiaoyan.business.mine.mock.MockAsyncTask;
import com.yanxiu.lib.yx_basic_library.base.basemvp.YXBasePresenterImpl;

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
        MockAsyncTask.doTask(new MockAsyncTask.Callback() {
            @Override
            public void done() {
                mView.onCheckInDone();
            }
        });
    }

    @Override
    public void doUploadPortrait(String path) {
        // todo: cailei
        MockAsyncTask.doTask(new MockAsyncTask.Callback() {
            @Override
            public void done() {
                mView.onUserInfoUpdate();
            }
        });
    }
}
