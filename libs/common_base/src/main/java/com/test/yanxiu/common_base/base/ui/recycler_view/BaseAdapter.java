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
    protected Context mContext;
    protected List<T> mDatas;
    protected OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;

    private BaseAdapter() {

    }

    public BaseAdapter(Context context) {
        this(context, null);
    }

    public BaseAdapter(Context context, List<T> datas) {
        super();
        mContext = context;
        mDatas = new ArrayList<>();
        setData(datas);
    }


    /**
     * 添加数据
     *
     * @param datas
     */
    public void setData(List<T> datas) {
        mDatas.clear();
        if (datas != null && !datas.isEmpty()) {
            mDatas.addAll(datas);
        }
    }

    /**
     * 获取数据list
     *
     * @return
     */
    public List<T> getData() {
        return mDatas;
    }

    /**
     * 获取单条数据
     *
     * @param position
     * @return
     */
    public T getData(int position) {
        return mDatas.get(position);
    }

    /**
     * 追加数据
     *
     * @param datas
     */
    public void addData(List<T> datas) {
        if (datas == null || datas.isEmpty())
            return;
        mDatas.addAll(datas);
    }

    /**
     * 追加单条数据
     *
     * @param data
     */
    public void addData(T data) {
        if (data == null)
            return;
        mDatas.add(data);
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
        return mDatas == null ? 0 : mDatas.size();
    }
}
