package com.yanxiu.gphone.jiaoyan.module.message.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseFragment;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.utils.TabLayoutUtil;
import com.yanxiu.gphone.jiaoyan.module.message.R;
import com.yanxiu.gphone.jiaoyan.module.message.adapter.MessagePagerAdapter;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.util.YXScreenUtil;

/**
 * Created by Hu Chao on 18/10/8.
 */
@Route(path = RoutePathConfig.Message_Fragment)
public class MessageFragment extends JYBaseFragment {

    private TabLayout tab_layout;
    private ViewPager view_pager;
    private MessagePagerAdapter mPageAdapter;

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.message_fragment;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        tab_layout = contentView.findViewById(R.id.tab_layout);
        view_pager = contentView.findViewById(R.id.view_pager);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void doBusiness() {
        mPageAdapter = new MessagePagerAdapter(getFragmentManager());
        view_pager.setAdapter(mPageAdapter);
        tab_layout.setupWithViewPager(view_pager);
        TabLayoutUtil.setTabLayoutDrivider(tab_layout);
    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }
}
