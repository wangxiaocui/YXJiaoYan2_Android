package com.yanxiu.gphone.jiaoyan.business.video.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseViewHolder;
import com.test.yanxiu.common_base.customize.view.StarProgressBar;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course_detail.bean.EvalutionBean;
import com.yanxiu.lib.yx_basic_library.customize.view.RoundCornerImageView;


/**
 * VideoInfoFragment里的提问与回复adapter
 * Created by 戴延枫 on 2018/10/19.
 */


public class VideoQuestionAdapter extends BaseAdapter<EvalutionBean> {

    private Context mContext;

    public VideoQuestionAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        BaseViewHolder viewHolder = null;
        View view = inflater.inflate(R.layout.video_question_item, parent, false);
        viewHolder = new ViewHolder(parent.getContext(), view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        EvalutionBean data = mDatas.get(position);
        ViewHolder demoViewHolder = (ViewHolder) holder;
        demoViewHolder.setData(position, data);
    }

    public class ViewHolder extends BaseViewHolder<EvalutionBean> {

        private RoundCornerImageView iv_avater;
        private TextView tv_name;
        private ImageView iv_icon;
        private TextView tv_question_date;
        private TextView tv_question_content;
        private TextView tv_question_reply_content;


        public ViewHolder(Context context, View itemView) {
            super(context, itemView);
            iv_avater = itemView.findViewById(R.id.iv_avater);
            tv_name = itemView.findViewById(R.id.tv_name);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_question_date = itemView.findViewById(R.id.tv_question_date);
            tv_question_content = itemView.findViewById(R.id.tv_question_content);
            tv_question_reply_content = itemView.findViewById(R.id.tv_question_reply_content);

        }

        @Override
        public void setData(final int position, final EvalutionBean data) {
            Glide.with(itemView.getContext()).load(R.drawable.bg_login).into(iv_avater);
            tv_name.setText("苏有朋");
            tv_question_date.setText("2018-10-19 15:46:11");
            tv_question_content.setText("党的十八大以来，习近平总书记站在全面建成小康社会、实现中华民族伟大复兴中国梦的战略高度");
            tv_question_reply_content.setText("对世界减贫进程作出了重大贡献。为帮助国内外读者");

            if (mOnRecyclerViewItemClickListener != null)
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnRecyclerViewItemClickListener.onItemClick(v, data, position);
                    }
                });
        }
    }
}
