package com.yanxiu.gphone.jiaoyan.business.mine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.util.logger.YXLogger;
import com.yanxiu.lib.yx_basic_library.util.permission.OnPermissionCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

/**
 * Created By cailei on 2018/10/22
 */
@Route(path = RoutePathConfig.Mine_My_Cert_Detail_Activity)
public class MineMyCertDetailActivity extends JYBaseActivity {
    private ImageView iv_cert;
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
        iv_cert = contentView.findViewById(R.id.iv_cert);
        iv_cert.setDrawingCacheEnabled(true);
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
        // http://c.hiphotos.baidu.com/image/h%3D300/sign=06f18776399b033b3388fada25cf3620/77c6a7efce1b9d162f210013fedeb48f8d5464da.jpg
        // http://e.hiphotos.baidu.com/image/pic/item/b8014a90f603738d952a8450be1bb051f819ec64.jpg

        requestWriteAndReadPermission(new OnPermissionCallback() {
            @Override
            public void onPermissionsGranted(@Nullable List<String> deniedPermissions) {
                Bitmap bitmap = iv_cert.getDrawingCache();

                String url = MediaStore.Images.Media.insertImage(MineMyCertDetailActivity.this.getContentResolver(),
                        bitmap,
                        UUID.randomUUID().toString(),
                        null);

                MediaScannerConnection.scanFile(MineMyCertDetailActivity.this, new String[]{url}, null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            public void onScanCompleted(String path, Uri uri) {
                                Toast.makeText(MineMyCertDetailActivity.this,
                                        "保存成功",
                                        Toast.LENGTH_LONG);
                            }
                        });
            }

            @Override
            public void onPermissionsDenied(@Nullable List<String> deniedPermissions) {
                Toast.makeText(MineMyCertDetailActivity.this, R.string.no_storage_permissions, Toast.LENGTH_LONG).show();
            }
        });
    }
}
