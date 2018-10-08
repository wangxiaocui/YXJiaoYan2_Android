package com.yanxiu.video;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;


/**
 * 正片控制栏
 */

public class LSTBodyPlaybackControllerView extends PlaybackControllerView {
    public LSTBodyPlaybackControllerView(@NonNull Context context) {
        this(context, null);
    }

    public LSTBodyPlaybackControllerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LSTBodyPlaybackControllerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int layoutId() {
        return R.layout.player_body_playback_control;
    }
}
