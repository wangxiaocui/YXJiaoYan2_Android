package com.yanxiu.gphone.jiaoyan.customize.banner;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yanxiu.lib.yx_basic_library.customize.view.RoundCornerImageView;
import com.youth.banner.loader.ImageLoader;


public class BannerGlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        Glide.with(context.getApplicationContext())
                .load(path)
                .into(imageView);
    }

    @Override
    public ImageView createImageView(Context context) {
        RoundCornerImageView imageView = new RoundCornerImageView(context);
        imageView.setCornerSize(6);
        return imageView;
    }
}
