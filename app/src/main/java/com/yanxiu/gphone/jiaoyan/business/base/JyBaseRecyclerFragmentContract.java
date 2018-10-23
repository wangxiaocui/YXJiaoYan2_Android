package com.yanxiu.gphone.jiaoyan.business.base;

import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBaseView;

import java.util.List;

/**
 * Created by Hu Chao on 18/10/10.
 */
public class JyBaseRecyclerFragmentContract {

    public interface IView<P extends IPresenter> extends IYXBaseView<P> {

        void onRequestSuccess(boolean isRefresh, int total, List datas);

        void loadMoreEnd(boolean end);
    }

    public interface IPresenter extends IYXBasePresenter {
        void refresh();

        void loadMore(String offset);
    }
}
