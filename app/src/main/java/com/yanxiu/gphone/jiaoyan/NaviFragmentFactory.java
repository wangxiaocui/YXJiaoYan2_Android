package com.yanxiu.gphone.jiaoyan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;

import com.test.yanxiu.common_base.route.RoutePathConfig;

public class NaviFragmentFactory extends BaseFragmentFactory {

    private View messageRedPoint;

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
                RoutePathConfig.Course_Fragment
        };
    }

    public void bindBottomNavigationView(final BottomNavigationView navigationView) {
        //消息添加红点
//        BottomNavigationItemView itemView = (BottomNavigationItemView) ((BottomNavigationMenuView) navigationView.getChildAt(0)).getChildAt(2);
//        messageRedPoint = LayoutInflater.from(navigationView.getContext()).inflate(R.layout.red_point_layout, itemView, false);
//        itemView.addView(messageRedPoint);
//        messageRedPoint.setVisibility(View.VISIBLE);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_course:
                        showRedPoint();
                        showFragment(0);
                        return true;
                    case R.id.navigation_live:
                        showFragment(1);
                        return true;
                    case R.id.navigation_message:
                        hideRedPoint();
                        showFragment(2);
                        return true;
                    case R.id.navigation_mine:
                        showFragment(3);
                        return true;
                }
                return false;
            }
        });
        navigationView.setSelectedItemId(R.id.navigation_course + mCurrentIndex);
    }

    private void showRedPoint() {
//        messageRedPoint.setVisibility(View.VISIBLE);
    }


    private void hideRedPoint() {
//        messageRedPoint.setVisibility(View.GONE);
    }

}
