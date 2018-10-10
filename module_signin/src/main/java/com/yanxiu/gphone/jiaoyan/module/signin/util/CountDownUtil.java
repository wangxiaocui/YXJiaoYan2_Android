package com.yanxiu.gphone.jiaoyan.module.signin.util;

import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.yanxiu.gphone.jiaoyan.module.signin.R;

/**
 * 倒计时工具类
 * Created by Hu Chao on 18/10/10.
 */
public class CountDownUtil {

    private CountDownTimer mCountDownTimer;
    private TextView mTextView;
    private CharSequence defaultText;

    /**
     * @param textView          倒计时view
     * @param millisInFuture    倒计时时长，毫秒long
     * @param countDownInterval 倒计时间隔时长，毫秒long
     */
    public CountDownUtil(TextView textView, long millisInFuture, long countDownInterval) {
        this.mTextView = textView;
        defaultText = mTextView.getText();
        mCountDownTimer = new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (mTextView != null) {
                    mTextView.setTextColor(ContextCompat.getColor(mTextView.getContext(), R.color.color_007aff));
                    mTextView.setText(String.format("%ds", (int) millisUntilFinished / 1000));
                }
            }

            @Override
            public void onFinish() {
                resetTextView();
            }
        };
    }

    public final void start() {
        if (mTextView != null) {
            mTextView.setEnabled(false);
        }
        if (mCountDownTimer != null) {
            mCountDownTimer.start();
        }
    }

    public final void cancel() {
        resetTextView();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    private void resetTextView() {
        if (mTextView != null) {
            mTextView.setTextColor(ContextCompat.getColorStateList(mTextView.getContext(), R.color.signin_get_code_text_color_selector));
            mTextView.setText(defaultText);
            mTextView.setEnabled(true);
        }
    }

}
