package com.sml.paintshader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sml on 2019/5/16.
 * 光束渐变两种构建方法 - 其一
 */

public class RadialGradient_2 extends View{

    private Paint paint;

    public RadialGradient_2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int[] colors = new int[]{Color.BLACK,Color.RED ,Color.BLACK};
        final float[] positions = new float[]{0f, 0.5f ,1f};
        RadialGradient radialGradient = new RadialGradient(getWidth()/2,getHeight()/2,Math.min(getWidth(),getHeight())/2,colors,positions, Shader.TileMode.CLAMP);
        paint.setShader(radialGradient);
        canvas.drawRect(0,0,getWidth(),Math.min(getWidth(),getHeight()),paint);
    }

    private void mMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(720,500);
        }else if(widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(720,heightSize);
        }else if(heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,500);
        }
    }
}
