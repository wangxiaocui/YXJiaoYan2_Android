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
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.lib.yx_basic_library.base.bean.YXBaseBean;
import com.yanxiu.lib.yx_basic_library.customize.view.RoundCornerImageView;


/**
 * VideoInfoFragment里的视频列表adapter
 * Created by 戴延枫 on 2018/10/12.
 */


public class VideoListAdapter extends BaseAdapter<YXBaseBean> {

    private Context mContext;

    public VideoListAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        BaseViewHolder viewHolder = null;
        View view = inflater.inflate(R.layout.video_list_item, parent, false);
        viewHolder = new ViewHolder(parent.getContext(), view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        YXBaseBean data = mDatas.get(position);
        ViewHolder demoViewHolder = (ViewHolder) holder;
        demoViewHolder.setData(position, data);
    }

    public class ViewHolder extends BaseViewHolder<YXBaseBean> {

        private RoundCornerImageView iv_img;
        private ImageView iv_mask;
        private TextView tv_video_name;
        private TextView tv_video_status;

        public ViewHolder(Context context, View itemView) {
            super(context, itemView);
            iv_img = itemView.findViewById(R.id.iv_img);
            iv_mask = itemView.findViewById(R.id.iv_mask);
            tv_video_name = itemView.findViewById(R.id.tv_video_name);
            tv_video_status = itemView.findViewById(R.id.tv_video_status);
        }

        @Override
        public void setData(final int position, final YXBaseBean data) {
            Glide.with(itemView.getContext()).load("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg").into(iv_img);
//            iv_img.setImageResource(R.drawable.bg_login);
            tv_video_name.setText("哈哈哈哈哈");
            switch (position) {
                case 0:
                    iv_mask.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.video_list_item_mask_learning_shape));
                    tv_video_status.setText("学习中");
                    tv_video_status.setTextColor(mContext.getResources().getColor(R.color.color_007aff));
                    break;
                case 1:
                    iv_mask.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.video_list_item_mask_not_learning_shape));
                    tv_video_status.setText("未学习");
                    tv_video_status.setTextColor(mContext.getResources().getColor(R.color.color_fca58d));
                    break;
                case 2:
                    iv_mask.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.video_list_item_mask_learning_finish_shape));
                    tv_video_status.setText("已学习");
                    tv_video_status.setTextColor(mContext.getResources().getColor(R.color.color_000000));
                    break;
            }
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
