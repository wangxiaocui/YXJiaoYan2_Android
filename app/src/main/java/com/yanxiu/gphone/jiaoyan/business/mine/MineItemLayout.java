package com.yanxiu.gphone.jiaoyan.business.mine;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yanxiu.gphone.jiaoyan.R;

/**
 * Created by Cai Lei on 2018/10/9.
 */

// "我的"入口页面中item的样式，个人资料，资质认证，财富值，我的课程，我的证书，设置
// 包含了白色的MajorTitle 和 蓝色的MinorTitle (用于财富值)
public class MineItemLayout extends LinearLayout {
    private Context mContext;
    private TextView tv_major_title;
    private TextView tv_minor_title;

    public MineItemLayout(Context context) {
        super(context);
        mContext = context;
        setupUI();
    }

    public MineItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setupUI();
    }

    public MineItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setupUI();
    }

    private void setupUI() {
        LayoutInflater.from(mContext).inflate(R.layout.mine_item_layout, this);
        tv_major_title = findViewById(R.id.tv_major_title);
        tv_minor_title = findViewById(R.id.tv_minor_title);
    }

    public void setTitle(String title) {
        setTitle(title, null);
    }

    public void setTitle(String majorTitle, String minorTitle) {
        if (minorTitle == null) {
            tv_minor_title.setVisibility(GONE);
        } else {
            tv_minor_title.setVisibility(VISIBLE);
        }

        tv_major_title.setText(majorTitle);
        tv_minor_title.setText(minorTitle);
    }
}
