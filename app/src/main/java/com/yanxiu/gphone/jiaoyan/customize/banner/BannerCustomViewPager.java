package com.yanxiu.gphone.jiaoyan.customize.banner;

import android.content.Context;
import android.util.AttributeSet;

import com.youth.banner.view.BannerViewPager;


public class BannerCustomViewPager extends BannerViewPager {

    public BannerCustomViewPager(Context context) {
        super(context);
        init();
    }

    public BannerCustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setPageMargin(getPaddingRight() / 2);
    }
}
