package com.yanxiu.gphone.jiaoyan.business.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.BaseFragmentFactory;
import com.yanxiu.gphone.jiaoyan.R;

public class NaviFragmentFactory extends BaseFragmentFactory {

    public NaviFragmentFactory(Bundle savedInstanceState, FragmentManager fragmentManager) {
        super(savedInstanceState, fragmentManager);
    }

    @Override
    public int initContainerId() {
        return R.id.fl_main_container;
    }

    @Override
    public String[] initFragmentPath() {
        return new String[]{
                RoutePathConfig.Course_Fragment,
                RoutePathConfig.Course_Fragment,
                RoutePathConfig.Message_Fragment,
                RoutePathConfig.Mine_Fragment
        };
    }

}
