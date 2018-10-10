package com.yanxiu.gphone.jiaoyan.customize;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanxiu.gphone.jiaoyan.R;

import java.util.ArrayList;

import static android.view.Gravity.CENTER;

/**
 * Created by Cai Lei on 2018/9/29.
 */

// OptionsViewGroup类，用于BottomSheet弹出选择
public class OptionsViewGroup extends ViewGroup {
    /**
     * 初始化各个选项
     * @param options 选项名称数组
     * @param cancel 取消键名称，如果传入null则没有取消选项
     */
    public void setupWith(String[] options, String cancel) {
        if ((options == null) || (options.length == 0)) {
            return;
        }

        mOptions = options;
        mCancel = cancel;
        mOptionViews = new ArrayList<>();
        mCancelView = null;
        removeAllViews();

        for (int i = 0; i < mOptions.length; i++) {
            TextView tv = new TextView(mContext);
            tv.setGravity(CENTER);
            tv.setText(mOptions[i]);
            mOptionViews.add(tv);
            addView(tv);
            tv.setTag(i);
            tv.setOnClickListener(onClickListener);
            tv.setBackgroundColor(getResources().getColor(R.color.color_white));
        }

        if (cancel != null) {
            mCancelView = new TextView(mContext);
            mCancelView.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            mCancelView.setGravity(CENTER);
            mCancelView.setText(cancel);
            addView(mCancelView);
            mCancelView.setTag(Integer.MAX_VALUE);
            mCancelView.setOnClickListener(onClickListener);
            mCancelView.setBackgroundColor(getResources().getColor(R.color.color_white));
        }

        requestLayout();
    }

    /**
     * 设置回调
     * @param callback
     */
    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void onCancel();
        void onSelect(int index);
    }

    private Callback mCallback;

    private Context mContext;
    private String[] mOptions;
    private String mCancel = null;
    private int mRowHeight = 180;
    private int mCancelPadding = 20;
    private ArrayList<TextView> mOptionViews;
    private TextView mCancelView;
    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (((Integer) view.getTag() == Integer.MAX_VALUE) && (mCallback != null)) {
                mCallback.onCancel();
            } else if (mCallback != null) {
                mCallback.onSelect((Integer) view.getTag());
            }
        }
    };

    public OptionsViewGroup(Context context) {
        super(context);
        mContext = context;
    }

    public OptionsViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public OptionsViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if ((mOptions == null) || (mOptions.length == 0)) {
            setMeasuredDimension(0, 0);
        }

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        height = mOptions.length * (mRowHeight + 1);
        if (mCancelView != null) {
            height += (mCancelPadding + mRowHeight);
        } else {
            height -= 1;
        }

        setMeasuredDimension(MeasureSpec.makeMeasureSpec(width, widthMode),
                MeasureSpec.makeMeasureSpec(height, heightMode));

        // https://stackoverflow.com/questions/40387093/textview-setgravity-not-working-ignored-with-viewgroup-programmatically/40556644#40556644
        // 这两句保证vertical居中
        for (TextView tv : mOptionViews) {
            tv.measure(MeasureSpec.makeMeasureSpec(width, widthMode), MeasureSpec.makeMeasureSpec(mRowHeight, MeasureSpec.EXACTLY));
        }
        mCancelView.measure(MeasureSpec.makeMeasureSpec(width, widthMode), MeasureSpec.makeMeasureSpec(mRowHeight, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)  {
        int curHeight = 0;

        for (int i = 0; i < mOptionViews.size(); i++) {
            TextView tv = mOptionViews.get(i);
            tv.layout(l, curHeight, r, curHeight + mRowHeight);

            curHeight += mRowHeight;
            curHeight += 1;
        }

        if (mCancelView != null) {
            curHeight += mCancelPadding;
            mCancelView.layout(l, curHeight, r, curHeight + mRowHeight);
        }
    }
}
