package com.yanxiu.gphone.jiaoyan.business.search.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.yanxiu.common_base.base.ui.JYBaseActivity;
import com.test.yanxiu.common_base.db.SpManager;
import com.test.yanxiu.common_base.route.RoutePathConfig;
import com.test.yanxiu.common_base.route.RouteUtils;
import com.test.yanxiu.common_base.route.data.RouteSearchData;
import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.business.course.bean.CourseCategoryBean;
import com.yanxiu.gphone.jiaoyan.business.search.fragment.SearchResultFragment;
import com.yanxiu.gphone.jiaoyan.business.search.interfaces.SearchContract;
import com.yanxiu.gphone.jiaoyan.business.search.presenter.SearchPresenter;
import com.yanxiu.gphone.jiaoyan.customize.CustomizeSearchView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hu Chao on 18/10/16.
 */
@Route(path = RoutePathConfig.Search_Activity)
public class SearchActivity extends JYBaseActivity<SearchContract.IPresenter> implements SearchContract.IView {

    private CustomizeSearchView customize_search_view;
    private ScrollView scroll_view_search;
    private FrameLayout fl_search_result;
    private RelativeLayout rl_search_history;
    private ImageView iv_clear_history;
    private TagFlowLayout tag_flow_layout_history;
    private TagFlowLayout tag_flow_layout_hot;

    private List<String> mHistoryKey;

    private SearchResultFragment mSearchResultFragment;

    @Override
    public int bindLayout() {
        return R.layout.search_activity;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {
        mHistoryKey = SpManager.getSearchHistoryKey();
        if (mHistoryKey == null) {
            mHistoryKey = new ArrayList<>();
        }
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        customize_search_view = contentView.findViewById(R.id.customize_search_view);
        scroll_view_search = contentView.findViewById(R.id.scroll_view_search);
        fl_search_result = contentView.findViewById(R.id.fl_search_result);
        rl_search_history = contentView.findViewById(R.id.rl_search_history);
        iv_clear_history = contentView.findViewById(R.id.iv_clear_history);
        tag_flow_layout_history = contentView.findViewById(R.id.tag_flow_layout_history);
        tag_flow_layout_hot = contentView.findViewById(R.id.tag_flow_layout_hot);
    }

    @Override
    public void initListener() {
        iv_clear_history.setOnClickListener(this);

        customize_search_view.setOnCancelClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        customize_search_view.setOnTextChangeListener(new CustomizeSearchView.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                if (TextUtils.isEmpty(text)) {
                    scroll_view_search.setVisibility(View.VISIBLE);
                    if (mSearchResultFragment != null && !mSearchResultFragment.isHidden()) {
                        getSupportFragmentManager().beginTransaction().hide(mSearchResultFragment)
                                .commitAllowingStateLoss();
                    }
                }
            }
        });

        customize_search_view.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                search(customize_search_view.getText().trim());
                return true;
            }
        });

        tag_flow_layout_history.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                customize_search_view.setText(mHistoryKey.get(position));
                search(mHistoryKey.get(position));
                return true;
            }
        });

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.iv_clear_history:
                mHistoryKey.clear();
                refreshSearchHistory();
                break;
        }
    }

    @Override
    public void doBusiness() {
        refreshSearchHistory();
        customize_search_view.setFocusState(true);
        mPresenter.getSearchHot();
    }

    private void search(final String key) {
        if (!TextUtils.isEmpty(key)) {
            if (!mHistoryKey.contains(key)) {
                mHistoryKey.add(key);
            }
            customize_search_view.setFocusState(false);
            //延时100ms,等键盘收起后再收缩
            customize_search_view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showSearchResult(key);
                    refreshSearchHistory();
                }
            }, 100);
        }
    }

    private void refreshSearchHistory() {
        if (mHistoryKey.size() == 0) {
//            rl_search_history.setVisibility(View.GONE);
        } else {
            rl_search_history.setVisibility(View.VISIBLE);
            if (tag_flow_layout_history.getAdapter() == null) {
                tag_flow_layout_history.setAdapter(new TagAdapter<String>(mHistoryKey) {

                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(com.yanxiu.gphone.jiaoyan.module.signin.R.layout.custom_flow_item_layout,
                                parent, false);
                        tv.setText(s);
                        return tv;
                    }
                });
            } else {
                tag_flow_layout_history.getAdapter().notifyDataChanged();
            }
        }
    }

    public void showSearchResult(String key) {
        if (mSearchResultFragment == null) {
            RouteSearchData data = new RouteSearchData();
            data.setSearchKey(key);
            mSearchResultFragment = RouteUtils.getFramentByPath(RoutePathConfig.Search_Result_Fragment, data);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_search_result, mSearchResultFragment)
                    .commitAllowingStateLoss();
        } else {
            if (mSearchResultFragment.isHidden()) {
                getSupportFragmentManager().beginTransaction().show(mSearchResultFragment)
                        .commitAllowingStateLoss();
            }
            mSearchResultFragment.search(key);
        }
        scroll_view_search.setVisibility(View.GONE);
    }

    @Override
    protected SearchContract.IPresenter initPresenterImpl() {
        return new SearchPresenter(this);
    }

    @Override
    protected void onDestroy() {
        SpManager.setSearchHistoryKey(mHistoryKey);
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        SpManager.setSearchHistoryKey(mHistoryKey);
    }

    @Override
    public void onSuccess(List<CourseCategoryBean> categoryBeanList) {
    }


    @Override
    public void onFail(String errorMsg) {

    }

}
