package com.yanxiu.gphone.jiaoyan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yanxiu.lib.yx_basic_library.YXBaseActivity;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

/**
 * 首页
 *
 * @author 戴延枫
 */
public class MainActivity extends YXBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }

}
