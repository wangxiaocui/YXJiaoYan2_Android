package com.yanxiu.video.other;

/**
 * Created by 戴延枫 on 2017/10/20.
 * 播放器view里，一些view的回调事件
 */

public interface PlayerViewListener {
    /**
     * 播放器titlebar里的返回按钮点击事件
     */
    void onPlayerBackViewClick();

    /**
     * 播放器播放计时(更新进度)回调
     */
    void onPlayerUpdateProgress();

    /**
     * 播放器弹出随堂练回调
     */
    void onPlayerShowSuiTangLian();
}
