package com.sml.paintcircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sml on 2019/4/23.
 */

public class CircleView extends View {

    private Paint paint = new Paint();

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureView(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        //-------------------------------------------------设置画笔样式（3种）------------------------------------------------------
//        paint.setStyle(Paint.Style.FILL); //实心
//        paint.setStyle(Paint.Style.FILL_AND_STROKE); //实心加描边
        paint.setStyle(Paint.Style.STROKE); //空心

        //-------------------------------------------------设置画笔属性------------------------------------------------------------
        paint.setFlags(Paint.ANTI_ALIAS_FLAG ); //抗锯齿
        paint.setFlags(Paint.DITHER_FLAG  ); //防抖动

        canvas.drawCircle(width/2,height/2,Math.min(width,height)/2,paint);
    }

    /**
     * 测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    private void measureView(int widthMeasureSpec, int heightMeasureSpec){
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(720,300);
        }else if(widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(720,height);
        }else if(height == MeasureSpec.AT_MOST){
            setMeasuredDimension(width,300);
        }
    }
}
