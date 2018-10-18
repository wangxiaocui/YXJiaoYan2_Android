package com.yanxiu.gphone.jiaoyan.customize;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanxiu.gphone.jiaoyan.R;
import com.yanxiu.gphone.jiaoyan.util.KeyboardUtils;

public class CustomizeSearchView extends FrameLayout {

    private EditText mEtSearch;
    private ImageView mIvClear;
    private TextView mTvClose;

    //是否获得焦点
    private boolean hasFocus;

    private OnTextChangeListener mOnTextChangeListener;
    private OnFocusChangeListener mOnFocusChangeListener;

    public CustomizeSearchView(Context context) {
        this(context, null);
    }

    public CustomizeSearchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomizeSearchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.customize_search_view, this);
        mEtSearch = this.findViewById(R.id.et_search);
        mIvClear = this.findViewById(R.id.iv_clear);
        mTvClose = this.findViewById(R.id.tv_cancel);
        initListeners();
    }

    private void initListeners() {
        mIvClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtSearch.setText("");
            }
        });

        mEtSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasFocus) {
                    requestEditTextFocus();
                }
            }
        });

        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && hasFocus) {
                    mIvClear.setVisibility(View.VISIBLE);
                } else {
                    mIvClear.setVisibility(View.GONE);
                }
                if (mOnTextChangeListener != null) {
                    mOnTextChangeListener.onTextChange(s.toString());
                }
            }
        });

    }

    public String getText() {
        return mEtSearch.getText().toString();
    }

    public void setText(String text) {
        mEtSearch.setText(text);
    }

    /**
     * 设置焦点状态，当获取焦点时弹出键盘，清除焦点时收起键盘
     */
    public void setFocusState(final boolean hasFocus) {
        if (hasFocus) {
            KeyboardUtils.showSoftInput(mEtSearch);
            requestEditTextFocus();
        } else {
            KeyboardUtils.hideSoftInput(mEtSearch);
            clearEditTextFocus();
        }
    }

    /**
     * edittext清除焦点
     */
    public void clearEditTextFocus() {
        hasFocus = false;
        mEtSearch.clearFocus();
        mEtSearch.setCursorVisible(false);
        mIvClear.setVisibility(View.GONE);
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener.onFocusChange(false);
        }
    }

    /**
     * edittext获取焦点
     */
    public void requestEditTextFocus() {
        hasFocus = true;
        mEtSearch.requestFocus();
        mEtSearch.setCursorVisible(true);
        if (!TextUtils.isEmpty(getText())) {
            mIvClear.setVisibility(View.VISIBLE);
            mEtSearch.setSelection(getText().length());
        }
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener.onFocusChange(true);
        }
    }

    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        this.mOnFocusChangeListener = onFocusChangeListener;
    }

    public void setOnTextChangeListener(OnTextChangeListener onTextChangeListener) {
        this.mOnTextChangeListener = onTextChangeListener;
    }

    public void setOnCancelClickListener(OnClickListener onClickListener) {
        mTvClose.setOnClickListener(onClickListener);
    }

    public void setOnEditorActionListener(TextView.OnEditorActionListener onEditorActionListener) {
        mEtSearch.setOnEditorActionListener(onEditorActionListener);
    }

    public interface OnFocusChangeListener {
        void onFocusChange(boolean hasFocus);
    }

    public interface OnTextChangeListener {
        void onTextChange(String text);
    }

}
