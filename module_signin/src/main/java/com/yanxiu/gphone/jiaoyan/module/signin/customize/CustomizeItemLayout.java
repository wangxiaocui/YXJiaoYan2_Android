package com.yanxiu.gphone.jiaoyan.module.signin.customize;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yanxiu.gphone.jiaoyan.module.signin.R;

/**
 * Created by Cai Lei on 2018/10/9.
 */

// "我的"入口页面中item的样式，个人资料，资质认证，财富值，我的课程，我的证书，设置
// 包含了白色的MajorTitle 和 蓝色的MinorTitle (用于财富值)
public class CustomizeItemLayout extends LinearLayout {
    private Context mContext;
    private TextView tv_major_title;
    private TextView tv_minor_title;
    private ImageView iv_into_icon;

    public CustomizeItemLayout(Context context) {
        super(context);
        mContext = context;
        setupUI();
    }

    public CustomizeItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setupUI();
    }

    public CustomizeItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setupUI();
    }

    private void setupUI() {
        setOrientation(VERTICAL);
        LayoutInflater.from(mContext).inflate(R.layout.customize_item_layout, this, true);
        tv_major_title = findViewById(R.id.tv_major_title);
        tv_minor_title = findViewById(R.id.tv_minor_title);
        iv_into_icon = findViewById(R.id.iv_into_icon);
    }

    public void setTitle(String title) {
        setTitle(title, null);
    }

    public void setTitle(String majorTitle, String minorTitle) {
        tv_major_title.setText(majorTitle);
        tv_minor_title.setText(minorTitle);
    }

    public void setMinorTitle(String minorTitle) {
        tv_minor_title.setText(minorTitle);
    }

    public void setMinorHint(String hintText) {
        tv_minor_title.setHint(hintText);
    }

    public void setRightIcon(@DrawableRes int resId) {
        iv_into_icon.setImageResource(resId);
    }

    public void setRightIconVisibility(int visibility) {
        iv_into_icon.setVisibility(visibility);
    }

    public String getMinorTitle() {
        return tv_minor_title.getText().toString();
    }
}
