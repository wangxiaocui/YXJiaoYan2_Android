package com.yanxiu.gphone.jiaoyan.module.signin.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.bean.TreeNode;
import com.test.yanxiu.common_base.bean.xueduanxueke.LevelBean;
import com.test.yanxiu.common_base.bean.xueduanxueke.SubjectBean;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.test.yanxiu.common_base.route.data.RouteSubjectTypeData;
import com.test.yanxiu.common_base.utils.FileUtils;
import com.yanxiu.gphone.jiaoyan.module.signin.R;
import com.yanxiu.gphone.jiaoyan.module.signin.customize.CustomizeItemLayout;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户必填项页面（设置地区、学段学科）
 * Created by Hu Chao on 18/10/22.
 */
@Route(path = RoutePathConfig.Signin_Set_Required_Info_Activity)
public class SetRequiredInfoActivity extends JYBaseActivity {

    private static final int REQUEST_CODE = 100;

    private CustomizeItemLayout item_diqu;
    private CustomizeItemLayout item_xueduan_xueke;
    private Button btn_next;

    @Override
    public int bindLayout() {
        return R.layout.signin_activity_set_required_info;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getJyDefaultToolbar();
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        item_diqu = contentView.findViewById(R.id.item_diqu);
        item_xueduan_xueke = contentView.findViewById(R.id.item_xueduan_xueke);
        btn_next = contentView.findViewById(R.id.btn_next);
        item_diqu.setMinorHint("请选择");
        item_xueduan_xueke.setMinorHint("请选择");
        item_diqu.setTitle("地区");
        item_xueduan_xueke.setTitle("学段/学科");
    }

    @Override
    public void initListener() {
        item_diqu.setOnClickListener(this);
        item_xueduan_xueke.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }

    @Override
    public void onWidgetClick(View view) {
        if (view.getId() == R.id.item_diqu) {
            showDialog();
        } else if (view.getId() == R.id.item_xueduan_xueke) {
            RouteSubjectTypeData data = new RouteSubjectTypeData();
            data.setFrom(RouteSubjectTypeData.FROM_USER_INFO);
            RouteUtils.startActivityForResult(this, RoutePathConfig.User_Info_Subject_Activity, REQUEST_CODE, data);
        } else if (view.getId() == R.id.btn_next) {
        }
    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected IYXBasePresenter initPresenterImpl() {
        return null;
    }


    private ArrayList<TreeNode> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<TreeNode>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<TreeNode>>> options3Items = new ArrayList<>();

    public void showDialog() {
        String json = FileUtils.getFromAssets("area.json");
        Gson gson = new Gson();
        TreeNode tree = gson.fromJson(json, TreeNode.class);

        TreeNode.setParentRecurse(tree);

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String id = options1Items.get(options1).uid + "\n" +
                        options2Items.get(options1).get(options2).uid + "\n" +
                        options3Items.get(options1).get(options2).get(options3).uid;

                String name = options1Items.get(options1).toString() + " " +
                        options2Items.get(options1).get(options2).toString() + " " +
                        options3Items.get(options1).get(options2).get(options3).toString();

                item_diqu.setMinorTitle(name);
                refreshBtnState();
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
        pvOptions.show(item_diqu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE && data != null) {
            LevelBean levelBean = (LevelBean) data.getSerializableExtra("level");
            List<SubjectBean> subjectBeans = (List<SubjectBean>) data.getSerializableExtra("subject");
            item_xueduan_xueke.setMinorTitle(levelBean.getName() + " " + subjectBeans.get(0).getName());
            refreshBtnState();
        }
    }

    private void refreshBtnState() {
        if (TextUtils.isEmpty(item_diqu.getMinorTitle()) || TextUtils.isEmpty(item_xueduan_xueke.getMinorTitle())) {
            btn_next.setEnabled(false);
        } else {
            btn_next.setEnabled(true);
        }
    }
}
