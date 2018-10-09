package com.yanxiu.gphone.jiaoyan.business.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseFragment;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

/**
 * Created by Cai Lei on 2018/10/9.
 */
@Route(path = RoutePathConfig.Mine_Fragment)
public class MineFragment extends JYBaseFragment {
    private TextView tv_title;

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.mine_fragment;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        tv_title = contentView.findViewById(R.id.tv_title);

    }

    @Override
    public void initListener() {
        tv_title.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        if (view == tv_title) {
            tv_title.setText("就是这样");
        }
    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }
}
