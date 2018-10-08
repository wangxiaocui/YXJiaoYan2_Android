package com.yanxiu.video;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.yanxiu.video.other.LoadingView;
import com.yanxiu.video.other.VideoPlayerErrorView;

import static com.yanxiu.video.LSTCourseVideoManager.VideoState.FourG;
import static com.yanxiu.video.LSTCourseVideoManager.VideoState.LastVideoFinished;
import static com.yanxiu.video.LSTCourseVideoManager.VideoState.Loading;
import static com.yanxiu.video.LSTCourseVideoManager.VideoState.NetworkError;
import static com.yanxiu.video.LSTCourseVideoManager.VideoState.Normal;
import static com.yanxiu.video.LSTCourseVideoManager.VideoState.NotFoundError;
import static com.yanxiu.video.LSTCourseVideoManager.VideoState.SuiTangLian;
import static com.yanxiu.video.PlaybackControllerView.DEFAULT_SHOW_TIMEOUT_MS;


/**
 * 播放器view
 */

public class PlayerView extends FrameLayout {
    private SimpleExoPlayer player;
    private ComponentListener componentListener = new ComponentListener();
    private LSTCourseVideoManager.VideoState state;
    private SurfaceView surfaceView;
    private LoadingView loadingView;
    private VideoPlayerErrorView networkErrorView;
    private VideoPlayerErrorView notFoundErrorView;
    private VideoPlayerErrorView lastVideoFinishedView;
    private VideoPlayerErrorView fourGView;
    private PlaybackControllerView controllerView;

    private Context context;
    private AspectRatioFrameLayout contentFrame;

    public PlayerView(@NonNull Context context) {
        this(context,null);
    }

    public PlayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PlayerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;

        int playerLayoutId = R.layout.player_view;
        int resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT;
        LayoutInflater.from(context).inflate(playerLayoutId, this);

        contentFrame = (AspectRatioFrameLayout) findViewById(R.id.content_frame);
        if (contentFrame != null) {
            contentFrame.setResizeMode(resizeMode);
        }

        if (contentFrame != null) {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            surfaceView = new SurfaceView(context);
            surfaceView.setLayoutParams(params);
            contentFrame.addView(surfaceView, 0);
        }

        loadingView = (LoadingView)findViewById(R.id.loading_view);
        networkErrorView = (VideoPlayerErrorView)findViewById(R.id.network_error_view);
        notFoundErrorView = (VideoPlayerErrorView)findViewById(R.id.not_found_error_view);
        lastVideoFinishedView = (VideoPlayerErrorView)findViewById(R.id.last_video_finished_view);
        fourGView = (VideoPlayerErrorView)findViewById(R.id.four_g_view);
    }

    public SimpleExoPlayer getPlayer() {
        return player;
    }

    public void setPlayer(SimpleExoPlayer player) {
        if (this.player == player) {
            return;
        }

        if (this.player != null) {
            this.player.clearVideoListener(componentListener);
            this.player.clearVideoSurfaceView((SurfaceView) surfaceView);
        }

        this.player = player;
        if (player != null) {
            player.setVideoSurfaceView((SurfaceView) surfaceView);
            player.setVideoListener(componentListener);
        }
    }

    public void setControllerView(PlaybackControllerView controllerView) {
        this.controllerView = controllerView;
    }
    /**
     * 设置videoview的状态
     * 具体参见{@link LSTCourseVideoManager.VideoState}
     */
    public void setVideoState(LSTCourseVideoManager.VideoState state) {
        this.state = state;

        if (state == SuiTangLian) {
            return;
        }

        if (state == Normal) {
            loadingView.hiddenLoadingView();
//            loadingView.setVisibility(GONE);
            networkErrorView.setVisibility(GONE);
            notFoundErrorView.setVisibility(GONE);
            lastVideoFinishedView.setVisibility(GONE);
            fourGView.setVisibility(GONE);
        }

        if (state == Loading) {
            loadingView.setVisibility(VISIBLE);
            loadingView.showLoadingView();
            networkErrorView.setVisibility(GONE);
            notFoundErrorView.setVisibility(GONE);
            lastVideoFinishedView.setVisibility(GONE);
            fourGView.setVisibility(GONE);
        }

        if (state == NetworkError) {
            loadingView.hiddenLoadingView();
//            loadingView.setVisibility(GONE);
            networkErrorView.setVisibility(VISIBLE);
            notFoundErrorView.setVisibility(GONE);
            lastVideoFinishedView.setVisibility(GONE);
            fourGView.setVisibility(GONE);
        }

        if (state == NotFoundError) {
            loadingView.hiddenLoadingView();
//            loadingView.setVisibility(GONE);
            networkErrorView.setVisibility(GONE);
            notFoundErrorView.setVisibility(VISIBLE);
            lastVideoFinishedView.setVisibility(GONE);
            fourGView.setVisibility(GONE);
        }

        if (state == LastVideoFinished) {
            loadingView.hiddenLoadingView();
//            loadingView.setVisibility(GONE);
            networkErrorView.setVisibility(GONE);
            notFoundErrorView.setVisibility(GONE);
            lastVideoFinishedView.setVisibility(VISIBLE);
            fourGView.setVisibility(GONE);
        }

        if (state == FourG) {
            loadingView.hiddenLoadingView();
//            loadingView.setVisibility(GONE);
            networkErrorView.setVisibility(GONE);
            notFoundErrorView.setVisibility(GONE);
            lastVideoFinishedView.setVisibility(GONE);
            fourGView.setVisibility(VISIBLE);
        }
    }

    private final class ComponentListener implements SimpleExoPlayer.VideoListener {
        // SimpleExoPlayer.VideoListener implementation

        @Override
        public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
            if (contentFrame != null) {
                float aspectRatio = height == 0 ? 1 : (width * pixelWidthHeightRatio) / height;
                contentFrame.setAspectRatio(aspectRatio);
            }
        }

        @Override
        public void onRenderedFirstFrame() {
        }
    }

    /**
     * 拦截down事件，控制栏显示
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //只有在player不为空，且是down事件时，才执行显示或隐藏控制栏逻辑
        if (player == null || ev.getActionMasked() != MotionEvent.ACTION_DOWN) {
            return false;
        }
        if (!controllerView.isVisible()) {
            maybeShowController(true);
        } else {
            controllerView.hide();
        }
        return true;
    }

    /**
     * 轨迹球回调，不需要
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onTrackballEvent(MotionEvent ev) {
        super.onTrackballEvent(ev);
        if (player == null) {
            return false;
        }
        maybeShowController(true);
        return true;
    }

    /**
     * 显示控制栏
     * 这个isForced参数目前看没有用
     *
     * @param isForced
     */
    private void maybeShowController(boolean isForced) {
        if (player == null) {
            return;
        }

        if ((state != Normal) && (state != Loading)) {
            return;
        }

        int playbackState = player.getPlaybackState();
        boolean showIndefinitely = playbackState == ExoPlayer.STATE_IDLE || playbackState == ExoPlayer.STATE_ENDED || !player.getPlayWhenReady();
        boolean wasShowingIndefinitely = controllerView.isVisible() && controllerView.getShowTimeoutMs() <= 0;
        //controllerView.setShowTimeoutMs(showIndefinitely ? 0 : DEFAULT_SHOW_TIMEOUT_MS);
        controllerView.setShowTimeoutMs(DEFAULT_SHOW_TIMEOUT_MS);
        if (isForced || showIndefinitely || wasShowingIndefinitely) {
            controllerView.show();
        }
    }
    //endregion
}
