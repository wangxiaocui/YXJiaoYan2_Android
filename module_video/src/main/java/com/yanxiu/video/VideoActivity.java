package com.yanxiu.video;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.yanxiu.video.other.PlayerViewListener;

import static com.yanxiu.video.LSTCourseVideoManager.VideoState.Loading;
import static com.yanxiu.video.LSTCourseVideoManager.VideoState.Normal;


/**
 * 全屏播放页面
 * Created by 戴延枫 on 2018/1/2.
 */

public class VideoActivity extends AppCompatActivity implements LSTCourseVideoManager.OnCourseEventListener, PlayerViewListener {

    public static final String VIDEO_URL = "videoUrl";
    public static final String VIDEO_NAME = "videoName";

    private boolean mFromOnPause = false;

    private PlayerView mPlayerView;//播放器view
    private LSTCourseVideoManager mVideoManger;//播放器控制类
    private CourseVideoModel mCurrentVideoModel;//播放器数据封装类

    private String mVideoUrl;
    private String mVideoName;

    //上报
    private String mFrom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fullscreen_video);
        getDataFromIntent();
        if (TextUtils.isEmpty(mVideoUrl)) {
            finish();
            return;
        }
        initView();
        startPlayVideo();
//        getFrom();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mFromOnPause) {
            LSTCourseVideoManager.VideoState lastState = mVideoManger.getState();
            mVideoManger.initPlayer();
            mVideoManger.setData(mCurrentVideoModel);

            if ((lastState != Normal) && (lastState != Loading)) {
                mVideoManger.setState(lastState);
            }
            mFromOnPause = false;
        }
        try {
            //注册网络状态的广播，绑定到mReceiver
            IntentFilter mFilter = new IntentFilter();
            mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(mNetStateReceiver, mFilter);
        } catch (Exception e) {

        }
//        if(!TextUtils.isEmpty(mFrom))
//            TCAgent.onPageStart(this, mFrom);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFromOnPause = true;
        if (mCurrentVideoModel != null) {
            mVideoManger.recordPlayPauseState();
            mVideoManger.clearPlayer();
        }
//        if(!TextUtils.isEmpty(mFrom))
//            TCAgent.onPageEnd(this, mFrom);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //注销接收
        try {
            unregisterReceiver(mNetStateReceiver);
        } catch (Exception e) {

        }
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        mVideoUrl = intent.getStringExtra(VIDEO_URL);
        mVideoName = intent.getStringExtra(VIDEO_NAME);
    }

    private void initView() {
        mPlayerView = (PlayerView)findViewById(R.id.player_view);
        mVideoManger = new LSTCourseVideoManager(this, mPlayerView);
        mVideoManger.initPlayer();
        mCurrentVideoModel = new CourseVideoModel();
        mVideoManger.setOnCourseEventListener(this);
        mVideoManger.setPlayerViewListener(this);
        setLandscapeStyle();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);   //应用运行时，保持屏幕高亮，不锁屏
    }

    private void startPlayVideo() {
        mCurrentVideoModel.clarity = null;
        mCurrentVideoModel.bodyUrl = mVideoUrl;
        mCurrentVideoModel.bodyPosition = 0;
        mCurrentVideoModel.bodyDuration = -1;
        mCurrentVideoModel.title = mVideoName;
        mVideoManger.clearPlayer();
        mVideoManger.initPlayer();
        mVideoManger.resetAllState();
        mVideoManger.setData(mCurrentVideoModel);
    }

    /**
     * 设置播放器横屏
     */
    private void setLandscapeStyle() {
        mVideoManger.isPortrait = false;
        mVideoManger.updatePortraitLandscapeControllerView();
    }

    /**
     * 监听网络变化
     */
    private BroadcastReceiver mNetStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();

                if (info != null && info.isAvailable()) {
                    String name = info.getTypeName();
                    if (!name.equals("WIFI")) {
                        // 移动网络
                        if (mVideoManger != null && mVideoManger.getPlayerView() != null && mVideoManger.getPlayerView().getPlayer() != null) {
                            //防止在4g网络下，进入改页面，player还没生成，空指针异常
                            mVideoManger.networkChangeToFourG();
                        }

                    }
                }
            }
        }
    };

    /**
     * 播放器titlebar里的返回按钮点击事件
     */
    @Override
    public void onPlayerBackViewClick() {
        finish();
    }

    /**
     * 播放器播放计时(更新进度)回调
     */
    @Override
    public void onPlayerUpdateProgress() {

    }

    /**
     * 播放器弹出随堂练回调
     */
    @Override
    public void onPlayerShowSuiTangLian() {

    }

    /**
     * 点击旋转按钮，在回调里控制屏幕旋转
     */
    @Override
    public void onRotate() {

    }

    /**
     * 片头播放完毕（业务需求：同一个list里的所有视频共享一个片头，只要有一个片头播放完毕，这个list里的所有视频都不在播放片头）
     */
    @Override
    public void onHeadFinish() {

    }

    /**
     * 正片播放完毕
     */
    @Override
    public void onBodyFinish() {
        finish();
    }

    /**
     * 从第一个视频重新播放
     */
    @Override
    public void onReplayFromFirstVideo() {

    }

    /**
     * @param context
     * @param url
     * @param title
     */
    public static void invoke(Context context, String url, String title) {
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra(VIDEO_URL, url);
        intent.putExtra(VIDEO_NAME, title);
        context.startActivity(intent);
    }

}
