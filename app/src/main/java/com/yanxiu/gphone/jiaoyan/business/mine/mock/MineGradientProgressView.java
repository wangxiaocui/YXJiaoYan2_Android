package com.yanxiu.gphone.jiaoyan.business.mine.mock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.yanxiu.gphone.jiaoyan.R;

/**
 * Created By cailei on 2018/10/19
 */
public class MineGradientProgressView extends View {
    private Context mContext;
    private float mProgress;
    public MineGradientProgressView(Context context) {
        super(context);
        mContext = context;
        setBackground(getResources().getDrawable(R.color.color_transparent));
    }

    public MineGradientProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setBackground(getResources().getDrawable(R.color.color_transparent));
    }

    public void setProgress(float progress) {
        mProgress = progress;
        invalidate();
    }

    public void doSomething() {
        mProgress += 0.1f;
        postDelayed(new Runnable() {
            @Override
            public void run() {
                doSomething();
            }
        }, 3000);
        invalidate();
        if (mProgress > 1) { mProgress = 0; }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getMeasuredWidth();
        int h = getMeasuredHeight();

        // 画渐变
        Path path1 = new Path();
        RectF rect1 = new RectF(0, 0, w, h);
        path1.addRoundRect(rect1, h * 0.5f, h * 0.5f, Path.Direction.CW);

        int beginColor = mContext.getResources().getColor(R.color.color_27d3ff);
        int endColor = mContext.getResources().getColor(R.color.color_cc66ff);
        LinearGradient gradient = new LinearGradient(0, 0, w, 0, beginColor, endColor, Shader.TileMode.MIRROR);
        Paint paint1 = new Paint();
        paint1.setShader(gradient);
        canvas.drawPath(path1, paint1);

        Paint paint2 = new Paint();
        paint2.setColor(Color.GRAY);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);

        float pW = w * mProgress;

        Path p = new Path();
        RectF rect = new RectF(-w + pW, 0, pW, h);
        RectF rect2 = new RectF(0, 0, w, h);
        p.addRoundRect(rect, h * 0.5f, h * 0.5f, Path.Direction.CW);
        p.addRoundRect(rect2, h * 0.5f, h * 0.5f, Path.Direction.CW);
        p.setFillType(Path.FillType.EVEN_ODD);
        canvas.clipPath(path1);
        canvas.drawPath(p, paint2);
    }
}
