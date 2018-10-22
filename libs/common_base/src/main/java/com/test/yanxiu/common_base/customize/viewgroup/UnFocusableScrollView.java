package com.test.yanxiu.common_base.customize.viewgroup;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * 当scrollview里面嵌套有listview、recycler等有焦点的子view时，scrollview会自动滚动到该子view的位置，为了不让其自动滚动，重写scrollview
 */
public class UnFocusableScrollView extends NestedScrollView {


    public UnFocusableScrollView(Context context) {
        super(context);
    }

    public UnFocusableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnFocusableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void requestChildFocus(View child, View focused) {
    }

    @Override
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        return true;
    }

    @Override
    public ArrayList<View> getFocusables(int direction) {
        return new ArrayList<View>();
    }

    private OnScrollListener onScrollListener;

    /**
     * 设置滚动接口
     *
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null) {
            onScrollListener.onScroll(t);
        }
    }

    /**
     * 滚动的回调接口
     *
     * @author xiaanming
     */
    public interface OnScrollListener {
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         *
         * @param scrollY
         */
        public void onScroll(int scrollY);
    }
}
