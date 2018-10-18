package com.test.yanxiu.common_base.customize.viewgroup;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.yanxiu.common_base.R;
import com.yanxiu.lib.yx_basic_library.util.logger.YXLogger;


/**
 * 课程详情中，自定义的tabview
 * Created by 戴延枫 on 2018/10/17.
 */

public class CustomTabView extends FrameLayout implements View.OnClickListener {
    private Context mContext;

    private int currentIndex = 1;
    private LinearLayout tab_1;//介绍
    private TextView tv_jieshao;
    private RelativeLayout line_jieshao;

    private LinearLayout tab_2;//目录
    private TextView tv_mulu;
    private RelativeLayout line_mulu;

    private LinearLayout tab_3;//评价
    private TextView tv_pingjia;
    private RelativeLayout line_pingjia;


    public CustomTabView(Context context) {
        this(context, null);
    }

    public CustomTabView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        inflate(mContext, R.layout.customize_viewgroup_custom_tabview_layout, this);
        tab_1 = findViewById(R.id.tab_1);
        tv_jieshao = findViewById(R.id.tv_jieshao);
        line_jieshao = findViewById(R.id.line_jieshao);

        tab_2 = findViewById(R.id.tab_2);
        tv_mulu = findViewById(R.id.tv_mulu);
        line_mulu = findViewById(R.id.line_mulu);

        tab_3 = findViewById(R.id.tab_3);
        tv_pingjia = findViewById(R.id.tv_pingjia);
        line_pingjia = findViewById(R.id.line_pingjia);

        tab_1.setOnClickListener(this);
        tab_2.setOnClickListener(this);
        tab_3.setOnClickListener(this);

        tab_1.setSelected(true);
    }

    /**
     * 设置选中tab
     *
     * @param position
     */
    public void selectTab(int position) {
        currentIndex = position;
        switch (position) {
            case 1:
                tab_1.setSelected(true);
                tab_2.setSelected(false);
                tab_3.setSelected(false);
                break;
            case 2:
                tab_1.setSelected(false);
                tab_2.setSelected(true);
                tab_3.setSelected(false);
                break;
            case 3:
                tab_1.setSelected(false);
                tab_2.setSelected(false);
                tab_3.setSelected(true);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        int position = 1;
        if (view.equals(tab_1)) {
            position = 1;
        } else if (view.equals(tab_2)) {
            position = 2;
        } else if (view.equals(tab_3)) {
            position = 3;
        }
        selectTab(position);
        if (mListener != null) {
            mListener.tabClick(position);
        }
    }

    public void setOnTabClickListener(@Nullable OnTabClickListener l) {
        mListener = l;
    }

    private OnTabClickListener mListener;

    public interface OnTabClickListener {

        void tabClick(int position);

    }
}
