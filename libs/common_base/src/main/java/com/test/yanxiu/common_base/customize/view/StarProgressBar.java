package com.test.yanxiu.common_base.customize.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.test.yanxiu.common_base.R;
import com.yanxiu.lib.yx_basic_library.util.YXDigitUtil;

/**
 * 星星评分的进度条
 * Created by 戴延枫 on 2018/10/9.
 */

public class StarProgressBar extends View {
    private int[] mColors = {Color.parseColor("#42beff"), Color.parseColor("#dd66ff")};//进度条颜色（渐变色的2个点）
    private int backgroundColor = Color.GRAY;//进度条默认颜色

    private Paint mPaint;//画笔
    private float mProgressPercentage = 0;//进度百分比

    private Drawable starDrawable; //星星
    private int starCount; //星星数量
    private int starWidth;     //星星图片width，
    private int starHeight;     //星星图片高度
    private boolean mTouchAble;     //是否可点击评分

    public StarProgressBar(Context context) {
        this(context, null);
    }

    public StarProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // 获得我自定义的样式属性
    public StarProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
        setClickable(mTouchAble);
    }

    private void init(Context context, AttributeSet attrs) {
        // 获得我们所定义的自定义样式属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StarProgressBar);
        mColors[0] = typedArray.getColor(R.styleable.StarProgressBar_startColor, Color.parseColor("#42beff"));// 渐变色之起始颜色
        mColors[1] = typedArray.getColor(R.styleable.StarProgressBar_endColor, Color.parseColor("#dd66ff"));// 渐变色之结束颜色
        backgroundColor = typedArray.getColor(R.styleable.StarProgressBar_backgroundColor, Color.GRAY);// 背景色，默认设置为灰色
        starDrawable = typedArray.getDrawable(R.styleable.StarProgressBar_starIcon);
        starCount = typedArray.getInt(R.styleable.StarProgressBar_starsCount, 5);
        mTouchAble = typedArray.getBoolean(R.styleable.StarProgressBar_touchAble, false);
        typedArray.recycle();
        mPaint = new Paint();
        mProgressPercentage = 0;


    }

    public void setColors(int[] colors) {
        mColors = colors;
    }

    public void setProgressIntValue(int progressValue) {
        if (progressValue > 100) {
            progressValue = 100;
        } else if (progressValue < 0) {
            progressValue = 0;
        }
        Log.i("customView", "log: progressValue=" + progressValue);
        mProgressPercentage = ((float) progressValue) / 100;
        if (mProgressPercentage > 1)
            mProgressPercentage = 1;
    }

    private void setProgressFloatValue(float progress) {
        if (progress > 1) {
            progress = 1;
        } else if (progress < 0) {
            progress = 0;
        }
        mProgressPercentage = progress;
        if (this.onStarChangeListener != null) {
            String result = YXDigitUtil.floatKeep1Decimal(mProgressPercentage * 100 / (100 / starCount));
            this.onStarChangeListener.onStarChange(Float.parseFloat(result));  //调用监听接口
        }
        invalidate();
    }

    /**
     * 是否能触摸评分
     */
    public void setBarTouchAble(boolean touchAble) {
        mTouchAble = touchAble;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!mTouchAble)
            return super.onTouchEvent(event);
        float x = event.getX();
        Log.e("dyf", "x = " + x);
        Log.e("dyf", "getMeasuredWidth = " + getMeasuredWidth());
        if (x < 0) {
            x = 0;
        }
        if (x > getMeasuredWidth()) {
            x = getMeasuredWidth();
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                setProgressFloatValue(x / getMeasuredWidth());
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                setProgressFloatValue(x / getMeasuredWidth());
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 设置宽度
        if (starDrawable != null) {
            Bitmap tempBitMap = drawableToBitmap(starDrawable);
            starWidth = tempBitMap.getWidth();
            starHeight = tempBitMap.getHeight();
            tempBitMap.recycle();
        }

        int width = starWidth * starCount + getPaddingLeft() + getPaddingRight();
        int height = starHeight + getPaddingTop() + getPaddingBottom();
        Log.i("dyf", "log: w=" + width + " h=" + height);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float lineWidth = getHeight();//线粗
        float progressWidth = getWidth();//绘画区域的长度

        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(lineWidth * 2);
        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mPaint.setColor(Color.TRANSPARENT);


        int count = mColors.length;
        int[] colors = new int[count];
        System.arraycopy(mColors, 0, colors, 0, count);

        //底部灰色背景，指示进度条总长度
        mPaint.setShader(null);
        mPaint.setColor(backgroundColor);
        canvas.drawLine(0, 0, progressWidth, 0, mPaint);

        //设置渐变色区域
        LinearGradient shader = new LinearGradient(0, 0, mProgressPercentage * progressWidth, 0, colors, null, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);

        //画出渐变色进度条
        canvas.drawLine(0, 0, mProgressPercentage * progressWidth, 0, mPaint);

        //画星星图片
        if (starDrawable != null) {
            for (int i = 0; i < starCount; i++) {
                starDrawable.setBounds(starWidth * i, 0, starWidth * i + starWidth, getHeight());
                starDrawable.draw(canvas);
            }
        }
    }

    private OnStarChangeListener onStarChangeListener;//监听星星变化接口

    /**
     * 定义星星点击的监听接口
     */
    public interface OnStarChangeListener {
        void onStarChange(float mark);
    }

    /**
     * 设置监听
     *
     * @param onStarChangeListener
     */
    public void setOnStarChangeListener(OnStarChangeListener onStarChangeListener) {
        this.onStarChangeListener = onStarChangeListener;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        //canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }


}
