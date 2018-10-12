package com.test.yanxiu.common_base.base.ui.fragment;

import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBaseView;

import java.util.List;

/**
 * Created by Hu Chao on 18/10/10.
 */
public class BaseRecyclerFragmentContract {

    public interface IView<P extends IPresenter> extends IYXBaseView<P> {

        void onRefreshSuccess(int total, List datas);

        void onLoadMoreSuccess(int total, List datas);
    }

    public interface IPresenter extends IYXBasePresenter {
        void refresh();

        void loadMore(String offset);
    }
}
