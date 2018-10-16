package com.yanxiu.gphone.jiaoyan.business.course_detail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseViewHolder;
import com.test.yanxiu.common_base.customize.view.StarProgressBar;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course_detail.bean.DirectioryBean;
import com.yanxiu.gphone.jiaoyan.business.course_detail.bean.EvalutionBean;
import com.yanxiu.lib.yx_basic_library.customize.view.RoundCornerImageView;


/**
 * EvaluationFragment里的adapter
 * Created by 戴延枫 on 2018/10/12.
 */


public class EvaluationAdapter extends BaseAdapter<EvalutionBean> {

    private Context mContext;

    public EvaluationAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        BaseViewHolder viewHolder = null;
        View view = inflater.inflate(R.layout.course_detail_item_evaluation, parent, false);
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

        private RoundCornerImageView iv;
        private TextView tv_name;
        private TextView tv_comment_status;
        private TextView tv_comment_date;
        private TextView tv_comment_content;
        private StarProgressBar star_progress;
        private TextView tv_reply_name;
        private TextView tv_reply_date;
        private TextView tv_reply_content;

        public ViewHolder(Context context, View itemView) {
            super(context, itemView);
            iv = itemView.findViewById(R.id.iv);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_comment_status = itemView.findViewById(R.id.tv_comment_status);
            tv_comment_date = itemView.findViewById(R.id.tv_comment_date);
            tv_comment_content = itemView.findViewById(R.id.tv_comment_content);
            tv_reply_name = itemView.findViewById(R.id.tv_reply_name);
            tv_reply_date = itemView.findViewById(R.id.tv_reply_date);
            tv_reply_content = itemView.findViewById(R.id.tv_reply_content);
            star_progress = itemView.findViewById(R.id.star_progress);
        }

        @Override
        public void setData(final int position, final EvalutionBean data) {
//            Glide.with(itemView.getContext()).load(data.getHead()).into(iv_head);
            iv.setImageResource(R.drawable.bg_login);
            tv_name.setText("苏有朋");
            tv_comment_status.setText("哈哈哈哈哈");
            tv_comment_date.setText("苏有朋");
            tv_comment_content.setText(content);
            tv_reply_name.setText("苏有朋");
            tv_reply_date.setText("苏有朋");
            tv_reply_content.setText(reply);
            star_progress.setProgressIntValue(60);
            if (mOnRecyclerViewItemClickListener != null)
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnRecyclerViewItemClickListener.onItemClick(v, data, position);
                    }
                });
        }
    }

    String content = "党的十八大以来，习近平总书记站在全面建成小康社会、实现中华民族伟大复兴中国梦的战略高度，" +
            "把脱贫攻坚摆到治国理政突出位置，提出一系列新思想新观点，作出一系列新决策新部署，推动中国减贫事业取得巨大成就，" +
            "对世界减贫进程作出了重大贡献。为帮助国内外读者";
    String reply = "党的十八大以来，习近平总书记站在全面建成小康社会、实现中华民族伟大复兴中国梦的战略高度，" +
            "把脱贫攻坚摆到治国理政突出位置";
}
