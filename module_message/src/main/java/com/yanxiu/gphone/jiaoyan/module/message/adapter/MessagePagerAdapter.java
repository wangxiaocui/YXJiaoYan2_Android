package com.yanxiu.gphone.jiaoyan.module.message.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Hu Chao on 18/10/18.
 */
public class MessagePagerAdapter extends FragmentStatePagerAdapter {

    private List<String> mDatas;
    private List<String> mPath;
    private HashMap<String, Fragment> fragmentHashMap;

    public MessagePagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentHashMap = new HashMap<>();
        mDatas = Arrays.asList("互动消息", "系统消息");
        mPath = Arrays.asList(RoutePathConfig.Message_Interact_Fragment, RoutePathConfig.Message_System_Fragment);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        String id = mDatas.get(position);
        if (!fragmentHashMap.containsKey(id)) {
            fragment = RouteUtils.getFramentByPath(mPath.get(position));
            fragmentHashMap.put(id, fragment);
        } else {
            fragment = fragmentHashMap.get(id);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas.get(position);
    }
}
