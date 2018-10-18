package com.yanxiu.gphone.jiaoyan.business.search.interfaces;

import com.yanxiu.gphone.jiaoyan.business.course.bean.CourseCategoryBean;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBaseView;

import java.util.List;

/**
 * Created by Hu Chao on 18/10/17.
 */
public class SearchContract {

    public interface IView extends IYXBaseView<IPresenter> {

        void showLoadingView();

        void hideLoadingView();

        void onSuccess(List<CourseCategoryBean> categoryBeanList);

        void onFail(String errorMsg);
    }

    public interface IPresenter extends IYXBasePresenter {
        void getSearchHot();
        void getSearchResult();
    }
}
