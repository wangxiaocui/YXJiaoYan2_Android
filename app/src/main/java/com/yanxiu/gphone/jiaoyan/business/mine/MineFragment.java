package com.yanxiu.gphone.jiaoyan.business.mine;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.test.yanxiu.common_base.base.ui.JYBaseFragment;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.test.yanxiu.common_base.third_party.GlideImageLoader;
import com.test.yanxiu.common_base.utils.FileUtils;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.mine.basic_candidate.TreeNode;
import com.yanxiu.gphone.jiaoyan.business.mine.interfaces.MineContract;
import com.yanxiu.gphone.jiaoyan.business.mine.mock.MockAsyncTask;
import com.yanxiu.gphone.jiaoyan.business.mine.presenter.MinePresenter;
import com.yanxiu.gphone.jiaoyan.customize.OptionsViewGroup;
import com.yanxiu.lib.yx_basic_library.YXBaseActivity;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.util.logger.YXLogger;
import com.yanxiu.lib.yx_basic_library.util.permission.OnPermissionCallback;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        if (view == tv_checkin_done) {
            mPresenter.doCheckIn();
        }

        if (view == ll_header) {
            popOptions();
        }

        if (view == item_gerenziliao) {
            YXLogger.d("cailei", "个人资料");
            test();
        }
        if (view == item_zizhirenzheng) {
            YXLogger.d("cailei", "资质证书");
        }

        if (view == item_caifuzhi) {
            // 我的财富值
            RouteUtils.startActivity(RoutePathConfig.Mine_My_Money_Activity);
        }
        if (view == item_wodekecheng) {
            // 我的课程
            RouteUtils.startActivity(RoutePathConfig.Mine_My_Course_Activity);
        }
        if (view == item_wodezhengshu) {
            // 我的证书
            RouteUtils.startActivity(RoutePathConfig.Mine_My_Cert_Activity);
        }
        if (view == item_shezhi) {
            RouteUtils.startActivity(RoutePathConfig.Mine_Setting_Activity);
        }

    }

    @Override
    protected MineContract.IPresenter initPresenterImpl() {
        return new MinePresenter(this);
    }

    // region 更换头像 + 手机号
    private static final int REQUEST_CODE_CAMERA = 0x01;
    private static final int REQUEST_CODE_ALBUM = 0x02;
    private static final int REQUEST_CODE_PIC_CROP = 0x03;

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
                    changePhone();
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
        RouteUtils.startActivity(RoutePathConfig.Mine_Change_Pwd_Step1_Activity);
    }

    private Uri mPicCropUri;
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

                Uri org = Uri.fromFile(new File(item.path));
                Uri des = Uri.fromFile(new File(item.path + "_crop"));
                new File(des.getPath()).delete();
                mPicCropUri = des;
                doPicCrop(org, des);
                break;

            case REQUEST_CODE_PIC_CROP:
                if (!new File(mPicCropUri.getPath()).exists()) {
                    //不裁剪 也直接返回
                    return;
                }

                // todo: cailei
                mPresenter.doUploadPortrait(mPicCropUri.getPath());

                RequestOptions options = new RequestOptions();
                options.skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .centerCrop();

                Glide.with(getActivity())
                        .asDrawable()
                        .load(mPicCropUri)
                        .apply(options)
                        .into(iv_portrait);
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

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void doPicCrop(Uri uri, Uri saveCroppedImageFileUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", false);
        //输出图片格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //取消人脸识别
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, saveCroppedImageFileUri);
        startActivityForResult(intent, REQUEST_CODE_PIC_CROP);
    }
    // endregion 更换头像 + 手机号

    // region mvp
    @Override
    public void onCheckInDone() {
        tv_checkin.setVisibility(View.GONE);
        tv_checkin_done.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(Error error) {
        Toast.makeText(MineFragment.this.getActivity(), error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserInfoUpdate() {

    }
    // endregion mvp


    private ArrayList<TreeNode> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<TreeNode>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<TreeNode>>> options3Items = new ArrayList<>();
    public void test() {
        String json = FileUtils.getFromAssets("area.json");
        Gson gson = new Gson();
        TreeNode tree = gson.fromJson(json, TreeNode.class);

        TreeNode.setParentRecurse(tree);
        int j = 0;

        OptionsPickerView pvOptions = new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String id = options1Items.get(options1).uid + "\n" +
                        options2Items.get(options1).get(options2).uid + "\n" +
                        options3Items.get(options1).get(options2).get(options3).uid;

                String name = options1Items.get(options1).toString() + "\n" +
                        options2Items.get(options1).get(options2).toString() + "\n" +
                        options3Items.get(options1).get(options2).get(options3).toString();

                YXLogger.d("cailei", "id : " + id + "\nname : " + name);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .isDialog(true)
                .build();

        // 调整位置
        Dialog mDialog = pvOptions.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvOptions.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }

        options1Items = tree.children;
        options2Items = new ArrayList<>();
        options3Items = new ArrayList<>();
        for (TreeNode node : tree.children) {
            options2Items.add(node.children);
        }

        for (TreeNode node : tree.children) {
            ArrayList<ArrayList<TreeNode>> list = new ArrayList<>();
            for (TreeNode node2 : node.children) {
                list.add(node2.children);
            }
            options3Items.add(list);
        }

        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show(item_gerenziliao);
    }
}
