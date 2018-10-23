package com.yanxiu.gphone.jiaoyan.business.mine.cert_verify;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.mine.MineFragment;
import com.yanxiu.lib.yx_basic_library.YXBaseActivity;
import com.yanxiu.lib.yx_basic_library.util.permission.OnPermissionCallback;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 提供资质认证中用到的上传照片功能
 * Created by lufengqing on 2018/10/23.
 */
public class MineUploadPictureUtil {
    public static final int REQUEST_CODE_CAMERA = 0x01;
    public static final int REQUEST_CODE_ALBUM = 0x02;
    public static final int REQUEST_CODE_PIC_CROP = 0x03;
    public static Activity mActivity;
    public static Uri mPicCropUri;

    public static void getPhotoFromCamera(final Activity activity) {
        mActivity = activity;
        YXBaseActivity.requestCameraPermission(new OnPermissionCallback() {
            @Override
            public void onPermissionsGranted(@Nullable List<String> deniedPermissions) {
                Intent intent = new Intent(activity, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true);
                activity.startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }

            @Override
            public void onPermissionsDenied(@Nullable List<String> deniedPermissions) {
                Toast.makeText(activity, R.string.no_storage_permissions, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void getPhotoFromAlbum(final Activity activity) {
        mActivity = activity;
        YXBaseActivity.requestWriteAndReadPermission(new OnPermissionCallback() {
            @Override
            public void onPermissionsGranted(@Nullable List<String> deniedPermissions) {
                Intent intent = new Intent(activity, ImageGridActivity.class);
                activity.startActivityForResult(intent, REQUEST_CODE_ALBUM);
            }

            @Override
            public void onPermissionsDenied(@Nullable List<String> deniedPermissions) {
                Toast.makeText(activity, R.string.no_storage_permissions, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
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

                // todo:上传图片请求
//                mPresenter.doUploadPortrait(mPicCropUri.getPath());
//
//                RequestOptions options = new RequestOptions();
//                options.skipMemoryCache(true)
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .centerCrop();
//
//                Glide.with(mContext)
//                        .asDrawable()
//                        .load(mPicCropUri)
//                        .apply(options)
//                        .into(iv_portrait);
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
    public static ArrayList<ImageItem> createSelectedImagesList(Intent data) {
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
    public static void doPicCrop(Uri uri, Uri saveCroppedImageFileUri) {
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
        mActivity.startActivityForResult(intent, REQUEST_CODE_PIC_CROP);
    }
}
