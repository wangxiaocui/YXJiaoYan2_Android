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
public class MyView extends View {
    public MyView(Context context) {
        super(context);
        setBackground(getResources().getDrawable(R.color.color_red));
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackground(getResources().getDrawable(R.color.color_red));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getMeasuredWidth();
        int h = getMeasuredHeight();

        LinearGradient gradient = new LinearGradient(0, 0, w, 0, Color.GREEN, Color.BLUE, Shader.TileMode.MIRROR);
        Paint paint = new Paint();
        paint.setShader(gradient);
        Path p = new Path();
        RectF rect = new RectF(0,0,w, h);
        p.addRoundRect(rect, h * 0.5f, h * 0.5f, Path.Direction.CW);
        canvas.drawPath(p, paint);

        
        canvas.clipPath(p, Region.Op.DIFFERENCE);
        canvas.drawRect(0 , 0, w, h, paint);

    }


}
