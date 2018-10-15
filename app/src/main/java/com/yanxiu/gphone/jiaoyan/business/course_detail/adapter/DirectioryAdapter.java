package com.yanxiu.gphone.jiaoyan.business.course_detail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseViewHolder;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course_detail.bean.DirectioryBean;
import com.yanxiu.lib.yx_basic_library.customize.view.RoundCornerImageView;


/**
 * DirectoryFragment里的adapter
 * Created by 戴延枫 on 2018/10/11.
 */


public class DirectioryAdapter extends BaseAdapter<DirectioryBean> {

    private Context mContext;

    public DirectioryAdapter(Context context) {
        super(context);
        mContext = context;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        BaseViewHolder viewHolder = null;
        View view = inflater.inflate(R.layout.course_detail_item_directory, parent, false);
        viewHolder = new ViewHolder(parent.getContext(), view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        DirectioryBean data = mDatas.get(position);
        ViewHolder demoViewHolder = (ViewHolder) holder;
        demoViewHolder.setData(position, data);
    }

    public class ViewHolder extends BaseViewHolder<DirectioryBean> {

        private RoundCornerImageView iv;
        private TextView tv_class_num;
        private TextView tv_class_name;

        public ViewHolder(Context context, View itemView) {
            super(context, itemView);
            iv = itemView.findViewById(R.id.iv);
            tv_class_num = itemView.findViewById(R.id.tv_class_num);
            tv_class_name = itemView.findViewById(R.id.tv_class_name);
        }

        @Override
        public void setData(final int position, final DirectioryBean data) {
//            Glide.with(itemView.getContext()).load(data.getHead()).into(iv_head);
            iv.setImageResource(R.drawable.bg_login);
            tv_class_num.setText("第" + (position + 1) + "节");
            tv_class_name.setText("知名专家");
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
