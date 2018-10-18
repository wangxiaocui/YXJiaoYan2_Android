package com.yanxiu.gphone.jiaoyan.business.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.test.yanxiu.common_base.base.ui.JYBaseFragment;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

/**
 * Created By cailei on 2018/10/18
 */
public class MineMyCourseReserveFragment extends JYBaseFragment {
    @Override
    public int bindLayout() {
        return R.layout.mine_fragment_list_base;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        TextView tv = contentView.findViewById(R.id.tv_temp);
        tv.setText("B");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }
}
