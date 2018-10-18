package com.yanxiu.gphone.jiaoyan.module.message.presenter;

import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragmentPresenter;
import com.yanxiu.gphone.jiaoyan.module.message.interfaces.MessageUserContract;

import java.util.Arrays;

/**
 * 互动消息页Presenter
 * Created by Hu Chao on 18/10/18.
 */
public class MessageUserPresenter extends BaseRecyclerFragmentPresenter<MessageUserContract.IView> implements MessageUserContract.IPresenter {

    public MessageUserPresenter(MessageUserContract.IView view) {
        super(view);
    }

    @Override
    public void request(boolean isRefresh, final String offset) {
        if (isRefresh) {
            mView.onRefreshSuccess(100, Arrays.asList("1", "2", "3"));
        } else {
            mView.onLoadMoreSuccess(100, Arrays.asList("1", "2", "3", "4", "5", "6"));
        }
    }
}
