package com.yanxiu.gphone.jiaoyan.business.search.presenter;

import com.yanxiu.gphone.jiaoyan.business.search.interfaces.SearchContract;
import com.yanxiu.lib.yx_basic_library.base.basemvp.YXBasePresenterImpl;

/**
 * Created by Hu Chao on 18/10/17.
 */
public class SearchPresenter extends YXBasePresenterImpl<SearchContract.IView> implements SearchContract.IPresenter {

    public SearchPresenter(SearchContract.IView view) {
        super(view);
    }

    @Override
    public void getSearchHot() {

    }

    @Override
    public void getSearchResult() {
        mView.onSuccess(null);
    }
}
