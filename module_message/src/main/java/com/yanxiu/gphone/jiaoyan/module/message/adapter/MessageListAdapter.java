package com.yanxiu.gphone.jiaoyan.module.message.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseViewHolder;
import com.yanxiu.gphone.jiaoyan.module.message.R;


/**
 * Created by Hu Chao on 18/10/18.
 */
public class MessageListAdapter extends BaseAdapter {


    public MessageListAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.message_item_interaction, viewGroup, false);
        return new ViewHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    static class ViewHolder extends BaseViewHolder {

        public ViewHolder(Context context, View itemView) {
            super(context, itemView);
        }

        @Override
        public void setData(final int position, final Object data) {
        }
    }

}
