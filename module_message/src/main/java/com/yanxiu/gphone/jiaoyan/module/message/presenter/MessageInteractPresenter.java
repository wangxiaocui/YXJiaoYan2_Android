package com.yanxiu.gphone.jiaoyan.module.message.presenter;

import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragmentPresenter;
import com.yanxiu.gphone.jiaoyan.module.message.bean.MessageBean;
import com.yanxiu.gphone.jiaoyan.module.message.interfaces.MessageInteractContract;

import java.util.ArrayList;
import java.util.List;

/**
 * 互动消息页Presenter
 * Created by Hu Chao on 18/10/18.
 */
public class MessageInteractPresenter extends BaseRecyclerFragmentPresenter<MessageInteractContract.IView> implements MessageInteractContract.IPresenter {

    public MessageInteractPresenter(MessageInteractContract.IView view) {
        super(view);
    }

    @Override
    public void request(boolean isRefresh, final String offset) {
        List<MessageBean> messageBeans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            messageBeans.add(new MessageBean());
        }
        if (isRefresh) {
            mView.onRefreshSuccess(100, messageBeans);
        } else {
            mView.onLoadMoreSuccess(100, messageBeans);
        }
    }
}
