package com.yanxiu.gphone.jiaoyan.business.mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.test.yanxiu.common_base.base.ui.recycler_view.BaseAdapter;
import com.test.yanxiu.common_base.base.ui.recycler_view.BaseViewHolder;

import java.util.List;

/**
 * Created By cailei on 2018/10/18
 */
public class MineMyCourseAdapter extends BaseAdapter<String> {
    public MineMyCourseAdapter(Context context) {
        super(context);
    }

    public MineMyCourseAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {

    }
}
