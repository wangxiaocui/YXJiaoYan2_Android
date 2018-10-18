package com.yanxiu.gphone.jiaoyan.business.course_detail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.base.ui.recycler_view.RecyclerLayoutManager.FullyLinearLayoutManager;
import com.test.yanxiu.common_base.customize.aggregate.vieweffect.GradientEffect;
import com.test.yanxiu.common_base.customize.aggregate.vieweffect.GradientEffectImpl;
import com.test.yanxiu.common_base.customize.view.StarProgressBar;
import com.test.yanxiu.common_base.customize.viewgroup.CustomTabView;
import com.test.yanxiu.common_base.customize.viewgroup.UnFocusableScrollView;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.test.yanxiu.common_base.route.data.CourseDetailTabData;
import com.test.yanxiu.common_base.route.data.RouteCourseDetailCommitData;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course_detail.adapter.DirectioryAdapter;
import com.yanxiu.gphone.jiaoyan.business.course_detail.adapter.EvaluationAdapter;
import com.yanxiu.gphone.jiaoyan.business.course_detail.bean.DirectioryBean;
import com.yanxiu.gphone.jiaoyan.business.course_detail.bean.EvalutionBean;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.util.YXToastUtil;

import java.util.ArrayList;

@Route(path = RoutePathConfig.App_Course_Detail)
public class CourseDetailActivity extends JYBaseActivity {

    public static final int EVALUATION_CODE = 15890;
    private CustomTabView tab_layout;
    private UnFocusableScrollView scrollView;

    private TextView tv_introduction;//课程
    private TextView tv_introduction_all;//课程简介全部
    private TextView tv_introduction_content;//课程简介内容

    private TextView tv_directory;//目录
    private TextView tv_directory_all;//目录全部
    private RecyclerView rv_directory;//目录

    private TextView tv_evalution;//评价
    private TextView tv_evalution_all;//评价全部
    private CardView card_view_evalution;//评价view
    private RecyclerView rv_evalution;//评价
    private EvaluationAdapter evaluationAdapter;
    private StarProgressBar starProgressBar;//评价里的评分

    private int tv_introduction_top, tv_directory_top, tv_evalution_top;

