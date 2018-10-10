package com.yanxiu.gphone.jiaoyan.business.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.test.yanxiu.common_base.base.ui.JYBaseFragment;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.third_party.GlideImageLoader;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.mine.interfaces.MineContract;
import com.yanxiu.gphone.jiaoyan.business.mine.mock.MockAsyncTask;
import com.yanxiu.gphone.jiaoyan.business.mine.presenter.MinePresenter;
import com.yanxiu.gphone.jiaoyan.customize.OptionsViewGroup;
import com.yanxiu.lib.yx_basic_library.YXBaseActivity;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.util.logger.YXLogger;
import com.yanxiu.lib.yx_basic_library.util.permission.OnPermissionCallback;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cai Lei on 2018/10/9.
 */
@Route(path = RoutePathConfig.Mine_Fragment)
public class MineFragment extends JYBaseFragment<MineContract.IPresenter>
        implements MineContract.IView {
    private TextView tv_checkin;
    private TextView tv_checkin_done;
    private MineItemLayout item_gerenziliao;
    private MineItemLayout item_zizhirenzheng;
    private MineItemLayout item_caifuzhi;
    private MineItemLayout item_wodekecheng;
    private MineItemLayout item_wodezhengshu;
    private MineItemLayout item_shezhi;

    private LinearLayout ll_header;
    private BottomSheetDialog bottomSheetDialog;
    private ImageView iv_portrait;

    @Override
    public void initData(@NonNull Bundle bundle) {
        setupImagePicker();
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

        ll_header = contentView.findViewById(R.id.ll_header);
        iv_portrait = contentView.findViewById(R.id.iv_portrait);
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
        ll_header.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {
    }

    @Override
    public void onWidgetClick(View view) {
        if (view == tv_checkin) {
            mPresenter.doCheckIn();
        }

        if (view == ll_header) {
            popOptions();
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
    protected MineContract.IPresenter initPresenterImpl() {
        return new MinePresenter(this);
    }

    // region 更换头像 + 手机号
    private static final int REQUEST_CODE_CAMERA = 0x01;
    private static final int REQUEST_CODE_ALBUM = 0x02;

    private void setupImagePicker() {
        GlideImageLoader glideImageLoader = new GlideImageLoader();
        ImagePicker ip = ImagePicker.getInstance();
        ip.setImageLoader(glideImageLoader);
        //显示拍照按钮
        ip.setShowCamera(false);
        //允许裁剪（单选才有效）
        ip.setCrop(false);
        //选中数量限制
        ip.setSelectLimit(1);

    }

    private void popOptions() {
        final String[] options = new String[]{"拍照设置头像", "从相册选择头像", "更换手机号"};

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
                    getPhotoFromCamera();
                }
                if (1 == index) {
                    getPhotoFromAlbum();
                }
                if (2 == index) {

                }
            }
        });

        bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.setContentView(ll_bottom_sheet);

        bottomSheetDialog.show();
    }

    private void getPhotoFromCamera() {


        YXBaseActivity.requestCameraPermission(new OnPermissionCallback() {
            @Override
            public void onPermissionsGranted(@Nullable List<String> deniedPermissions) {
                Intent intent = new Intent(MineFragment.this.getActivity(), ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }

            @Override
            public void onPermissionsDenied(@Nullable List<String> deniedPermissions) {
                Toast.makeText(MineFragment.this.getActivity(), R.string.no_storage_permissions, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPhotoFromAlbum() {
        YXBaseActivity.requestWriteAndReadPermission(new OnPermissionCallback() {
            @Override
            public void onPermissionsGranted(@Nullable List<String> deniedPermissions) {
                Intent intent = new Intent(MineFragment.this.getActivity(), ImageGridActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ALBUM);
            }

            @Override
            public void onPermissionsDenied(@Nullable List<String> deniedPermissions) {
                Toast.makeText(MineFragment.this.getActivity(), R.string.no_storage_permissions, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void changePhone() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
            case REQUEST_CODE_ALBUM:
                ArrayList<ImageItem> imageItems = createSelectedImagesList(data);
                if (imageItems == null || imageItems.size() == 0) {
                    //用户没选图片 直接返回
                    return;
                }
                ImageItem item = imageItems.get(0);
                mPresenter.doUploadPortrait(item.path);

                // todo: cailei
                Glide.with(getActivity()).load(item.path).apply(new RequestOptions().centerCrop()).into(iv_portrait);
                break;
            default:
                break;
        }
    }

    /**
     * 构造需要的图片数据
     *
     * @param data
     */
    private ArrayList<ImageItem> createSelectedImagesList(Intent data) {
        ArrayList<ImageItem> images = null;
        try {
            images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
        } catch (Exception e) {
        }
        if (images == null) {
            return null;
        }
        return images;
    }

    // endregion 更换头像 + 手机号

    // region mvp
    @Override
    public void onCheckInDone() {
        tv_checkin.setVisibility(View.GONE);
        tv_checkin_done.setVisibility(View.VISIBLE);
    }

    @Override
    public void onUserInfoUpdate() {

    }
    // endregion mvp
}
