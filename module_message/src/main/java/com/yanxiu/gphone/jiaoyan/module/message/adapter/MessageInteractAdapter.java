package com.yanxiu.gphone.jiaoyan.module.message.adapter;

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
import com.yanxiu.gphone.jiaoyan.module.message.R;
import com.yanxiu.gphone.jiaoyan.module.message.bean.MessageBean;


/**
 * 互动消息页adapter
 * Created by Hu Chao on 18/10/18.
 */
public class MessageInteractAdapter extends BaseAdapter<MessageBean> {


    public MessageInteractAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public InteractViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.message_item_interact, viewGroup, false);
        return new InteractViewHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
        baseViewHolder.setData(position, mDatas.get(position));
    }

    static class InteractViewHolder extends BaseViewHolder<MessageBean> {

        private ImageView iv_avatar;
        private TextView tv_name;
        private TextView tv_time;
        private TextView tv_message_content;
        private TextView tv_reply_content;
        private View v_reply_divider;
        private TextView tv_course_title;

        public InteractViewHolder(Context context, View itemView) {
            super(context, itemView);
            iv_avatar = itemView.findViewById(R.id.iv_avatar);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_message_content = itemView.findViewById(R.id.tv_message_content);
            tv_reply_content = itemView.findViewById(R.id.tv_reply_content);
            v_reply_divider = itemView.findViewById(R.id.v_reply_divider);
            tv_course_title = itemView.findViewById(R.id.tv_course_title);
        }

        @Override
        public void setData(final int position, final MessageBean data) {
            if (position % 3 == 0) {
                tv_reply_content.setVisibility(View.VISIBLE);
                v_reply_divider.setVisibility(View.VISIBLE);
                tv_reply_content.setText("党的十八大以来，习近平总书记站在全面建成小康社会、实现中华民族伟大复兴中国梦的战略高度《习近平扶贫论述摘编》。");
            } else {
                tv_reply_content.setVisibility(View.GONE);
                v_reply_divider.setVisibility(View.GONE);
            }
            iv_avatar.setImageResource(R.color.color_007aff);
            tv_name.setText("张三丰");
            tv_time.setText("2018.10.18\u300010:18");
            tv_message_content.setText("党的十八大以来，习近平总书记站在全面建成小康社会、实现中华民族伟大复兴中国梦的战略高度《习近平扶贫论述摘编》。");
            tv_course_title.setText("《习近平扶贫论述摘编》");
        }
    }

}
