package com.yanxiu.gphone.jiaoyan.business.course_detail.presenter;

import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragmentPresenter;
import com.yanxiu.gphone.jiaoyan.business.course_detail.interfaces.EvalutionFragmentContract;
import com.yanxiu.gphone.jiaoyan.business.course_detail.interfaces.IntrodutionFragmentContract;
import com.yanxiu.lib.yx_basic_library.base.basemvp.YXBasePresenterImpl;

/**
 * 介绍逻辑处理
 * Created by 戴延枫 on 18/10/10.
 */
public class EvalutionFragmentPresenter extends BaseRecyclerFragmentPresenter<EvalutionFragmentContract.IView> implements EvalutionFragmentContract.IPresenter {
    public EvalutionFragmentPresenter(EvalutionFragmentContract.IView view) {
        super(view);
    }

    @Override
    public void request(String offset) {

    }
}
