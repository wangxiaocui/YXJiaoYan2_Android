package com.yanxiu.gphone.jiaoyan.business.mine.adapter;

import android.content.Context;

import com.test.yanxiu.common_base.base.ui.recycler_view.BaseViewHolder;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course.adapter.CourseListAdapter;

/**
 * Created By cailei on 2018/10/18
 */
public class MineMyCourseReserveAdapter extends CourseListAdapter {
    public MineMyCourseReserveAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        // todo : cailei 通过数据不同设置不同颜色
        ((CourseViewHolder)holder).tv_looker_num.setTextColor(mContext.getResources().getColor(R.color.color_green));
    }
}
