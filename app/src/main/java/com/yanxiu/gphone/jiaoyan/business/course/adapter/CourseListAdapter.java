package com.yanxiu.gphone.jiaoyan.business.course.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseViewHolder;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course.bean.ClassStudyScoreRankingBean;

import java.util.List;



public class CourseListAdapter extends BaseAdapter<ClassStudyScoreRankingBean> {

    public CourseListAdapter(Context context) {
        super(context);
    }

    @Override
    public void setData(List<ClassStudyScoreRankingBean> datas) {
        mDatas = datas;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_flow_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(mContext, view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setData(position, mDatas.get(position));
    }

    class ViewHolder extends BaseViewHolder<ClassStudyScoreRankingBean> {
        TextView tv_text;

        public ViewHolder(Context context, View itemView) {
            super(context, itemView);
            tv_text = (TextView) itemView;
        }

        @Override
        public void setData(final int position, final ClassStudyScoreRankingBean data) {
            tv_text.setText(data.getUserName());
        }
    }


}
