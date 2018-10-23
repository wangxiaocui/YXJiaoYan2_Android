package com.yanxiu.gphone.jiaoyan.business.mine.cert_verify;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.customize.OptionsViewGroup;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.util.logger.YXLogger;

/**
 * "实名认证"页
 * Created by lufengqing on 2018/10/23.
 */
@Route(path = RoutePathConfig.Mine_My_RealName_Verify_Activity)
public class MineMyRealNameVerifyActivity extends JYBaseActivity {
    //认证状态 0：还未认证，1：审核中，2：认证失败，3：认证成功
    private int mVerifyState;
    private ImageView iv_identity_front;
    private ImageView iv_identity_back;
    private TextView tv_cert_submit;
    private TextView tv_cert_cancel;
    private TextView tv_fail_reason;
    private BottomSheetDialog bottomSheetDialog;

    @Override
    public int bindLayout() {
        if(mVerifyState == 0) {
            return R.layout.mine_activity_my_realname_verify;
        } else if(mVerifyState == 1) {
            return R.layout.mine_activity_my_realname_verifying;
        } else if(mVerifyState == 2){
            return R.layout.mine_activity_my_realname_verify_result_fail;
        } else{
            return R.layout.mine_activity_my_realname_verify_result_success;
        }
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getJyDefaultToolbar().setTitle("实名认证", 18, getResources().getColor(R.color.color_17171b));
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        iv_identity_front = contentView.findViewById(R.id.iv_identity_front);
        iv_identity_back = contentView.findViewById(R.id.iv_identity_back);
        tv_cert_submit = contentView.findViewById(R.id.tv_cert_submit);
        if(mVerifyState == 2) {
            tv_cert_cancel = contentView.findViewById(R.id.tv_cert_cancel);
            tv_fail_reason = contentView.findViewById(R.id.tv_fail_reason);
        }
    }

    @Override
    public void initListener() {
        iv_identity_front.setOnClickListener(this);
        iv_identity_back.setOnClickListener(this);
        tv_cert_submit.setOnClickListener(this);
        tv_cert_cancel.setOnClickListener(this);
    }

    @Override
    public void onWidgetClick(View view) {
        // 上传身份证前面
        if (view == iv_identity_front) {
            //还未认证或认证失败，可继续上传
            if(mVerifyState == 0 || mVerifyState == 2) {
                popOptions();
            } else { //审核中或认证成功，点击无反应
            }
        }
        //上传身份证后面
        if (view == iv_identity_back) {

        }
        //提交认证
        if (view == tv_cert_submit) {
        }
        //撤销认证
        if (view == tv_cert_cancel) {
        }
    }

    private void popOptions() {
        final String[] options = new String[]{"拍照", "从相册中添加"};

        LinearLayout ll_bottom_sheet = (LinearLayout) getLayoutInflater().inflate(R.layout.mine_portrait_phone_options, null);
        OptionsViewGroup options_view_group = ll_bottom_sheet.findViewById(R.id.options_view_group);
        options_view_group.setupWith(options, "取消");

        options_view_group.setCallback(new OptionsViewGroup.Callback() {
            @Override
            public void onCancel() {
                YXLogger.d("cailei", "cancel");
                bottomSheetDialog.dismiss();
            }

            @Override
            public void onSelect(int index) {
                YXLogger.d("cailei", index+"");
                bottomSheetDialog.dismiss();
                if (0 == index) {
                    MineUploadPictureUtil.getPhotoFromCamera(MineMyRealNameVerifyActivity.this);
                }
                if (1 == index) {
                    MineUploadPictureUtil.getPhotoFromAlbum(MineMyRealNameVerifyActivity.this);
                }
            }
        });

        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.setContentView(ll_bottom_sheet);

        bottomSheetDialog.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MineUploadPictureUtil.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void doBusiness() {

    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }
}
