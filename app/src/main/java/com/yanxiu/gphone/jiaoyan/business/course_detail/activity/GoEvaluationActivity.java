package com.yanxiu.gphone.jiaoyan.business.course_detail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.customize.view.StarProgressBar;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.test.yanxiu.common_base.route.data.CourseDetailTabData;
import com.test.yanxiu.common_base.route.data.RouteCourseDetailCommitData;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.util.YXToastUtil;

@Route(path = RoutePathConfig.App_Course_Detail_Go_Evaluation)
public class GoEvaluationActivity extends JYBaseActivity {

    private RouteCourseDetailCommitData mData;

    private TextView tv_title;
    private StarProgressBar starProgressBar;//评价里的评分
    private EditText et_evalaution;
    private Button bt_evalaution_commit;

    private String mScore;//评分


    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {
        mData = (RouteCourseDetailCommitData) bundle.getSerializable(RoutePathConfig.App_Course_Detail_Go_Evaluation);
    }

    @Override
    public int bindLayout() {
        return R.layout.course_detail_do_evaluation_activity;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        tv_title = findViewById(R.id.tv_title);
        et_evalaution = findViewById(R.id.et_evalaution);
        bt_evalaution_commit = findViewById(R.id.bt_evalaution_commit);
        starProgressBar = findViewById(R.id.star_progress);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getDefaultStyleToolbar().addRightIcon(View.generateViewId(), R.color.color_1da1f2, 20, 20, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YXToastUtil.showToast("right");
            }
        }).apply();
    }

    @Override
    public void doBusiness() {
    }

    @Override
    public void initListener() {
        bt_evalaution_commit.setOnClickListener(this);
        starProgressBar.setOnStarChangeListener(new StarProgressBar.OnStarChangeListener() {

            @Override
            public void onStarChange(float mark) {
                checkoutScoreAndShowTitle(mark);
                mScore = mark + "";
            }
        });
    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.bt_evalaution_commit:
                submitEvalution();
                break;
        }
    }

    /**
     * 根据评分，显示title的内容
     */
    private void checkoutScoreAndShowTitle(float score) {
        String title = "发表评价";
        if (0 == score) {
            title = "发表评价";
        } else if (0 < score && score <= 1) {
            title = "我要吐槽";
        } else if (1 < score && score <= 2) {
            title = "有点失望";
        } else if (2 < score && score <= 3) {
            title = "还行吧";
        } else if (3 < score && score <= 4) {
            title = "值得推荐";
        } else if (4 < score && score <= 5) {
            title = "超级棒的课程";
        }
        tv_title.setText(title);
    }

    private void submitEvalution() {
        String evalutionContent = et_evalaution.getText().toString();
        if (!TextUtils.isEmpty(evalutionContent)) {
            YXToastUtil.showToast(mScore);
            Intent intent = getIntent();
            mData.setEvalutionContent(evalutionContent);
            intent.putExtra(RoutePathConfig.App_Course_Detail_Go_Evaluation, mData);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