    private GradientEffectImpl mGradientEffect;

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.course_detail_activity;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        scrollView = findViewById(R.id.scrollView);
        initTabLayout();
        initIntroductionView();
        initDirectoryView();
        initEvalutionView();
        setDragAmin();
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getJyDefaultToolbar().addRightIcon(View.generateViewId(), R.drawable.homepage_my, 20, 20, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YXToastUtil.showToast("right");
            }
        });
    }

    private void initTabLayout() {
        tab_layout = findViewById(R.id.tab_layout);
    }

    private void initIntroductionView() {
        tv_introduction = findViewById(R.id.tv_introduction);
        tv_introduction_all = findViewById(R.id.tv_introduction_all);
        tv_introduction_content = findViewById(R.id.tv_introduction_content);
    }

    private void initDirectoryView() {
        tv_directory = findViewById(R.id.tv_directory);
        tv_directory_all = findViewById(R.id.tv_directory_all);
        rv_directory = findViewById(R.id.rv_directory);
    }

    private void initEvalutionView() {
        card_view_evalution = findViewById(R.id.card_view_evalution);
        tv_evalution = findViewById(R.id.tv_evalution);
        tv_evalution_all = findViewById(R.id.tv_evalution_all);
        rv_evalution = findViewById(R.id.rv);
        starProgressBar = findViewById(R.id.star_progress);
        starProgressBar.setBarTouchAble(false);
    }

    private void setData() {
        setIntroductionData();
        setDirectoryData();
        setEvalutionData();
    }

    private void setIntroductionData() {
        tv_introduction_content.setText("爱神的箭爱打架爱神的箭安康的境况爱神的箭奥斯卡来得及阿卡啥恐龙当家奥斯卡来得快大家唉看领导是考虑到就爱看的按十块李达康卡洛斯达");
    }

    private void setDirectoryData() {
        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_directory.setLayoutManager(linearLayoutManager);
        DirectioryAdapter adapter = new DirectioryAdapter(this);
        ArrayList<DirectioryBean> list = new ArrayList();
        for (int i = 0; i < 3; i++) {
            list.add(new DirectioryBean());
        }
        adapter.setData(list);
        rv_directory.setAdapter(adapter);
    }

    private void setEvalutionData() {
        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_evalution.setLayoutManager(linearLayoutManager);
        evaluationAdapter = new EvaluationAdapter(this);
        ArrayList<EvalutionBean> list = new ArrayList();
        for (int i = 0; i < 3; i++) {
            list.add(new EvalutionBean());
        }
        evaluationAdapter.setData(list);
        rv_evalution.setAdapter(evaluationAdapter);
    }

    @Override
    public void doBusiness() {
        setData();
    }

    @Override
    public void initListener() {
        tv_introduction_all.setOnClickListener(this);
        tv_directory_all.setOnClickListener(this);
        tv_evalution_all.setOnClickListener(this);
        card_view_evalution.setOnClickListener(this);
        tab_layout.setOnTabClickListener(new CustomTabView.OnTabClickListener() {

            @Override
            public void tabClick(int position) {
                int scrollToY = -1;
                switch (position) {
                    case 1:
                        scrollToY = tv_introduction_top;
                        break;
                    case 2:
                        scrollToY = tv_directory_top;
                        break;
                    case 3:
                        scrollToY = tv_evalution_top;
                        break;
                }
                if (scrollToY != -1) {
                    scrollView.smoothScrollTo(0, scrollToY);
                }
            }
        });
    }

    @Override
    public void onWidgetClick(View view) {
        CourseDetailTabData data = new CourseDetailTabData();
        switch (view.getId()) {
            case R.id.tv_introduction_all:
                data.setTabposition(1);
                RouteUtils.startActivityWithData(RoutePathConfig.App_Course_Detail_Tab, data);
                break;
            case R.id.tv_directory_all:
                data.setTabposition(2);
                RouteUtils.startActivityWithData(RoutePathConfig.App_Course_Detail_Tab, data);
                break;
            case R.id.tv_evalution_all:
                data.setTabposition(3);
                RouteUtils.startActivityWithData(RoutePathConfig.App_Course_Detail_Tab, data);
                break;
            case R.id.card_view_evalution:
                RouteCourseDetailCommitData commitData = new RouteCourseDetailCommitData();
                commitData.setCourseId("test");
                RouteUtils.startActivityForResult(CourseDetailActivity.this, RoutePathConfig.App_Course_Detail_Go_Evaluation, EVALUATION_CODE, commitData);
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        tv_introduction_top = ((View) tv_introduction.getParent()).getTop();  //滑动到tv_introduction需要的距离
        tv_directory_top = ((View) tv_directory.getParent()).getTop();  //滑动到tv_directory需要的距离
        tv_evalution_top = ((View) tv_evalution.getParent()).getTop();  //滑动到tv_evalution需要的距离
    }

    /**
     * 设置拖拽渐变动画回调
     */
    private void setDragAmin() {
        tab_layout.getBackground().mutate();//因为加了alpha渐变，不加该方法，会在个别手机上造成闪屏。
        View sliderView = findViewById(R.id.view_blank);

        mGradientEffect = new GradientEffectImpl(tab_layout, sliderView, scrollView);
        mGradientEffect.setOnGradientEffectListener(new GradientEffect.OnGradientEffectListener() {
            @Override
            public void onGrade(float ratio) {
                //设置导航条背景透明度
//                int alpha = (int) (ratio * 255);
                tab_layout.setAlpha(ratio);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EVALUATION_CODE) {
            if (resultCode == RESULT_OK) {
                RouteCourseDetailCommitData commitData = (RouteCourseDetailCommitData) data.getSerializableExtra(RoutePathConfig.App_Course_Detail_Go_Evaluation);
                YXToastUtil.showToast(commitData.getEvalutionContent());
                evaluationAdapter.addData(new EvalutionBean());
            }
        }
    }
}
