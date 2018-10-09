package com.test.yanxiu.common_base.base.ui.recycler_view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * recycler基类adapter
 * Created by 戴延枫 on 2018/4/16.
 */


public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    public Context mContext;
    public List<T> mDatas;
    public OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;
    public View mEmptyView;

    private BaseAdapter() {

    }

    public BaseAdapter(Context context) {
        super();
        mContext = context;
    }

    public BaseAdapter(Context context, List<T> datas) {
        super();
        mContext = context;
        mDatas = datas;
    }


    /**
     * 添加数据
     *
     * @param datas
     */
    public void setData(List<T> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    /**
     * 追加数据
     *
     * @param datas
     */
    public void addData(List<T> datas) {
        if (datas == null || datas.isEmpty())
            return;
        int poisition = getItemCount();
        if (mDatas != null) {
            mDatas.addAll(datas);
        } else {
            mDatas = datas;
        }
        notifyItemRangeInserted(poisition, datas.size());
    }

    /**
     * 追加单条数据
     *
     * @param data
     */
    public void addData(T data) {
        if (data == null)
            return;
        int poisition = getItemCount();
        if (mDatas != null) {
            mDatas.add(data);
        } else {
            mDatas = new ArrayList<>();
            mDatas.add(data);
        }
        notifyItemInserted(poisition);
    }

    /**
     * 设置item点击事件
     *
     * @param onRecyclerViewItemClickListener
     */
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public int getItemCount() {
        if (mDatas == null)
            return 0;
        return mDatas.size();
    }
}
