package com.yanxiu.video;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;


/**
 * 片头控制栏
 */

public class LSTHeadPlaybackControllerView extends PlaybackControllerView {
    public LSTHeadPlaybackControllerView(@NonNull Context context) {
        this(context, null);
    }

    public LSTHeadPlaybackControllerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LSTHeadPlaybackControllerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int layoutId() {
        return R.layout.player_head_playback_control;
    }

    @Override
    public void hide() {
    }
}
