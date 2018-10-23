package com.yanxiu.gphone.jiaoyan.business.my_wealth.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.test.yanxiu.common_base.base.ui.JYBaseFragment;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.base.recycler_base.JYBaseRecyclerFragment_2;
import com.yanxiu.gphone.jiaoyan.business.course.adapter.CourseAdapter;
import com.yanxiu.gphone.jiaoyan.business.my_wealth.presenter.MockDataFetcher;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

/**
 * Created By cailei on 2018/10/23
 */
public class MyWealthFragment extends JYBaseRecyclerFragment_2 {
    @Override
    public void initData(@NonNull Bundle bundle) {
        mFetcher = new MockDataFetcher();
        ((MockDataFetcher) mFetcher).callback = this;

        mAdapter = new CourseAdapter();
    }
}
