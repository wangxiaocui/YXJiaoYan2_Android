package com.yanxiu.gphone.jiaoyan.business.course_detail.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.base.ui.recycler_view.RecyclerLayoutManager.FullyLinearLayoutManager;
import com.test.yanxiu.common_base.customize.view.StarProgressBar;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course_detail.adapter.CourseDetailViewPagerAdapter;
import com.yanxiu.gphone.jiaoyan.business.course_detail.adapter.DirectioryAdapter;
import com.yanxiu.gphone.jiaoyan.business.course_detail.adapter.EvaluationAdapter;
import com.yanxiu.gphone.jiaoyan.business.course_detail.bean.DirectioryBean;
import com.yanxiu.gphone.jiaoyan.business.course_detail.bean.EvalutionBean;
import com.yanxiu.gphone.jiaoyan.business.course_detail.fragment.DirectoryFragment;
import com.yanxiu.gphone.jiaoyan.business.course_detail.fragment.EvaluationFragment;
import com.yanxiu.gphone.jiaoyan.business.course_detail.fragment.IntroductionFragment;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.util.YXToastUtil;
import com.yanxiu.lib.yx_basic_library.util.logger.YXLogger;

import java.util.ArrayList;

@Route(path = RoutePathConfig.App_Course_Detail)
public class CourseDetailActivity extends JYBaseActivity {

    private TextView tv_introduction_all;//课程简介全部
    private TextView tv_introduction_content;//课程简介内容

    private TextView tv_directory_all;//目录全部
    private RecyclerView rv_directory;//目录

    private TextView tv_evalution_all;//评价全部
    private RecyclerView rv_evalution;//评价
    private StarProgressBar starProgressBar;//评价里的评分

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
        initIntroductionView();
        initDirectoryView();
        initEvalutionView();
    }

    @Override
    protected void initTitle() {
        super.initTitle();
    }

    private void initIntroductionView() {
        tv_introduction_all = findViewById(R.id.tv_introduction_all);
        tv_introduction_content = findViewById(R.id.tv_introduction_content);
    }

    private void initDirectoryView() {
        tv_directory_all = findViewById(R.id.tv_directory_all);
        rv_directory = findViewById(R.id.rv_directory);
    }

    private void initEvalutionView() {
        tv_evalution_all = findViewById(R.id.tv_evalution_all);
        rv_evalution = findViewById(R.id.rv);
        starProgressBar = findViewById(R.id.star_progress);
        starProgressBar.setProgressIntValue(60);
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
        EvaluationAdapter adapter = new EvaluationAdapter(this);
        ArrayList<EvalutionBean> list = new ArrayList();
        for (int i = 0; i < 3; i++) {
            list.add(new EvalutionBean());
        }
        adapter.setData(list);
        rv_evalution.setAdapter(adapter);
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
        starProgressBar.setOnStarChangeListener(new StarProgressBar.OnStarChangeListener() {

            @Override
            public void onStarChange(float mark) {
//                text.setText(mark + "分");
                YXLogger.e("dyf", mark + "分");
            }
        });
    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv_introduction_all:
                YXToastUtil.showToast("简介");
                break;
            case R.id.tv_directory_all:
                YXToastUtil.showToast("目录");
                break;
            case R.id.tv_evalution_all:
                YXToastUtil.showToast("评价");
                break;
        }
    }


}
