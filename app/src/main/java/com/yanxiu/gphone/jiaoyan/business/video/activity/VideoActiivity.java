package com.yanxiu.gphone.jiaoyan.business.video.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.test.yanxiu.common_base.utils.keyboard.InputMethodUtil;
import com.test.yanxiu.common_base.utils.keyboard.KeyboardChangeListener;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.video.fragment.VideoInfoFragment;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.base.bean.YXBaseBean;
import com.yanxiu.lib.yx_basic_library.util.YXToastUtil;
import com.yanxiu.video.CourseVideoModel;
import com.yanxiu.video.LSTCourseVideoManager;
import com.yanxiu.video.PlayerView;
import com.yanxiu.video.other.PlayerViewListener;
import com.yanxiu.video.other.ScreenOrientationSwitcher;

/**
 * 视频页面
 * Created by 戴延枫 on 2018/10/18.
 */
@Route(path = RoutePathConfig.Video_Activity)
public class VideoActiivity extends JYBaseActivity implements LSTCourseVideoManager.OnCourseEventListener, PlayerViewListener, KeyboardChangeListener.KeyBoardListener {

    // region 播放器view

    private View mVideoLayout;//播放器layout

    private PlayerView mPlayerView;//播放器view
    private LSTCourseVideoManager mVideoManger;//播放器控制类
    private CourseVideoModel mCurrentVideoModel;//播放器数据封装类
    private boolean isFullscreen;
    private int mCachedHeight;
    //endregion 播放器view

    // region 视频详情view

    private VideoInfoFragment videoInfoFragment;
    //endregion 视频详情view

    // region 提问view

    private KeyboardChangeListener mKeyboardChangeListener;
    private boolean isFromVideoRotation = false;//播放器切换横竖屏时，也会触发键盘监听事件，需要排除该情况
    private Button bt_question_commit; //底部提交问题按钮
    private View layout_question_commit;//输入内容layout
    private EditText et_question_content;//问题输入框
    private TextView tv_question_commit;//真正的提交按钮
    private String mQuestionContent;//提问或回复内容
    //endregion 提问view

    //region 基类回调

    /**
     * 绑定布局
     *
     * @return 布局 Id
     */
    @Override
    public int bindLayout() {
        return R.layout.video_activity;
    }

