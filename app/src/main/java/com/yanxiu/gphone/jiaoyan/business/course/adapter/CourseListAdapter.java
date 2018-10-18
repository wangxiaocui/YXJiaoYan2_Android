package com.yanxiu.gphone.jiaoyan.business.course.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseViewHolder;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course.bean.CourseBean;


public class CourseListAdapter extends BaseAdapter<CourseBean> {

    public static final int COURSE_SINGLE = 0;
    public static final int COURSE_MULTIPLE = 1;

    public CourseListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 2) {
            return COURSE_MULTIPLE;
        } else if (position == 5 || position == 8) {
            return COURSE_MULTIPLE;
        } else {
            return COURSE_SINGLE;
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == COURSE_SINGLE) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.course_item_single, parent, false);
            return new SingleViewHolder(mContext, view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.course_item_multiple, parent, false);
            return new MultipleViewHolder(mContext, view);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        holder.setData(position, mDatas.get(position));
        if (getItemViewType(position) == COURSE_SINGLE) {
            if (position + 1 < getItemCount()
                    && getItemViewType(position + 1) == COURSE_MULTIPLE) {
                ((SingleViewHolder) holder).setDividerVisibility(View.INVISIBLE);
            } else {
                ((SingleViewHolder) holder).setDividerVisibility(View.VISIBLE);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRecyclerViewItemClickListener != null) {
                    mOnRecyclerViewItemClickListener.onItemClick(v, mDatas.get(position), position);
                }
            }
        });
    }


    public static class CourseViewHolder extends BaseViewHolder<CourseBean> {
        public ImageView iv_course_cover;
        public TextView tv_title;
        public TextView tv_level;
        public TextView tv_subject;
        public TextView tv_speaker;
        public TextView tv_looker_num;

        public CourseViewHolder(Context context, View itemView) {
            super(context, itemView);
            iv_course_cover = itemView.findViewById(R.id.iv_course_cover);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_level = itemView.findViewById(R.id.tv_level);
            tv_subject = itemView.findViewById(R.id.tv_subject);
            tv_speaker = itemView.findViewById(R.id.tv_speaker);
            tv_looker_num = itemView.findViewById(R.id.tv_looker_num);
        }

        @Override
        public void setData(final int position, final CourseBean data) {
            iv_course_cover.setImageResource(R.drawable.bg_login);
            tv_title.setText("如何把握英语课程中各种任务群的关系如何把握英语课程中的各种任务群的关系如何把握");
            tv_level.setText("初中");
            tv_subject.setText("英语与语法");
            tv_looker_num.setText("1234人观看");
        }
    }

    public static class SingleViewHolder extends CourseViewHolder {
        public ImageView iv_course_play;
        public View view_divider;

        public SingleViewHolder(Context context, View itemView) {
            super(context, itemView);
            iv_course_play = itemView.findViewById(R.id.iv_course_play);
            view_divider = itemView.findViewById(R.id.view_divider);
        }

        @Override
        public void setData(final int position, final CourseBean data) {
            super.setData(position, data);
            tv_speaker.setText("主讲：嘿嘿嘿\u3000·\u3000120分钟");
            iv_course_play.setImageResource(R.drawable.homepage_notice);
        }

        public void setDividerVisibility(int visibility) {
            view_divider.setVisibility(visibility);
        }
    }

    public static class MultipleViewHolder extends CourseViewHolder {
        private TextView tv_duration;

        public MultipleViewHolder(Context context, View itemView) {
            super(context, itemView);
            tv_duration = itemView.findViewById(R.id.tv_duration);
        }

        @Override
        public void setData(final int position, final CourseBean data) {
            super.setData(position, data);
            tv_speaker.setText("主讲：哈哈哈");
            tv_duration.setText("9课时 (360分钟)");
        }
    }


}
