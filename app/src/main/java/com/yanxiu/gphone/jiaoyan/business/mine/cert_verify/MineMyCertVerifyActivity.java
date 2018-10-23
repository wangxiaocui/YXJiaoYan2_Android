package com.yanxiu.gphone.jiaoyan.business.mine.cert_verify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

/**
 * 我的--->资质认证
 * Created by lufengqing on 2018/10/22.
 */
@Route(path = RoutePathConfig.Mine_My_Cert_Verify_Activity)
public class MineMyCertVerifyActivity extends JYBaseActivity {
    private ImageView iv_cert_verify_guide;
    private TextView tv_realname_verify;
    private TextView tv_teacher_cert_verify;
    private TextView tv_professor_cert_verify;
    private TextView tv_education_cert_verify;
    private TextView tv_tech_cert_verify;
    private TextView tv_prize_cert_verify;

    @Override
    public int bindLayout() {
        return R.layout.mine_activity_my_cert_verify;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        iv_cert_verify_guide = contentView.findViewById(R.id.iv_cert_verify_guide);
        tv_realname_verify = contentView.findViewById(R.id.tv_realname_verify);
        tv_teacher_cert_verify = contentView.findViewById(R.id.tv_teacher_cert_verify);
        tv_professor_cert_verify = contentView.findViewById(R.id.tv_professor_cert_verify);
        tv_education_cert_verify = contentView.findViewById(R.id.tv_education_cert_verify);
        tv_tech_cert_verify = contentView.findViewById(R.id.tv_tech_cert_verify);
        tv_prize_cert_verify = contentView.findViewById(R.id.tv_prize_cert_verify);
    }

    @Override
    public void initListener() {
        iv_cert_verify_guide.setOnClickListener(this);
        tv_realname_verify.setOnClickListener(this);
        tv_teacher_cert_verify.setOnClickListener(this);
        tv_professor_cert_verify.setOnClickListener(this);
        tv_education_cert_verify.setOnClickListener(this);
        tv_tech_cert_verify.setOnClickListener(this);
        tv_prize_cert_verify.setOnClickListener(this);
    }

    @Override
    public void onWidgetClick(View view) {
        // "资质验证介绍" 页
        if (view == iv_cert_verify_guide) {
            RouteUtils.startActivity(RoutePathConfig.Mine_My_Cert_Verify_Guide_Activity);
        }
        //"实名认证"页
        if (view == tv_realname_verify) {
            RouteUtils.startActivity(RoutePathConfig.Mine_My_RealName_Verify_Activity);
        }
        //"教师资格证"页
        if (view == tv_teacher_cert_verify) {
            RouteUtils.startActivity(RoutePathConfig.Mine_My_teacher_Verify_Activity);
        }
        //"教研员"页
        if (view == tv_professor_cert_verify) {
            RouteUtils.startActivity(RoutePathConfig.Mine_My_Professor_Verify_Activity);
        }
        //"学历认证"页
        if (view == tv_education_cert_verify) {
            RouteUtils.startActivity(RoutePathConfig.Mine_My_Education_Verify_Activity);
        }
        //"职称证书"页
        if (view == tv_tech_cert_verify) {
            RouteUtils.startActivity(RoutePathConfig.Mine_My_Tech_Verify_Activity);
        }
        //"荣誉证书"页
        if (view == tv_prize_cert_verify) {
            RouteUtils.startActivity(RoutePathConfig.Mine_My_Prize_Verify_Activity);
        }
    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }
}
