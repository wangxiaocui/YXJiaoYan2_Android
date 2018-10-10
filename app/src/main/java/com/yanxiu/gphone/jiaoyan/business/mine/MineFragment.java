package com.yanxiu.gphone.jiaoyan.business.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseFragment;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.util.logger.YXLogger;

/**
 * Created by Cai Lei on 2018/10/9.
 */
@Route(path = RoutePathConfig.Mine_Fragment)
public class MineFragment extends JYBaseFragment {
    private TextView tv_checkin;
    private TextView tv_checkin_done;
    private MineItemLayout item_gerenziliao;
    private MineItemLayout item_zizhirenzheng;
    private MineItemLayout item_caifuzhi;
    private MineItemLayout item_wodekecheng;
    private MineItemLayout item_wodezhengshu;
    private MineItemLayout item_shezhi;

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.mine_fragment;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        tv_checkin = contentView.findViewById(R.id.tv_checkin);
        tv_checkin_done = contentView.findViewById(R.id.tv_checkin_done);
        item_gerenziliao = contentView.findViewById(R.id.item_gerenziliao);
        item_gerenziliao.setTitle("个人资料");
        item_zizhirenzheng = contentView.findViewById(R.id.item_zizhirenzheng);
        item_zizhirenzheng.setTitle("资质认证");
        item_caifuzhi = contentView.findViewById(R.id.item_caifuzhi);
        item_caifuzhi.setTitle("财富值", "898分");
        item_wodekecheng = contentView.findViewById(R.id.item_wodekecheng);
        item_wodekecheng.setTitle("我的课程");
        item_wodezhengshu = contentView.findViewById(R.id.item_wodezhengshu);
        item_wodezhengshu.setTitle("我的证书");
        item_shezhi = contentView.findViewById(R.id.item_shezhi);
        item_shezhi.setTitle("设置");
    }

    @Override
    public void initListener() {
        tv_checkin.setOnClickListener(this);
        item_gerenziliao.setOnClickListener(this);
        item_zizhirenzheng.setOnClickListener(this);
        item_caifuzhi.setOnClickListener(this);
        item_wodekecheng.setOnClickListener(this);
        item_wodezhengshu.setOnClickListener(this);
        item_shezhi.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {
    }

    @Override
    public void onWidgetClick(View view) {
        if (view == tv_checkin) {
            tv_checkin.setVisibility(View.GONE);
            tv_checkin_done.setVisibility(View.VISIBLE);
        }

        if (view == item_gerenziliao) {
            YXLogger.d("cailei", "个人资料");
        }
        if (view == item_zizhirenzheng) {
            YXLogger.d("cailei", "资质证书");
        }

        if (view == item_caifuzhi) {
            YXLogger.d("cailei", "财富值");
        }
        if (view == item_wodekecheng) {
            YXLogger.d("cailei", "我的课程");
        }
        if (view == item_wodezhengshu) {
            YXLogger.d("cailei", "我的证书");
        }
        if (view == item_shezhi) {
            YXLogger.d("cailei", "设置");
        }

    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }
}
