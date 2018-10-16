package com.yanxiu.gphone.jiaoyan.customize.viewgroup;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.yanxiu.gphone.jiaoyan.R;

/**
 * 课程详情中，学习状态view
 * Created by 戴延枫 on 2018/10/15.
 */

public class CourseDetailLearningStatusView extends FrameLayout {
    private Context mContext;
    private View layout_learning_wave;//wave
    private LinearLayout layout_join_learning;//加入学习
    private LinearLayout layout_learning_other;//其他情况
    private LinearLayout layout_learning_finish;//学习完成

    public CourseDetailLearningStatusView(Context context) {
        this(context, null);
    }

    public CourseDetailLearningStatusView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CourseDetailLearningStatusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        inflate(mContext, R.layout.course_detail_join_learning_layout, this);
        layout_learning_wave = findViewById(R.id.learning_wave);
        layout_join_learning = findViewById(R.id.layout_join_learning);
        layout_learning_other = findViewById(R.id.layout_learning_other);
        layout_learning_finish = findViewById(R.id.layout_learning_finish);
    }

    public void setData() {

    }
}
