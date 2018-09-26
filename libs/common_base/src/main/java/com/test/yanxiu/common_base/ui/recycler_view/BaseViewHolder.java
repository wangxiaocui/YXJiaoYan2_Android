package com.test.yanxiu.common_base.ui.recycler_view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * RecyclerView的基类ViewHolder
 * Created by 戴延枫 on 2018/4/16.
 */

public abstract class BaseViewHolder<D> extends RecyclerView.ViewHolder {
    public Context mContext;

    /*
     * @param context  预留，以备后续扩展会使用到
     * @param itemView
     */
    public BaseViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
    }

    /*
     * 设置数据
     */
    public abstract void setData(int position, D data);

}