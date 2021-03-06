package com.yanxiu.gphone.jiaoyan.business.mine;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.base.ui.toolbar.CommonToolbar;
import com.test.yanxiu.common_base.base.ui.toolbar.Style;
import com.test.yanxiu.common_base.db.SpManager;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.test.yanxiu.common_base.utils.CacheDataManager;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.lib.yx_basic_library.YXApplication;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.util.YXToastUtil;

/**
 * 设置页面，没用mvp
 */
@Route(path = RoutePathConfig.Mine_Setting_Activity)
public class MineSettingActivity extends JYBaseActivity {
    private Switch switch_auto_play_next;
    private Switch switch_allow_play_on_4g;
    private LinearLayout ll_change_4g_video_resolution;
    private TextView tv_resolution;
    private LinearLayout ll_change_password;
    private LinearLayout ll_clear_cache;
    private TextView tv_cache_size;
    private LinearLayout ll_check_version;
    private TextView tv_current_version;

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.mine_setting_activity;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        switch_auto_play_next = contentView.findViewById(R.id.switch_auto_play_next);
        switch_allow_play_on_4g = contentView.findViewById(R.id.switch_allow_play_on_4g);
        ll_change_4g_video_resolution = contentView.findViewById(R.id.ll_change_4g_video_resolution);
        tv_resolution = contentView.findViewById(R.id.tv_resolution);
        ll_change_password = contentView.findViewById(R.id.ll_change_password);
        ll_clear_cache = contentView.findViewById(R.id.ll_clear_cache);
        tv_cache_size = contentView.findViewById(R.id.tv_cache_size);
        ll_check_version = contentView.findViewById(R.id.ll_check_version);
        tv_current_version = contentView.findViewById(R.id.tv_current_version);
    }

    @Override
    public void initListener() {
        switch_auto_play_next.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SpManager.setAutoPlayNext(b);
            }
        });
        switch_allow_play_on_4g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SpManager.setAllow4GPlay(b);
            }
        });
        ll_change_4g_video_resolution.setOnClickListener(this);
        ll_change_password.setOnClickListener(this);
        ll_clear_cache.setOnClickListener(this);
        ll_check_version.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {
        switch_auto_play_next.setChecked(SpManager.getAutoPlayNext());
        switch_allow_play_on_4g.setChecked(SpManager.getAllow4GPlay());

        tv_resolution.setText(SpManager.getVideoResolution4g());

        String ver = "当前版本号：";
        try {
            PackageInfo pkgInfo = YXApplication.getContext().getPackageManager()
                    .getPackageInfo(YXApplication.getContext().getPackageName(), 0);
            ver += pkgInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        tv_current_version.setText(ver);

        long cacheSize = CacheDataManager.getAllCacheSize(YXApplication.getContext());
        String cacheSizeStr = CacheDataManager.getFormatSize(cacheSize);
        tv_cache_size.setText(cacheSizeStr);
    }

    @Override
    public void onWidgetClick(View view) {
        if (view == ll_change_4g_video_resolution) {
            RouteUtils.startActivity(RoutePathConfig.Mine_Video_Resolution_Activity);
        }

        if (view == ll_change_password) {

        }

        if (view == ll_clear_cache) {
            YXToastUtil.showToast("已清除");
            CacheDataManager.ClearAllCache(YXApplication.getContext());
            tv_cache_size.setText("0 KB");
        }

        if (view == ll_check_version) {

        }
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getJyDefaultToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_resolution.setText(SpManager.getVideoResolution4g());
    }

    private View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}
