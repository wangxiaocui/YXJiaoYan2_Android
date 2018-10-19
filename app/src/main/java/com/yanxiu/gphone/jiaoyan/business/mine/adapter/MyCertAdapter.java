package com.yanxiu.gphone.jiaoyan.business.mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseViewHolder;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course.adapter.CourseListAdapter;

/**
 * Created By cailei on 2018/10/19
 */
public class MyCertAdapter extends BaseAdapter<Object> {
    public MyCertAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null; // 两个子类分别实现
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int i) {
        viewHolder.setData(i, null);
    }

    public static class CertViewHolder extends BaseViewHolder {
        public ImageView iv_course_cover;   // 课程图
        public TextView tv_speaker;         // 主讲人
        public TextView tv_duration;        // 课时（时长）
        public TextView tv_title;           // 主标题

        public ImageView iv_icon_cert;      // 证书icon
        public TextView tv_cert;           // "证书"这两个字

        public CertViewHolder(Context context, View itemView) {
            super(context, itemView);
            iv_course_cover = itemView.findViewById(R.id.iv_course_cover);
            tv_speaker = itemView.findViewById(R.id.tv_speaker);
            tv_duration = itemView.findViewById(R.id.tv_duration);
            tv_title = itemView.findViewById(R.id.tv_title);
            iv_icon_cert = itemView.findViewById(R.id.iv_icon_cert);
            tv_cert = itemView.findViewById(R.id.tv_cert);
        }

        @Override
        public void setData(int position, Object data) {

        }
    }
}
