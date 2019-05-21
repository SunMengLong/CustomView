package com.sml.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sml on 2019/5/21.
 */

public class PotionView extends View{

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public PotionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10f); //要设置宽度，不设置不会显示
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 画多个点
         * 两个点为一组，表示一个点的坐标位置；
         * 源码中注解：@Size(multiple = 2) 长度须是2的倍数，如果数据中有5个值，生效的只有前4个
         */
//        canvas.drawPoints(new float[]{100f,100f,300f,200f},paint);

        /**
         * 画一个点
         */
//        canvas.drawPoint(100f,100f,paint);

        /**
         * 参数1：要绘制的点的坐标数据
         * 参数2：要跳过的数量，跳过的数值时无效的
         * 参数3：数组的有效数据量
         */
        canvas.drawPoints(new float[]{100f,100f,300f,200f},2,2,paint);
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
