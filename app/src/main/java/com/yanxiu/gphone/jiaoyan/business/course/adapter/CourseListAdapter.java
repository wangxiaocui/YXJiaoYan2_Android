package com.yanxiu.gphone.jiaoyan.business.course.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseViewHolder;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course.bean.ClassStudyScoreRankingBean;

import java.util.List;
import java.util.Random;


public class CourseListAdapter extends BaseAdapter<ClassStudyScoreRankingBean> {

    public static final int COURSE_SINGLE = 0;
    public static final int COURSE_MULTIPLE = 1;

    public CourseListAdapter(Context context) {
        super(context);
    }

    @Override
    public void setData(List<ClassStudyScoreRankingBean> datas) {
        mDatas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        int random = new Random().nextInt();
        if (position < 2) {
            return COURSE_MULTIPLE;
        } else if (position < 5) {
            return COURSE_SINGLE;
        } else if (position == 5) {
            return COURSE_MULTIPLE;
        } else if (random % 4 == 0) {
            return COURSE_MULTIPLE;
        } else {
            return COURSE_SINGLE;
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == COURSE_SINGLE) {
            view = LayoutInflater.from(mContext).inflate(R.layout.course_item_single, parent, false);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.course_item_multiple, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder(mContext, view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setData(position, mDatas.get(position));
    }

    class ViewHolder extends BaseViewHolder<ClassStudyScoreRankingBean> {

        public ViewHolder(Context context, View itemView) {
            super(context, itemView);
        }

        @Override
        public void setData(final int position, final ClassStudyScoreRankingBean data) {
        }
    }


}
