package com.yanxiu.gphone.jiaoyan.business.video.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.video.fragment.VideoInfoFragment;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.base.bean.YXBaseBean;
import com.yanxiu.lib.yx_basic_library.util.YXToastUtil;

/**
 * 视频页面
 * Created by 戴延枫 on 2018/10/18.
 */
@Route(path = RoutePathConfig.Video_Activity)
public class VideoActiivity extends JYBaseActivity {

    private VideoInfoFragment videoInfoFragment;

    /**
     * 绑定布局
     *
     * @return 布局 Id
     */
    @Override
    public int bindLayout() {
        return R.layout.video_activity;
    }

    /**
     * 初始化数据
     *
     * @param bundle 传递过来的 bundle
     */
    @Override
    public void initData(@NonNull Bundle bundle) {
        super.initTitle();
        getJyDefaultToolbar().addRightIcon(View.generateViewId(), R.drawable.homepage_my, 20, 20, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YXToastUtil.showToast("right");
            }
        });
    }

    /**
     * 初始化 view
     *
     * @param savedInstanceState
     * @param contentView
     */
    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        videoInfoFragment = RouteUtils.getFramentByPath(RoutePathConfig.Video_Activity_Fragment,new YXBaseBean());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.video_info_container, videoInfoFragment)
                .commitAllowingStateLoss();
    }

    /**
     * 初始化 监听
     */
    @Override
    public void initListener() {

    }

    /**
     * 视图点击事件
     *
     * @param view 视图
     */
    @Override
    public void onWidgetClick(View view) {

    }

    /**
     * 业务操作
     */
    @Override
    public void doBusiness() {

    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }
}
