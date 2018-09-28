package com.yanxiu.gphone.jiaoyan;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.test.yanxiu.common_base.route.RoutePathConfig;

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
                RoutePathConfig.Course_Fragment,
                RoutePathConfig.Course_Fragment
        };
    }
}
