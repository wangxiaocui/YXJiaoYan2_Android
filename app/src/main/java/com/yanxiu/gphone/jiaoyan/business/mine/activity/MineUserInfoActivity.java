package com.yanxiu.gphone.jiaoyan.business.mine.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.utils.FileUtils;
import com.yanxiu.gphone.jiaoyan.business.mine.basic_candidate.TreeNode;
import com.yanxiu.gphone.jiaoyan.module.signin.R;
import com.yanxiu.gphone.jiaoyan.module.signin.customize.CustomizeItemLayout;
import com.yanxiu.lib.yx_basic_library.base.basemvp.IYXBasePresenter;
import com.yanxiu.lib.yx_basic_library.util.logger.YXLogger;

import java.util.ArrayList;

/**
 * Created by Hu Chao on 18/10/22.
 */
public class MineUserInfoActivity extends JYBaseActivity {

    private CustomizeItemLayout item_diqu;
    private CustomizeItemLayout item_xueke_xueduan;
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
        btn_next = contentView.findViewById(R.id.btn_next);
        item_diqu.setMinorHint("请选择");
        item_xueke_xueduan.setMinorHint("请选择");
        item_diqu.setTitle("地区");
        item_xueke_xueduan.setTitle("学段/学科");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onWidgetClick(View view) {

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
    public void test() {
        String json = FileUtils.getFromAssets("area.json");
        Gson gson = new Gson();
        TreeNode tree = gson.fromJson(json, TreeNode.class);

        TreeNode.setParentRecurse(tree);
        int j = 0;

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
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
        pvOptions.show(item_diqu);
    }
}
