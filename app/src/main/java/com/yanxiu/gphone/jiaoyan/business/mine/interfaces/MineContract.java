package com.yanxiu.gphone.jiaoyan.business.mine.interfaces;

import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBaseView;

/**
 * Created by Cai Lei on 2018/10/10.
 */
public class MineContract {
    public interface IPresenter extends IYXBasePresenter {
        void doCheckIn();
        void doUploadPortrait();
    }

    public interface IView extends IYXBaseView<IPresenter> {
        void onCheckInDone();
        void onUserInfoUpdate();
    }
}
