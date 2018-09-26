package com.test.yanxiu.common_base.ui.recycler_view.wrapper;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.yanxiu.common_base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.ui.recycler_view.BaseViewHolder;


/**
 * recyclerview添加头和尾的包装类
 * Created by 戴延枫 on 2018/4/19.
 */

public class HeaderAndFooterWrapper extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;
    public static final int ITEM_TYPE_EMPTY = Integer.MAX_VALUE - 1;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();

    //空界面 start
    private View mEmptyView;
    private int mEmptyLayoutId = -100;
    //空界面 end

    private BaseAdapter mInnerAdapter;

    public HeaderAndFooterWrapper(BaseAdapter adapter) {
        mInnerAdapter = adapter;
    }

    private boolean isEmpty() {
        return (mEmptyView != null || mEmptyLayoutId != -100)
                && mHeaderViews.size() == 0
                && mFootViews.size() == 0
                && mInnerAdapter.getItemCount() == 0;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (isEmpty()) {
            ViewHolder holder;
            if (mEmptyView != null) {
                holder = ViewHolder.createViewHolder(parent.getContext(), mEmptyView);
                return holder;
            } else if (mEmptyLayoutId != -100) {
                holder = ViewHolder.createViewHolder(parent.getContext(), parent, mEmptyLayoutId);
                return holder;
            }
//            else { //默认emptyview
//                holder = ViewHolder.createViewHolder(parent.getContext(), parent, R.layout.demo_header_empty_view);
//            }
//            return holder;
        }
        if (mHeaderViews.get(viewType) != null) {
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mHeaderViews.get(viewType));
            return holder;

        } else if (mFootViews.get(viewType) != null) {
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mFootViews.get(viewType));
            return holder;
        }
        return (BaseViewHolder) mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        }

        if (isFooterViewPos(position)) {
            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }

        if (isEmpty()) {
            return ITEM_TYPE_EMPTY;
        }

        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    private int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (isEmpty()) {
            return;
        }
        if (isHeaderViewPos(position)) {
            return;
        }
        if (isFooterViewPos(position)) {
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        int realItemCount = getRealItemCount();
        if (isEmpty()) {
            realItemCount = 1;
        }
        return getHeadersCount() + getFootersCount() + realItemCount;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                int viewType = getItemViewType(position);
                if (mHeaderViews.get(viewType) != null) {
                    return layoutManager.getSpanCount();
                }
                if (mFootViews.get(viewType) != null) {
                    return layoutManager.getSpanCount();
                }
//                if (isEmpty()) {
//                    return layoutManager.getSpanCount();
//                }
                if (oldLookup != null)
                    return oldLookup.getSpanSize(position);
                return 1;
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeaderViewPos(position) || isFooterViewPos(position) || isEmpty()) {
            WrapperUtils.setFullSpan(holder);
        }
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }


    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    public void addFootView(View view) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }

    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }

    public void setEmptyView(int layoutId) {
        mEmptyView = null;
        mEmptyLayoutId = layoutId;
    }

    public static class ViewHolder extends BaseViewHolder {

        public ViewHolder(Context context, View itemView) {
            super(context, itemView);
        }

        @Override
        public void setData(int position, Object data) {

        }

        /**
         * 给wrapper使用的快速创建holder的方法。
         *
         * @param itemView
         * @return
         */
        public static ViewHolder createViewHolder(Context context, View itemView) {
            ViewHolder holder = new ViewHolder(context, itemView);
            return holder;
        }

        /**
         * 给wrapper使用的快速创建holder的方法。
         *
         * @param context
         * @param parent
         * @param layoutId
         * @return
         */
        public static ViewHolder createViewHolder(Context context, ViewGroup parent, int layoutId) {
            View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            ViewHolder holder = new ViewHolder(context, itemView);
            return holder;
        }
    }

//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        this.mRecyclerView = recyclerView;
//        super.onAttachedToRecyclerView(recyclerView);
//    }

    private int mHeight;
    private RecyclerView mRecyclerView;
    public int mAllHeadersAndFootersHeight;//全部herder和footer的高度，用来显示emptyview时，有herder或者footer时，计算empty的高度

    /**
     * 设置空界面view
     */
    public void setEmptyView(View emptyView, View... allHeaderAndFooter) {
        mEmptyView = emptyView;
        if (allHeaderAndFooter != null && allHeaderAndFooter.length > 0) {
            for (final View view : allHeaderAndFooter) {
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        mAllHeadersAndFootersHeight += view.getMeasuredHeight();
                        mHeight = mRecyclerView.getHeight();
                        ViewGroup.LayoutParams lp = mEmptyView.getLayoutParams();
                        lp.height = mHeight - mAllHeadersAndFootersHeight;
                        mEmptyView.setLayoutParams(lp);
                    }
                });
            }
        }

    }
}
