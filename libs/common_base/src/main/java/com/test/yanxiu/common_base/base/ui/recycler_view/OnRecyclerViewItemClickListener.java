package com.test.yanxiu.common_base.base.ui.recycler_view;

import android.view.View;

/**
 * recyclerView的item点击回调
 * Created by 戴延枫 on 2018/4/19.
 */

public interface OnRecyclerViewItemClickListener<D> {

    void onItemClick(View itemView, D data, int position);
}
