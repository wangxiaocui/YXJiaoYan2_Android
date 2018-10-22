package com.yanxiu.gphone.jiaoyan.business.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

/**
 * Created By cailei on 2018/10/19
 */
@Route(path = RoutePathConfig.Mine_My_Money_Activity)
public class MineMyMoneyActivity extends JYBaseActivity {
    private TextView tv_today_checkin;
    private ImageView iv_today_checkin;
    private TextView tv_complete_info;
    private TextView tv_qualify_cert;

    @Override
    public int bindLayout() {
        return R.layout.mine_activity_my_money;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        tv_today_checkin = contentView.findViewById(R.id.tv_today_checkin);
        iv_today_checkin = contentView.findViewById(R.id.iv_today_checkin);
        tv_complete_info = contentView.findViewById(R.id.tv_complete_info);
        tv_qualify_cert = contentView.findViewById(R.id.tv_qualify_cert);
    }

    @Override
    public void initListener() {
        tv_today_checkin.setOnClickListener(this);
        tv_complete_info.setOnClickListener(this);
        tv_qualify_cert.setOnClickListener(this);
    }

    @Override
    public void onWidgetClick(View view) {
        if (view == tv_today_checkin) {
            tv_today_checkin.setEnabled(false);
            iv_today_checkin.setVisibility(View.VISIBLE);
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
        getJyDefaultToolbar();
    }
}
