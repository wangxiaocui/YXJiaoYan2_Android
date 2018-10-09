package com.yanxiu.video.other;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yanxiu.video.R;


/**
 * 播放器errorView
 * Created by 戴延枫 on 2017/10/16.
 */

public class VideoPlayerErrorView extends FrameLayout {

    private Context mContext;
    private TextView mTextView;// 错误信息

    private TextView mButton;//按钮

    public VideoPlayerErrorView(Context context) {
        this(context, null);
    }

    public VideoPlayerErrorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoPlayerErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    /**
     * 初始化view
     */
    private void initView(Context context, AttributeSet attrs) {
        mContext = context;
        inflate(context, R.layout.videoplayer_error_layout, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.VideoPlayerErrorView);//TypedArray是一个数组容器
        String error_info = typedArray.getString(R.styleable.VideoPlayerErrorView_error_info_text);
        String error_button = typedArray.getString(R.styleable.VideoPlayerErrorView_error_button_text);
        mTextView = (TextView) findViewById(R.id.video_error_text);
        mButton = (TextView) findViewById(R.id.video_tv_button);
        if (!TextUtils.isEmpty(error_info) && !TextUtils.isEmpty(error_button)){
            mTextView.setText(error_info);
            mButton.setText(error_button);
        }
        setVisibility(GONE);
    }

    public TextView getmTextView() {
        return mTextView;
    }

    public TextView getmButton() {
        return mButton;
    }

}
