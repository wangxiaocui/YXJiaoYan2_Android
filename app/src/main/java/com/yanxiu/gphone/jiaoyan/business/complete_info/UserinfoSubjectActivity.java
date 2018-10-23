package com.yanxiu.gphone.jiaoyan.business.complete_info;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.bean.xueduanxueke.LevelBean;
import com.test.yanxiu.common_base.bean.xueduanxueke.SubjectBean;
import com.test.yanxiu.common_base.bean.xueduanxueke.XueduanXuekeBean;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.data.RouteSubjectTypeData;
import com.test.yanxiu.common_base.utils.FileUtils;
import com.yanxiu.gphone.jiaoyan.module.signin.R;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 设置用户学科学段以及感兴趣的学科学段
 * 用户个人资料的学段学科都只能单选，感兴趣的学段单选、学科可多选
 * Created by Hu Chao on 18/10/11.
 */
@Route(path = RoutePathConfig.User_Info_Subject_Activity)
public class UserinfoSubjectActivity extends JYBaseActivity {

    public List<LevelBean> mDatas;
    public List<SubjectBean> mSubjectDatas;

    private View ll_subject;
    private TagFlowLayout tag_flow_layout_level;
    private TagFlowLayout tag_flow_layout_subject;
    private Button btn_ok;
    //选中的学段，学段只能单选
    private LevelBean mSelectLevel;
    //选中的学科，感兴趣的学科可多选，其他单选
    private List<SubjectBean> mSelectedSubjects;
    //从哪进入学科学段选择页面，用于确定学科是否可多选
    private int mFrom = RouteSubjectTypeData.FROM_USER_INFO;

    @Override
    public void initData(@NonNull Bundle bundle) {
        RouteSubjectTypeData data = (RouteSubjectTypeData) bundle.getSerializable(RoutePathConfig.User_Info_Subject_Activity);
        if (data != null) {
            mFrom = data.getFrom();
        }
        String json = FileUtils.getFromAssets("xueduanxueke.json");
        Gson gson = new Gson();
        mDatas = gson.fromJson(json, XueduanXuekeBean.class).getData();
        mSubjectDatas = new ArrayList<>();
        mSelectedSubjects = new ArrayList<>();
    }

    @Override
    public int bindLayout() {
        return R.layout.signin_activity_set_subject;
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getJyDefaultToolbar("学段/学科", true);
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        ll_subject = contentView.findViewById(R.id.ll_subject);
        btn_ok = contentView.findViewById(R.id.btn_ok);
        tag_flow_layout_level = contentView.findViewById(R.id.tag_flow_layout_level);
        tag_flow_layout_subject = contentView.findViewById(R.id.tag_flow_layout_subject);
        tag_flow_layout_level.setMaxSelectCount(1);
        tag_flow_layout_level.setAdapter(new TagAdapter<LevelBean>(mDatas) {

            @Override
            public View getView(FlowLayout parent, int position, LevelBean levelBean) {
                TextView tv = (TextView) LayoutInflater.from(UserinfoSubjectActivity.this).inflate(R.layout.custom_flow_item_layout,
                        parent, false);
                tv.setText(levelBean.getName());
                return tv;
            }
        });
        tag_flow_layout_subject.setAdapter(new TagAdapter<SubjectBean>(mSubjectDatas) {

            @Override
            public View getView(FlowLayout parent, int position, SubjectBean s) {
                TextView tv = (TextView) LayoutInflater.from(UserinfoSubjectActivity.this).inflate(R.layout.custom_flow_item_layout,
                        parent, false);
                tv.setText(s.getName());
                return tv;
            }


        });

        //个人资料的学段学科单选，感兴趣的学段学科可多选
        if (mFrom == RouteSubjectTypeData.FROM_USER_INFO) {
            tag_flow_layout_subject.setMaxSelectCount(1);
        }
        //默认无选中学段，隐藏学科
        ll_subject.setVisibility(View.GONE);
        refreshBtnState();
    }


    @Override
    public void initListener() {
        btn_ok.setOnClickListener(this);
        tag_flow_layout_level.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                mSubjectDatas.clear();
                mSelectedSubjects.clear();
                mSelectLevel = null;
                if (selectPosSet.isEmpty()) {
                    ll_subject.setVisibility(View.GONE);
                } else {
                    ll_subject.setVisibility(View.VISIBLE);
                    mSelectLevel = mDatas.get(selectPosSet.iterator().next());
                    mSubjectDatas.addAll(mSelectLevel.getSubjects());
                    tag_flow_layout_subject.onChanged();
                }
                refreshBtnState();
            }
        });
        tag_flow_layout_subject.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                mSelectedSubjects.clear();
                Iterator<Integer> iterator = selectPosSet.iterator();
                while (iterator.hasNext()) {
                    mSelectedSubjects.add(mSubjectDatas.get(iterator.next()));
                }
                refreshBtnState();
            }
        });
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                Intent intent = new Intent();
                intent.putExtra("level", mSelectLevel);
                intent.putExtra("subject", (Serializable) mSelectedSubjects);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }

    private void refreshBtnState() {
        btn_ok.setEnabled(mSelectLevel != null && !mSelectedSubjects.isEmpty());
    }
}
