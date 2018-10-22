package com.yanxiu.gphone.jiaoyan.business.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

/**
 * Created By cailei on 2018/10/22
 */
@Route(path = RoutePathConfig.Mine_My_Cert_Detail_Activity)
public class MineMyCertDetailActivity extends JYBaseActivity {
    private TextView tv_save;
    @Override
    public int bindLayout() {
        return R.layout.mine_activity_my_cert_detail;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        tv_save = contentView.findViewById(R.id.tv_save);
    }

    @Override
    public void initListener() {
        tv_save.setOnClickListener(this);
    }

    @Override
    public void onWidgetClick(View view) {
        if (view == tv_save) {
            doSave();
        }
    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getJyDefaultToolbar("证书详情");
    }

    private void doSave() {

    }
}