    /**
     * 初始化数据
     *
     * @param bundle 传递过来的 bundle
     */
    @Override
    public void initData(@NonNull Bundle bundle) {
        super.initTitle();
        getJyDefaultToolbar().addRightIcon(View.generateViewId(), R.drawable.homepage_my, 20, 20, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YXToastUtil.showToast("right");
            }
        });
    }

    /**
     * 初始化 view
     *
     * @param savedInstanceState
     * @param contentView
     */
    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        initPlayerView();
        initVideoInfoView();
        intCommitView();
    }

    /**
     * 初始化 监听
     */
    @Override
    public void initListener() {
        bt_question_commit.setOnClickListener(this);
        tv_question_commit.setOnClickListener(this);
        et_question_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mQuestionContent = editable.toString();
                if (TextUtils.isEmpty(mQuestionContent)) {
                    tv_question_commit.setEnabled(false);
                } else {
                    tv_question_commit.setEnabled(true);
                }
            }
        });

        mKeyboardChangeListener = new KeyboardChangeListener(this);
        mKeyboardChangeListener.setKeyBoardListener(this);

        setupRotationListener();
    }

    /**
     * 视图点击事件
     *
     * @param view 视图
     */
    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.bt_question_commit:
                showCommitView(true);
                break;
            case R.id.tv_question_commit:
                YXToastUtil.showToast("haha");
                break;
        }
    }

    /**
     * 业务操作
     */
    @Override
    public void doBusiness() {
        mockPlay();
    }

    private void mockPlay(){
        CourseVideoModel m = new CourseVideoModel();
        m.headPosition = 0;
        m.bodyUrl = "http://upload.ugc.yanxiu.com/video/4620490456e684328d4fcf5a920f54a1.mp4";
        m.bodyPosition = 0;
        m.isHeadFinished = false;

        mVideoManger.clearPlayer();
        mVideoManger.initPlayer();
        mVideoManger.resetAllState();
        mVideoManger.setData(m);
    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }

    //endregion 基类回调

    // region 播放器

    private void initPlayerView() {
        mVideoLayout = findViewById(R.id.video_layout);
        mPlayerView = findViewById(R.id.player_view);
        mVideoManger = new LSTCourseVideoManager(this, mPlayerView);
        mVideoManger.initPlayer();
        mCurrentVideoModel = new CourseVideoModel();
        mVideoManger.setOnCourseEventListener(this);
        mVideoManger.setPlayerViewListener(this);
        //end
        setVideoAreaSize();

//        mCourseImgView = (ImageView) findViewById(R.id.course_img);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);   //应用运行时，保持屏幕高亮，不锁屏
    }

    /**
     * 置视频区域大小
     */
    private void setVideoAreaSize() {
        mVideoLayout.post(new Runnable() {
            @Override
            public void run() {
                int width = mVideoLayout.getWidth();
                mCachedHeight = (int) (width * 420f / 750f);
                ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = mCachedHeight;
                mVideoLayout.setLayoutParams(videoLayoutParams);
            }
        });
    }


    /**
     * 播放器titlebar里的返回按钮点击事件
     */
    @Override
    public void onPlayerBackViewClick() {
        if (isFullscreen) {
            rotateScreen();
        } else {
            finish();
        }
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
        rotateScreen();
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

    }

    /**
     * 从第一个视频重新播放
     */
    @Override
    public void onReplayFromFirstVideo() {

    }

    /*
     * 全屏半屏切换相关 代码区域开始
     */
    private ScreenOrientationSwitcher mOrientationSwitcher;

    /**
     * 设置横竖屏切换回调
     */
    private void setupRotationListener() {
        try {
            getSupportActionBar().hide();//有可能为空
        } catch (Exception e) {
            e.printStackTrace();
        }
        mOrientationSwitcher = new ScreenOrientationSwitcher(this);
        mOrientationSwitcher.setChangeListener(new ScreenOrientationSwitcher.OnChangeListener() {
            // 重力感应旋转
            @Override
            public void onChanged(int requestedOrientation) {
                isFromVideoRotation = true;

                if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) { //竖屏
                    setRequestedOrientation(requestedOrientation);
                    setPortraitStyle();
                }

                if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) { //横屏
                    setRequestedOrientation(requestedOrientation);
                    setLandscapeStyle();
                }

                if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) { //Activity在横向屏幕上显示，但与正常的横向屏幕方向相反
                    setRequestedOrientation(requestedOrientation);
                    setLandscapeStyle();
                }
            }
        });
        mOrientationSwitcher.enable();
    }

    /**
     * 主动点击旋转
     */
    private void rotateScreen() {
        if (mVideoManger.isPortrait) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setLandscapeStyle();
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setPortraitStyle();
        }
    }

    /**
     * 设置竖屏
     */
    private void setPortraitStyle() {
        this.isFullscreen = false;
        mVideoManger.isPortrait = true;
        mVideoManger.updatePortraitLandscapeControllerView();

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(this, 250));
//        mVideoManger.getPlayerView().setLayoutParams(params);

        ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = this.mCachedHeight;
        mVideoLayout.setLayoutParams(layoutParams);

    }

    /**
     * 设置横屏
     */
    private void setLandscapeStyle() {
        this.isFullscreen = true;
        mVideoManger.isPortrait = false;
        mVideoManger.updatePortraitLandscapeControllerView();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        mVideoManger.getPlayerView().setLayoutParams(params);

        ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        mVideoLayout.setLayoutParams(layoutParams);
    }

    /*
     * 全屏半屏切换相关 代码区域结束
     */

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
    //endregion 播放器

    //region 视频详情
    private void initVideoInfoView() {
        videoInfoFragment = RouteUtils.getFramentByPath(RoutePathConfig.Video_Activity_Fragment, new YXBaseBean());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.video_info_container, videoInfoFragment)
                .commitAllowingStateLoss();
    }
    //endregion 视频详情

    // region 提交问题

    /**
     * 初始化提交问题view
     */
    private void intCommitView() {
        bt_question_commit = findViewById(R.id.bt_question_commit);
        layout_question_commit = findViewById(R.id.layout_question_commit);
        et_question_content = findViewById(R.id.et_question_content);
        tv_question_commit = findViewById(R.id.tv_question_commit);

        showCommitView(false);
    }

    private void showCommitView(boolean showCommitView) {
        if (showCommitView) {
            bt_question_commit.setVisibility(View.GONE);
            layout_question_commit.setVisibility(View.VISIBLE);
            et_question_content.requestFocus();
            InputMethodUtil.openInputMethod(et_question_content);
            if (TextUtils.isEmpty(mQuestionContent)) {
                tv_question_commit.setEnabled(false);
            } else {
                tv_question_commit.setEnabled(true);
            }
        } else {
            bt_question_commit.setVisibility(View.VISIBLE);
            layout_question_commit.setVisibility(View.GONE);
            et_question_content.clearFocus();
            InputMethodUtil.closeInputMethod(VideoActiivity.this, et_question_content);
        }
    }
    // endregion 提交问题

    /**
     * call back
     *
     * @param isShow         true is show else hidden
     * @param keyboardHeight keyboard height
     */
    @Override
    public void onKeyboardChange(boolean isShow, int keyboardHeight) {
//        if (isFromVideoRotation) {
//            isFromVideoRotation = false;
//        } else {
            if(!isShow)
            showCommitView(isShow);
//        }
    }
}
