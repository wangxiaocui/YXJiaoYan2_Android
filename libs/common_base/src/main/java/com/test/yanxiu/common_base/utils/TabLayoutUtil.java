package com.test.yanxiu.common_base.utils;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;

import com.test.yanxiu.common_base.R;
import com.yanxiu.lib.yx_basic_library.util.YXScreenUtil;

/**
 * Created by Hu Chao on 18/10/19.
 */
public class TabLayoutUtil {

    public static void setTabLayoutDrivider(final TabLayout tabLayout) {
        final LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(tabLayout.getContext(), R.drawable.common_tab_layout_divider_shape));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                linearLayout.setDividerPadding((linearLayout.getHeight() - YXScreenUtil.dpToPxInt(tabLayout.getContext(), 19)) / 2);
            }
        });
    }
}
