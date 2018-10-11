package com.yanxiu.gphone.jiaoyan.module.signin.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.yanxiu.gphone.jiaoyan.module.signin.R;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

/**
 * 设置学科学段
 * Created by Hu Chao on 18/10/11.
 */
@Route(path = RoutePathConfig.SIGNIN_SET_SUBJECT_ACTIVITY)
public class SetSubjectActivity extends JYBaseActivity {

    private String[] level = {"小学", "初中", "高中", "大学"};
    private String[] subject = {"语文", "数学", "英语", "物理", "化学", "生物", "计算机", "思想品德",
            "语文", "数学", "英语", "物理", "化学", "生物", "计算机", "思想品德"};

    private TagFlowLayout tag_flow_layout_level;
    private TagFlowLayout tag_flow_layout_subject;

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.signin_activity_set_subject;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        tag_flow_layout_level = contentView.findViewById(R.id.tag_flow_layout_level);
        tag_flow_layout_subject = contentView.findViewById(R.id.tag_flow_layout_subject);
        tag_flow_layout_level.setMaxSelectCount(1);
        tag_flow_layout_level.setAdapter(new TagAdapter<String>(level) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(SetSubjectActivity.this).inflate(R.layout.custom_flow_item_layout,
                        parent, false);
                tv.setText(s);
                return tv;
            }
        });
        tag_flow_layout_subject.setMaxSelectCount(2);
        tag_flow_layout_subject.setAdapter(new TagAdapter<String>(subject) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(SetSubjectActivity.this).inflate(R.layout.custom_flow_item_layout,
                        parent, false);
                tv.setText(s);
                return tv;
            }
        });
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
}
