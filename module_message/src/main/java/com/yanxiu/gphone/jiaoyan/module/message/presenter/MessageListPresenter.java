package com.yanxiu.gphone.jiaoyan.module.message.presenter;

import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragmentPresenter;
import com.yanxiu.gphone.jiaoyan.module.message.interfaces.MessageListContract;

import java.util.Arrays;

/**
 * Created by Hu Chao on 18/10/18.
 */
public class MessageListPresenter extends BaseRecyclerFragmentPresenter<MessageListContract.IView> implements MessageListContract.IPresenter {

    public MessageListPresenter(MessageListContract.IView view) {
        super(view);
    }

    @Override
    public void request(final String offset) {
        if (offset == null) {
            mView.onRefreshSuccess(100, Arrays.asList("1", "2", "3"));
        } else {
            mView.onLoadMoreSuccess(100, Arrays.asList("1", "2", "3", "4", "5", "6"));
        }
    }
}
