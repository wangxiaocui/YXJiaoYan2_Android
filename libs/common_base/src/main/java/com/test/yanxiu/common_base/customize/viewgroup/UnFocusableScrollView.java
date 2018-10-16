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
}
