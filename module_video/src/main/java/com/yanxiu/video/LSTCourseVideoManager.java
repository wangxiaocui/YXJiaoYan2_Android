package com.yanxiu.video;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.UnrecognizedInputFormatException;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;
import com.yanxiu.video.other.NetWorkUtils;
import com.yanxiu.video.other.PlayerViewListener;


import static com.google.android.exoplayer2.C.TIME_UNSET;
import static com.google.android.exoplayer2.ExoPlayer.STATE_ENDED;
import static com.yanxiu.video.CourseVideoModel.SuiTangLian_State.Answer_Correct;
import static com.yanxiu.video.CourseVideoModel.SuiTangLian_State.Answer_Wrong;


/**
 * player管理类
 */
public class LSTCourseVideoManager {
    private final String TAG = "newVideoPlayer";

    /**
     * video状态
     * Normal 正常（默认）
     * Loading 加载中
     * NetworkError 网络异常
     * NotFoundError 没有该视频文件--本地文件无法播放或格式无法识别
     * LastVideoFinished 最后一个视频播完了
     * FourG 移动网络
     * SuiTangLian 显示随堂练
     */
    public enum VideoState {
        Normal,
        Loading,
        NetworkError,
        NotFoundError,
        LastVideoFinished,
        FourG,
        SuiTangLian
    }

    private VideoState state = VideoState.Normal;

    public boolean isUserWantPlayWhenReady() {
        return userWantPlayWhenReady;
    }

    private boolean userWantPlayWhenReady = true;//用户是否希望播放--即是否播放视频
    private boolean realPlayWhenReady = true;//暂时感觉没用

    public interface OnCourseEventListener {
        /**
         * 点击旋转按钮，在回调里控制屏幕旋转
         */
        void onRotate();

        /**
         * 片头播放完毕（业务需求：同一个list里的所有视频共享一个片头，只要有一个片头播放完毕，这个list里的所有视频都不在播放片头）
         */
        void onHeadFinish();

        /**
         * 正片播放完毕
         */
        void onBodyFinish();

        /**
         * 从第一个视频重新播放
         */
        void onReplayFromFirstVideo();
    }

    private OnCourseEventListener listener;

    public void setOnCourseEventListener(OnCourseEventListener listener) {
        this.listener = listener;
    }

    private CourseVideoModel model;

    private SimpleExoPlayer headPlayer;//片头播放器
    private SimpleExoPlayer bodyPlayer;//正片播放器

    public PlayerView getPlayerView() {
        return playerView;
    }

    private PlayerView playerView;
    private PlaybackControllerView controllerPlaceholderView;

    private Context context;
    private Handler mainHandler;
    private DataSource.Factory dataSourceFactory;
    private ExtractorsFactory extractorsFactory;

    final private HeadListener headListener = new HeadListener();
    final private BodyListener bodyListener = new BodyListener();

    private PlayerViewListener playerViewListener;

    public void setPlayerViewListener(PlayerViewListener playerViewListener) {
        this.playerViewListener = playerViewListener;
    }

    public LSTCourseVideoManager(Context context, PlayerView playerView) {
        this.context = context;
        this.playerView = playerView;

        setupVideoStateViewClickListeners();
    }

    public void initPlayer() {
        // 1. Create a default TrackSelector
        mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        TrackSelector trackSelector2 =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        // 2. Create the player
        headPlayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(context), trackSelector);
        headPlayer.addListener(headListener);

