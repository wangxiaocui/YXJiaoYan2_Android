package com.yanxiu.gphone.jiaoyan.business.mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.yanxiu.common_base.base.ui.recycler_view.BaseViewHolder;
import com.yanxiu.gphone.jiaoyan.R;

/**
 * Created By cailei on 2018/10/19
 */
public class MyCertFinishAdapter extends MyCertAdapter {
    public MyCertFinishAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mine_my_cert_item_finish, viewGroup, false);
        return new FinishViewHolder(mContext, view);
    }

    public class FinishViewHolder extends CertViewHolder {
        public TextView tv_cert_check;

        public FinishViewHolder(Context context, View itemView) {
            super(context, itemView);
            tv_cert_check = itemView.findViewById(R.id.tv_cert_check);
        }
    }
}
