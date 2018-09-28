package com.yanxiu.gphone.jiaoyan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.test.yanxiu.common_base.route.RouteUtils;

public abstract class BaseFragmentFactory {
    private FragmentManager mFragmentManager;
    private int mCurrentIndex;
    private Fragment[] mFragments;
    private String[] mRoutePath;

    public BaseFragmentFactory(Bundle savedInstanceState, FragmentManager fragmentManager) {
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt("mCurrentIndex");
        } else {
            mCurrentIndex = 0;
        }
        mFragmentManager = fragmentManager;
        mRoutePath = initFragmentPath();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mFragments = new Fragment[mRoutePath.length];
        for (int i = 0; i < mFragments.length; i++) {
            mFragments[i] = fragmentManager.findFragmentByTag(mRoutePath[i]);
            if (mFragments[i] == null) {
                mFragments[i] = RouteUtils.getFramentByPath(mRoutePath[i]);
                transaction.add(initContainerId(), mFragments[i], mRoutePath[i]);
            }
            transaction.hide(mFragments[i]);
        }
        transaction.show(mFragments[mCurrentIndex]);
        transaction.commit();
    }

    public abstract int initContainerId();

    public abstract String[] initFragmentPath();

    public void hideAndShowFragment(int index) {
        if (index == mCurrentIndex) {
            return;
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.hide(mFragments[mCurrentIndex]);
        mCurrentIndex = index;
        transaction.show(mFragments[mCurrentIndex]);
        transaction.commit();
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("mCurrentIndex", mCurrentIndex);
    }

    public int getCurrentIndex() {
        return mCurrentIndex;
    }
}
