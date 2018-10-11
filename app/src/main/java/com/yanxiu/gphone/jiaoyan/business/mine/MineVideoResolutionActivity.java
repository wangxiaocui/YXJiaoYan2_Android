package com.yanxiu.gphone.jiaoyan.business.mine;

import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.base.ui.recycler_view.OnRecyclerViewItemClickListener;
import com.test.yanxiu.common_base.base.ui.toolbar.CommonToolbar;
import com.test.yanxiu.common_base.base.ui.toolbar.Style;
import com.test.yanxiu.common_base.db.SpManager;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.customize.view.RecycleViewDivider;
import com.yanxiu.lib.yx_basic_library.util.logger.YXLogger;

/**
 * 更改4G下视频清晰图页面
 */
@Route(path = RoutePathConfig.Mine_Video_Resolution_Activity)
public class MineVideoResolutionActivity extends JYBaseActivity {
    private RecyclerView rv_view;

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.mine_video_resolution_activity;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        rv_view = contentView.findViewById(R.id.rv_view);
        rv_view.setLayoutManager(new LinearLayoutManager(this));
        Adaptor adaptor = new Adaptor();
        rv_view.setAdapter(adaptor);
        rv_view.addItemDecoration(new RecycleViewDivider(this,
                LinearLayoutManager.VERTICAL,
                2,
                getResources().getColor(R.color.color_f0f1f3)));
    }

    @Override
    public void initListener() {

    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }

    @Override
    protected void initTitle() {
        super.initTitle();

        // todo: cailei 需要都调试好了放到Base里去
        new CommonToolbar.Builder(this).setStatusBarStyle(Style.DEFAULT)
                .addLeftIcon(View.generateViewId(), com.test.yanxiu.common_base.R.drawable.selector_back, 20, 20, backListener)
                .addLeftText(View.generateViewId(), "返回", 18, getResources().getColor(R.color.color_007aff), backListener)
                .apply();
    }

    private View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    private OnRecyclerViewItemClickListener itemClickListener = new OnRecyclerViewItemClickListener() {
        @Override
        public void onItemClick(View itemView, Object data, int position) {
            VideoResolutionBean bean = (VideoResolutionBean)data;
            SpManager.setVideoResolution4g(bean.title);
            finish();
        }
    };

    private String[] resolutions = {"流畅", "高清"};

    private class Adaptor extends RecyclerView.Adapter<ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            return new ViewHolder(inflater.inflate(R.layout.mine_video_resolution_item, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
            final VideoResolutionBean bean = new VideoResolutionBean();
            bean.title = resolutions[i];
            bean.isSelected = false;
            viewHolder.setData(bean);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(view, bean, viewHolder.getAdapterPosition());
                }
            });
        }

        @Override
        public int getItemCount() {
            return resolutions.length;
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private ImageView iv_selected;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            iv_selected = itemView.findViewById(R.id.iv_selected);
        }

        public void setData(Object data) {
            VideoResolutionBean bean = (VideoResolutionBean)data;
            tv_title.setText(bean.title);
            iv_selected.setVisibility(bean.isSelected ? View.VISIBLE:View.GONE);
        }
    }

    private class VideoResolutionBean {
        public String title;
        public boolean isSelected = false;
    }
}
