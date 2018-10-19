package com.yanxiu.gphone.jiaoyan.module.message.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseViewHolder;
import com.yanxiu.gphone.jiaoyan.module.message.R;
import com.yanxiu.gphone.jiaoyan.module.message.bean.MessageBean;


/**
 * 系统消息页adapter
 * Created by Hu Chao on 18/10/18.
 */
public class MessageSystemAdapter extends BaseAdapter<MessageBean> {

    //预约
    public static final int TYPE_RESERVE = 0;
    //认证
    public static final int TYPE_CERTIFICATION = 1;

    public MessageSystemAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3 != 0 ? TYPE_RESERVE : TYPE_CERTIFICATION;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == TYPE_CERTIFICATION) {
            view = LayoutInflater.from(mContext).inflate(R.layout.message_item_system_certification, viewGroup, false);
            return new CertificationViewHolder(mContext, view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.message_item_system_reserve, viewGroup, false);
            return new ReserveViewHolder(mContext, view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
        baseViewHolder.setData(position, mDatas.get(position));
    }

    static class ReserveViewHolder extends BaseViewHolder {

        private ImageView iv_avatar;
        private TextView tv_name;
        private TextView tv_time;
        private TextView tv_message_content;
        private TextView tv_course_title;
        private TextView tv_enter_btn;

        public ReserveViewHolder(Context context, View itemView) {
            super(context, itemView);
            iv_avatar = itemView.findViewById(R.id.iv_avatar);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_message_content = itemView.findViewById(R.id.tv_message_content);
            tv_course_title = itemView.findViewById(R.id.tv_course_title);
            tv_enter_btn = itemView.findViewById(R.id.tv_enter_btn);
        }

        @Override
        public void setData(final int position, final Object data) {
            iv_avatar.setImageResource(R.color.color_007aff);
            tv_name.setText("教研网");
            tv_time.setText("2018.10.18\u300010:18");
            tv_message_content.setText("预约的课程已上线");
            tv_course_title.setText("推动全面建成小康社会");
        }
    }

    static class CertificationViewHolder extends BaseViewHolder {

        private ImageView iv_avatar;
        private TextView tv_name;
        private TextView tv_time;
        private TextView tv_message_content;
        private TextView tv_message_extra;


        public CertificationViewHolder(Context context, View itemView) {
            super(context, itemView);
            iv_avatar = itemView.findViewById(R.id.iv_avatar);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_message_content = itemView.findViewById(R.id.tv_message_content);
            tv_message_extra = itemView.findViewById(R.id.tv_message_extra);
        }

        @Override
        public void setData(final int position, final Object data) {
            iv_avatar.setImageResource(R.color.color_007aff);
            tv_name.setText("教研网");
            tv_time.setText("2018.10.18\u300010:18");
            tv_message_content.setText("身份证审核已通过");
            tv_message_extra.setText("荣誉值+10、财富值+5");
        }
    }

}
