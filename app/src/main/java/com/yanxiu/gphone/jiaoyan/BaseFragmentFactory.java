package com.yanxiu.gphone.jiaoyan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.test.yanxiu.common_base.route.RouteUtils;

public abstract class BaseFragmentFactory {

    private static final String CURRENT_INDEX = "STATE_CURRENT_INDEX";

    private FragmentManager mFragmentManager;
    private Fragment[] mFragments;
    private String[] mRoutePath;

    protected int mCurrentIndex;

    public BaseFragmentFactory(Bundle savedInstanceState, FragmentManager fragmentManager) {
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(CURRENT_INDEX);
        } else {
            mCurrentIndex = 0;
        }
        mFragmentManager = fragmentManager;
        mRoutePath = initFragmentPath();
        mFragments = new Fragment[mRoutePath.length];
    }

    public abstract int initContainerId();

    public abstract String[] initFragmentPath();

    public void showFragment(int index) {
        if (index == mCurrentIndex && mFragments[mCurrentIndex] != null) {
            return;
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        mFragments[mCurrentIndex] = mFragmentManager.findFragmentByTag(mRoutePath[mCurrentIndex]);
        if (mFragments[mCurrentIndex] != null) {
            transaction.hide(mFragments[mCurrentIndex]);
        }

        mFragments[index] = mFragmentManager.findFragmentByTag(mRoutePath[index]);
        if (mFragments[index] == null) {
            mFragments[index] = RouteUtils.getFramentByPath(mRoutePath[index]);
            if (mFragments[index] != null) {
                transaction.add(initContainerId(), mFragments[index], mRoutePath[index]);
                transaction.show(mFragments[index]);
            }
        } else {
            transaction.show(mFragments[index]);
        }
        mCurrentIndex = index;
        transaction.commit();
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_INDEX, mCurrentIndex);
    }

    public int getCurrentIndex() {
        return mCurrentIndex;
    }
}
