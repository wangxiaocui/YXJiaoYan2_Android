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

    @Override
    public void request(String offset) {

    }
}
