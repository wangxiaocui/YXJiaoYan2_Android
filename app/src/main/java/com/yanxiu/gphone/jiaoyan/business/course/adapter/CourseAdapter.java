package com.yanxiu.gphone.jiaoyan.business.course.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course.bean.CourseBean;

import java.util.ArrayList;


public class CourseAdapter extends BaseQuickAdapter<CourseBean, BaseViewHolder> {

    public CourseAdapter() {
        super(new ArrayList<CourseBean>());
        setMultiTypeDelegate(new MultiTypeDelegate<CourseBean>() {
            @Override
            protected int getItemType(CourseBean courseBean) {
                return courseBean.getRecommend();
            }
        });
        //Step.2
        getMultiTypeDelegate()
                .registerItemType(0, R.layout.course_item_single)
                .registerItemType(1, R.layout.course_item_multiple);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseBean entity) {
        helper.setImageResource(R.id.iv_course_cover, R.drawable.bg_login);
        helper.setText(R.id.tv_title, "如何把握英语课程中各种任务群的关系如何把握英语课程中的各种任务群的关系如何把握");
        helper.setText(R.id.tv_level, "初中");
        helper.setText(R.id.tv_subject, "英语与语法");
        helper.setText(R.id.tv_looker_num, "1234人观看");
        switch (helper.getItemViewType()) {
            case 0:
                helper.setText(R.id.tv_speaker, "主讲：嘿嘿嘿\u3000·\u3000120分钟");
                helper.setImageResource(R.id.iv_course_play, R.drawable.homepage_notice);
                // do something
                break;
            case 1:
                helper.setText(R.id.tv_speaker, "主讲：哈哈哈");
                helper.setText(R.id.tv_duration, "9课时 (360分钟)");
                // do something
                break;
        }
    }

}
