package com.yanxiu.gphone.jiaoyan.business.course_detail.presenter;

import com.yanxiu.gphone.jiaoyan.business.course_detail.interfaces.IntrodutionFragmentContract;
import com.yanxiu.lib.yx_basic_library.base.basemvp.YXBasePresenterImpl;

/**
 * 介绍逻辑处理
 * Created by 戴延枫 on 18/10/10.
 */
public class IntrodutionFragmentPresenter extends YXBasePresenterImpl<IntrodutionFragmentContract.IView> implements IntrodutionFragmentContract.IPresenter {
    public IntrodutionFragmentPresenter(IntrodutionFragmentContract.IView view) {
        super(view);
    }

}
