package com.yanxiu.gphone.jiaoyan.business.mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.yanxiu.common_base.base.ui.recycler_view.BaseViewHolder;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.mine.mock.MineGradientProgressView;

import java.util.Random;

/**
 * Created By cailei on 2018/10/19
 */
public class MyCertUnFinishAdapter extends MyCertAdapter {
    public MyCertUnFinishAdapter(Context context) {
        super(context);
    }
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mine_my_cert_item_un_finish, viewGroup, false);
        return new FinishViewHolder(mContext, view);
    }

    public class FinishViewHolder extends CertViewHolder {
        public TextView tv_cert_progress;
        public MineGradientProgressView custom_progress_view;

        public FinishViewHolder(Context context, View itemView) {
            super(context, itemView);
            tv_cert_progress = itemView.findViewById(R.id.tv_cert_progress);
            custom_progress_view = itemView.findViewById(R.id.custom_progress_view);
            custom_progress_view.setProgress(new Random().nextFloat());
        }

        @Override
        public void setData(int position, Object data) {
            super.setData(position, data);
            this.itemView.setOnClickListener(onClickListener);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RouteUtils.startActivity(RoutePathConfig.Mine_My_Cert_Detail_Activity);
            }
        };
    }
}
