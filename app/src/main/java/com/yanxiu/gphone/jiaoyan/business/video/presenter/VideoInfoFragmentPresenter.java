package com.yanxiu.gphone.jiaoyan.business.video.presenter;

import com.test.yanxiu.common_base.base.ui.fragment.BaseRecyclerFragmentPresenter;
import com.yanxiu.gphone.jiaoyan.business.video.interfaces.VideoInfoFragmentContract;

/**
 * 介绍逻辑处理
 * Created by 戴延枫 on 18/10/10.
 */
public class VideoInfoFragmentPresenter extends BaseRecyclerFragmentPresenter<VideoInfoFragmentContract.IView> implements VideoInfoFragmentContract.IPresenter {
    public VideoInfoFragmentPresenter(VideoInfoFragmentContract.IView view) {
        super(view);
    }

    /**
     * @param isRefresh 区分刷新请求还是加载更多请求
     * @param offset    加载更多的偏移
     */
    @Override
    public void request(boolean isRefresh, String offset) {

    }
}