        //bodyPlayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(context), trackSelector2);
        DefaultLoadControl lc = new DefaultLoadControl(new DefaultAllocator(true, 64 * 1024), 3000, 30000, 3000, 3000);
        bodyPlayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(context), trackSelector2, lc);
        bodyPlayer.addListener(bodyListener);

        // Produces DataSource instances through which media data is loaded.
        dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "aVideoPlay"), (TransferListener<? super DataSource>) bandwidthMeter);
        // Produces Extractor instances for parsing the media data.
        extractorsFactory = new DefaultExtractorsFactory();
    }

    public void clearPlayer() {
        if (headPlayer != null) {
            headPlayer.release();
            headPlayer = null;
        }
        if (bodyPlayer != null) {
            bodyPlayer.release();
            bodyPlayer = null;
        }

        handler.removeCallbacks(updateProgressAction);
    }

    /**
     * 记录播放器当前暂停状态
     */
    public void recordPlayPauseState() {
        if (playerView != null && playerView.getPlayer() != null)
            realPlayWhenReady = playerView.getPlayer().getPlayWhenReady();
    }

    public void resetAllState() {
        state = VideoState.Normal;
        userWantPlayWhenReady = true;
        realPlayWhenReady = true;
    }

    public VideoState getState() {
        return state;
    }

    /**
     * 设置video的状态
     * 具体参见{@link VideoState}
     *
     * @param state
     */
    public void setState(VideoState state) {
        this.state = state;
        playerView.setVideoState(state);

        if ((state != VideoState.Normal) && (state != VideoState.Loading)) {
            playerView.getPlayer().setPlayWhenReady(false);
        }

        // 每次状态变化后，记录当下playWhenReady
        realPlayWhenReady = playerView.getPlayer().getPlayWhenReady();

        if (state == VideoState.FourG) {
            // 为了一点流量都不偷跑
            playerView.getPlayer().stop();
        }
    }

    /**
     * 各种状态view的点击事件
     * 给网络错误、未找到视频、都播放完毕等，添加点击事件
     */
    private void setupVideoStateViewClickListeners() {
//        playerView.findViewById(R.id.network_error_view).setOnClickListener(view -> {
//            stopAndRecoverCurrentVideo(true);
//        });
        //网络错误
        playerView.findViewById(R.id.network_error_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAndRecoverCurrentVideo(true);
            }
        });
        //未找到视频
        playerView.findViewById(R.id.not_found_error_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAndRecoverCurrentVideo(true);
            }
        });
        //都播放完毕，重头播放
//        playerView.findViewById(R.id.last_video_finished_view).setOnClickListener(view -> {
//            if (listener != null) {
//                listener.onReplayFromFirstVideo();
//            }
//        });
        playerView.findViewById(R.id.last_video_finished_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onReplayFromFirstVideo();
            }
        });
        //非wifi是否继续播放
