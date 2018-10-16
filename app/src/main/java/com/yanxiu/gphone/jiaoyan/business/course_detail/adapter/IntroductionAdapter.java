package com.yanxiu.gphone.jiaoyan.business.course_detail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseViewHolder;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course_detail.bean.IntroductionBean;
import com.yanxiu.lib.yx_basic_library.customize.view.RoundCornerImageView;


/**
 * IntroductionFragment里的专家adapter
 * Created by 戴延枫 on 2018/10/11.
 */


public class IntroductionAdapter extends BaseAdapter<IntroductionBean> {

    private Context mContext;

    public IntroductionAdapter(Context context) {
        super(context);
        mContext = context;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        BaseViewHolder viewHolder = null;
        View view = inflater.inflate(R.layout.course_detail_item_introduction, parent, false);
        viewHolder = new ViewHolder(parent.getContext(), view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        IntroductionBean data = mDatas.get(position);
        ViewHolder demoViewHolder = (ViewHolder) holder;
        demoViewHolder.setData(position, data);
    }

    public class ViewHolder extends BaseViewHolder<IntroductionBean> {

        private RoundCornerImageView iv_head;
        private TextView tv_name;
        private TextView tv_honor_name;

        public ViewHolder(Context context, View itemView) {
            super(context, itemView);
            iv_head = itemView.findViewById(R.id.iv_head);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_honor_name = itemView.findViewById(R.id.tv_honor_name);
        }

        @Override
        public void setData(final int position, final IntroductionBean data) {
//            Glide.with(itemView.getContext()).load(data.getHead()).into(iv_head);
            iv_head.setImageResource(R.drawable.bg_login);
            tv_name.setText("转学有");
            tv_honor_name.setText("知名专家");
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
