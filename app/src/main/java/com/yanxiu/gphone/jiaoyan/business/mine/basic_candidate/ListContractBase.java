package com.yanxiu.gphone.jiaoyan.business.mine.basic_candidate;

import com.yanxiu.gphone.jiaoyan.business.mine.interfaces.MineContract;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBaseView;

/**
 * Created By cailei on 2018/10/18
 */
public class ListContractBase {
    public interface IPresenter extends IYXBasePresenter {
        void doRequestFirstPage();
        void doRequestNextPage();
    }

    public interface IView extends IYXBaseView<ListContractBase.IPresenter> {
        void onFirstPageError(Error error);
        void onNextPageError(Error error);
        void onNoMorePage();
        void onSuccess();
    }
}