//        playerView.findViewById(R.id.four_g_view).setOnClickListener(view -> {
//            stopAndRecoverCurrentVideo(false);
//        });
        playerView.findViewById(R.id.four_g_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAndRecoverCurrentVideo(false);
            }
        });
    }

    /**
     * 播放
     */
    private void playWithModel() {
        if ((model.headUrl != null) && (!model.isHeadFinished)) {
            headPlayer.prepare(mediaSourceFromUrl(model.headUrl), true, true);
            headPlayer.seekTo(model.headPosition);
        }

        if (model.bodyUrl != null) {
            bodyPlayer.prepare(mediaSourceFromUrl(model.bodyUrl), true, true);
            bodyPlayer.seekTo(model.bodyPosition);
        }

        if ((model.headUrl != null) && (!model.isHeadFinished)) {
            //播放片头
            playerView.setPlayer(headPlayer);
            headPlayer.setPlayWhenReady(true);
            bodyPlayer.setPlayWhenReady(false);
        } else {
            //播放正片
            playerView.setPlayer(bodyPlayer);
            bodyPlayer.setPlayWhenReady(true);
        }

        updatePortraitLandscapeControllerView();
    }

    public CourseVideoModel getModel() {
        return model;
    }

    /**
     * 设置播放数据
     *
     * @param model
     */
    public void setData(CourseVideoModel model) {
        if ((model.bodyDuration != -1) && (model.bodyDuration <= model.bodyPosition + 1000) && (model.bodyDuration != TIME_UNSET)) {
            model.bodyPosition = 0;
        }
        this.model = model;
        playWithModel();
        stopAndRecoverCurrentVideo(true);
    }

    private void replaceControllerView(PlaybackControllerView controllerView) {
        if (controllerPlaceholderView == null) {
            controllerPlaceholderView = (PlaybackControllerView) playerView.findViewById(R.id.exo_controller_placeholder);
        }

        controllerView.setLayoutParams(controllerPlaceholderView.getLayoutParams());
        ViewGroup parent = ((ViewGroup) controllerPlaceholderView.getParent());
        int controllerIndex = parent.indexOfChild(controllerPlaceholderView);
        parent.removeView(controllerPlaceholderView);
        parent.addView(controllerView, controllerIndex);

        controllerPlaceholderView = controllerView;
        playerView.setControllerView(controllerPlaceholderView);

        controllerView.getRotateButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onRotate();
                }
            }
        });

        controllerView.setControlDispatcher(new PlaybackControllerView.ControlDispatcher() {
            @Override
            public boolean dispatchSetPlayWhenReady(ExoPlayer player, boolean playWhenReady) {
                userWantPlayWhenReady = playWhenReady;
                if (playWhenReady) {
                    if (hasDealtWithFourG()) {
                        return false;
                    }
                }

                player.setPlayWhenReady(playWhenReady);
                return true;
            }

            @Override
            public boolean dispatchSeekTo(ExoPlayer player, int windowIndex, long positionMs) {
                player.seekTo(windowIndex, positionMs);
                return true;
            }
        });
    }

    /**
     * 播放正片
     *
     * @param isHeadFinished 片头是否播放完成
     */
    private void goPlayBody(boolean isHeadFinished) {
        model.isHeadFinished = isHeadFinished;
        bodyPlayer.prepare(mediaSourceFromUrl(model.bodyUrl), false, true);
        playerView.setPlayer(bodyPlayer);
        bodyPlayer.setPlayWhenReady(true);

        updatePortraitLandscapeControllerView();

        if ((listener != null) && (isHeadFinished)) {
            listener.onHeadFinish();
        }
    }

    private void stopAndRecoverCurrentVideo(boolean considerFourG) {
        if (considerFourG && hasDealtWithFourG()) {
            return;
        }

        // 由于以下bug，所以每次都stop了再重新来，否则网络切换过程中，一定概率一直显示加载 （STATE_BUFFERING）
        // bug : https://github.com/google/ExoPlayer/issues/911
        playerView.getPlayer().stop();
        if (playerView.getPlayer() == headPlayer) {
            playerView.getPlayer().prepare(mediaSourceFromUrl(model.headUrl), true, true);
            playerView.getPlayer().seekTo(model.headPosition);
        }
        if (playerView.getPlayer() == bodyPlayer) {
            playerView.getPlayer().prepare(mediaSourceFromUrl(model.bodyUrl), true, true);
            playerView.getPlayer().seekTo(model.bodyPosition);
        }
        setState(VideoState.Normal);
        playerView.getPlayer().setPlayWhenReady(userWantPlayWhenReady);
    }

    /*
     *视频状态监听 开始
     */

    /**
     * region 片头，正片状态listeners基类
     */
    class LSTVideoListener implements ExoPlayer.EventListener {
        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            updateProgress();

            if (playbackState == ExoPlayer.STATE_BUFFERING) {
                state = VideoState.Loading;
                playerView.setVideoState(VideoState.Loading);
            } else {
                state = VideoState.Normal;
                playerView.setVideoState(VideoState.Normal);
            }
        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {
            // 展示Error界面
            if (error.getCause() instanceof FileDataSource.FileDataSourceException) {
                //在本地文件读取操作期间遇到IOException时抛出
                setState(VideoState.NotFoundError);
            }

            if (error.getCause() instanceof UnrecognizedInputFormatException) {
                //格式无法识别
                setState(VideoState.NotFoundError);
            }

            if (error.getCause() instanceof HttpDataSource.HttpDataSourceException) {
                //网络出错
                setState(VideoState.NetworkError);
            }
        }

        //region 没用
        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest) {
        }

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
        }

        @Override
        public void onLoadingChanged(boolean isLoading) {
        }

        @Override
        public void onPositionDiscontinuity() {
        }

        @Override
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        }
        //endregion
    }

    /**
     * 片头状态监听
     */
    final class HeadListener extends LSTVideoListener {
        @Override
        public void onPlayerError(ExoPlaybackException error) {
            Log.e(TAG, "head error" + error);
            if (NetWorkUtils.isNetworkAvailable(context)) {
                // 跳过片头，播放正片
                goPlayBody(false);
                return;
            }

            if (playerView.getPlayer() == headPlayer) {
                // 只处理当前player的error
                super.onPlayerError(error);
            }
        }

        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            if ((state != VideoState.Loading) && (state != VideoState.Normal)) {
                return;
            }

            Log.e(TAG, "head state change to : " + playbackState);
            super.onPlayerStateChanged(playWhenReady, playbackState);
            if (playbackState == STATE_ENDED) {
                goPlayBody(true);
            }
        }
    }

    /**
     * 正片状态监听
     */
    final class BodyListener extends LSTVideoListener {
        @Override
        public void onPlayerError(ExoPlaybackException error) {
            Log.e(TAG, "error");
            if (playerView.getPlayer() == bodyPlayer) {
                // 只处理当前player的error
                super.onPlayerError(error);
            }
        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            if ((state != VideoState.Loading) && (state != VideoState.Normal)) {
                return;
            }
            super.onPlayerStateChanged(playWhenReady, playbackState);
            if ((playbackState == STATE_ENDED) && (listener != null)) {
                listener.onBodyFinish();
            }
        }
    }
    /*
     * 视频状态监听 结束
     */

    /*
     * 旋转屏 开始
     */
    public boolean isPortrait = true;//是否竖屏

    /**
     * 更改横竖屏控制栏
     */
    public void updatePortraitLandscapeControllerView() {
        if (playerView == null || model == null)
            return;
        boolean isHead = (playerView.getPlayer() == headPlayer ? true : false);
        if (isPortrait && isHead) {
            setupPortraitHeadControllerView();
        }

        if (!isPortrait && isHead) {
            setupLandscapeHeadControllerView();
        }

        if (isPortrait && !isHead) {
            setupPortraitBodyControllerView();
        }

        if (!isPortrait && !isHead) {
            setupLandscapeBodyControllerView();
        }
    }

    /**
     * 设置竖屏的片头控制栏
     */
    private void setupPortraitHeadControllerView() {
        LSTHeadPlaybackControllerView controllerView = new LSTHeadPlaybackControllerView(context);
        controllerView.findViewById(R.id.top_layout).setVisibility(View.GONE);
        controllerView.rotateState(true, false);
        replaceControllerView(controllerView);
        controllerView.setPlayer(headPlayer);
        controllerView.show();
    }

    /**
     * 设置横屏的片头控制栏
     */
    private void setupLandscapeHeadControllerView() {
        LSTHeadPlaybackControllerView controllerView = new LSTHeadPlaybackControllerView(context);
        controllerView.findViewById(R.id.top_layout).setVisibility(View.VISIBLE);
        controllerView.rotateState(true, true);
        controllerView.setTitle(model.title);
        controllerView.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerViewListener != null)
                    playerViewListener.onPlayerBackViewClick();
            }
        });
        replaceControllerView(controllerView);
        controllerView.setPlayer(headPlayer);
        controllerView.show();

    }

    /**
     * 设置竖屏的正片控制栏
     */
    private void setupPortraitBodyControllerView() {
        LSTBodyPlaybackControllerView controllerView = new LSTBodyPlaybackControllerView(context);
        controllerView.findViewById(R.id.top_layout).setVisibility(View.GONE);
        controllerView.rotateState(false, false);
        replaceControllerView(controllerView);
        controllerView.setPlayer(bodyPlayer);
        controllerView.show();
    }

    /**
     * 设置横屏的正片控制栏
     */
    private void setupLandscapeBodyControllerView() {
        LSTBodyPlaybackControllerView controllerView = new LSTBodyPlaybackControllerView(context);
        controllerView.findViewById(R.id.top_layout).setVisibility(View.VISIBLE);
        controllerView.rotateState(false, true);
        controllerView.setClarityText(model.clarity);
        controllerView.setTitle(model.title);
        controllerView.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerViewListener != null)
                    playerViewListener.onPlayerBackViewClick();
            }
        });
        replaceControllerView(controllerView);
        controllerView.setPlayer(bodyPlayer);
        controllerView.show();
    }
    /*
     * 旋转屏 结束
     */

    /*
     * 随堂练 相关 开始
     */
    private Handler handler = new Handler();
    private final Runnable updateProgressAction = new Runnable() {
        @Override
        public void run() {
            updateProgress();
        }
    };

    /**
     * 更新播放进度
     * 1.把播放进度直接写进model里
     * 2.控制弹出随堂练等
     */
    private void updateProgress() {
        long position = bodyPlayer.getCurrentPosition();
        model.headPosition = headPlayer.getCurrentPosition();
        model.bodyPosition = position;
        model.bodyDuration = bodyPlayer.getDuration();
        int playbackState = bodyPlayer == null ? ExoPlayer.STATE_IDLE : bodyPlayer.getPlaybackState();

        boolean isHead = (playerView.getPlayer() == headPlayer ? true : false);//是否为片头，片头不计时
        if (!isHead && playerViewListener != null && playbackState != ExoPlayer.STATE_IDLE
                && playbackState != ExoPlayer.STATE_ENDED && playerView.getPlayer().getPlayWhenReady()) {

            playerViewListener.onPlayerUpdateProgress();

        }
        reachSuiTangLian(position);
        handler.removeCallbacks(updateProgressAction);
        if (playbackState != ExoPlayer.STATE_IDLE && playbackState != ExoPlayer.STATE_ENDED) {
            long delayMs = 1000;
            handler.postDelayed(updateProgressAction, delayMs);
        }
    }

    /**
     * 判断随堂练是否显示
     *
     * @param position
     */
    private void reachSuiTangLian(long position) {
        if ((state != VideoState.Normal) && (state != VideoState.Loading)) {
            return;
        }

        if (!playerView.getPlayer().getPlayWhenReady()) {
            // 没有播放的状态下不弹出随堂练
            return;
        }

        if (playerViewListener != null)
            playerViewListener.onPlayerShowSuiTangLian();

//        for (CourseVideoModel.SuiTangLianModel model : this.model.suiTangLianItems) {
//            if ((position > model.position - 5000) && (position < model.position + 5000)) {
//                popSuiTangLian(model);
//                return;
//            }
//        }
    }

    /**
     * 弹出随堂练
     *
     * @param model
     */
    private void popSuiTangLian(final CourseVideoModel.SuiTangLianModel model) {
        Log.e(TAG, "reach sui tang lian : " + model.position);
        if (model.state != Answer_Correct) {
            setState(VideoState.SuiTangLian);

            final PopupWindow popupWindow = new PopupWindow(context);
            popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            popupWindow.setContentView(LayoutInflater.from(context).inflate(R.layout.player_suitanglian_answer_view, null));
            popupWindow.setBackgroundDrawable(new ColorDrawable(0x00333333));
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);
            // TBD : cailei
            Activity a = (Activity) context;
//            popupWindow.showAtLocation(a.findViewById(R.id.root_laytout), Gravity.CENTER, 0, 0);--dyf这需要

//            popupWindow.getContentView().findViewById(R.id.answer_correct).setOnClickListener(view -> {
//                model.state = Answer_Correct;
//                popupWindow.dismiss();
//                setState(Normal);
//                playerView.getPlayer().setPlayWhenReady(userWantPlayWhenReady);
//            });
            popupWindow.getContentView().findViewById(R.id.answer_correct).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model.state = Answer_Correct;
                    popupWindow.dismiss();
                    setState(VideoState.Normal);
                    playerView.getPlayer().setPlayWhenReady(userWantPlayWhenReady);
                }
            });
