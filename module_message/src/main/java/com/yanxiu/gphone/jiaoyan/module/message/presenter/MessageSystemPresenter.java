package com.yanxiu.gphone.jiaoyan.module.message.presenter;

import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragmentPresenter;
import com.yanxiu.gphone.jiaoyan.module.message.bean.MessageBean;
import com.yanxiu.gphone.jiaoyan.module.message.interfaces.MessageSystemContract;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统消息页Presenter
 * Created by Hu Chao on 18/10/18.
 */
public class MessageSystemPresenter extends BaseRecyclerFragmentPresenter<MessageSystemContract.IView> implements MessageSystemContract.IPresenter {

    public MessageSystemPresenter(MessageSystemContract.IView view) {
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