//            popupWindow.getContentView().findViewById(R.id.answer_wrong).setOnClickListener(view -> {
//                model.state = Answer_Wrong;
//                popupWindow.dismiss();
//                setState(Normal);
//                seekToSuitableSuiTangLian(model);
//            });
            popupWindow.getContentView().findViewById(R.id.answer_wrong).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model.state = Answer_Wrong;
                    popupWindow.dismiss();
                    setState(VideoState.Normal);
                    seekToSuitableSuiTangLian(model);
                }
            });
        }
    }

    /**
     * 随堂练回答错了，seek到上一个随堂练的时间点继续播放
     * 随堂练需求：
     * 当前随堂练，如果回答正确，继续播放；
     * 如果回答错误，返回到上一个回答正确的随堂练的时间点重新播放；如果前面没有答对的随堂练，那从0开始重新播放
     *
     * @param model
     */
    private void seekToSuitableSuiTangLian(CourseVideoModel.SuiTangLianModel model) {
        long position = 0;
        for (CourseVideoModel.SuiTangLianModel m : this.model.suiTangLianItems) {
            if (m == model) {
                break;
            }
            if (m.state == Answer_Correct) {
                position = m.position;
            }
        }
        playerView.getPlayer().seekTo(position);
        playerView.getPlayer().setPlayWhenReady(userWantPlayWhenReady);
    }
     /*
     * 随堂练 相关 结束
     */

    /*
     * 移动网wifi 相关 开始
     */
    private boolean hasDealtWithFourG() {
        if (NetWorkUtils.isNetworkAvailable(context) && !NetWorkUtils.isWifi(context)) {
            setState(VideoState.FourG);
            return true;
        }

        return false;
    }

    /**
     * 网络状态切换为4G
     */
    public void networkChangeToFourG() {
        if ((state != VideoState.Normal) && (state != VideoState.Loading)) {
            return;
        }

        setState(VideoState.FourG);
    }
    /*
     *移动网wifi 相关 结束
     */

    /**
     * 创建mp4、m3u8等格式的MediaSource，prepare需要
     */
    private MediaSource mediaSourceFromUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            //处理url为空时，给其""值，让其显示未找到该视频
            url = "";
        }

        Uri uri = Uri.parse(url);
        if (VideoTypeParser.Type.M3U8 == VideoTypeParser.getTypeForUri(uri)) {
            MediaSource m3u8 = new HlsMediaSource(uri, dataSourceFactory, mainHandler, null);
            return m3u8;
        }

        if (VideoTypeParser.Type.MP4 == VideoTypeParser.getTypeForUri(uri)) {
            MediaSource mp4 = new ExtractorMediaSource(uri, dataSourceFactory, extractorsFactory, null, null);
            return mp4;
        }

//        // 希望Extractor能捕获其余漏网之鱼
//        MediaSource defaultMediaSource = new ExtractorMediaSource(uri, dataSourceFactory, extractorsFactory, null, null);
        // 希望Extractor能捕获其余漏网之鱼，默认m3u8格式，研修宝都是m3u8格式
        MediaSource defaultMediaSource = new HlsMediaSource(uri, dataSourceFactory, mainHandler, null);
        return defaultMediaSource;
    }

    /**
     * 获取当前播放时间点
     *
     * @return
     */
    public int getCurrentPosition() {
        if (playerView != null && playerView.getPlayer() != null) {
            return (int) playerView.getPlayer().getCurrentPosition();
        }
        return -1;
    }

    /**
     * 获取视频总时间长度
     *
     * @return
     */
    public int getDuration() {
        if (playerView != null && playerView.getPlayer() != null) {
            return (int) playerView.getPlayer().getDuration();
        }
        return -1;
    }

    /**
     * 当前真正的播放状态
     *
     * @return
     */
    public boolean isRealPlayWhenReady() {
        return realPlayWhenReady;
    }
}