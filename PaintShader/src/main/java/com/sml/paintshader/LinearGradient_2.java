package com.sml.paintshader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sml on 2019/5/16.
 * 线性渐变两种构建方法 - 其二
 */

public class LinearGradient_2 extends View{

    private Paint paint;

    public LinearGradient_2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /**
         * 参数：
         * x0: 开始绘制的x轴坐标
         * y0: 开始绘制的y轴坐标
         * x1: 结束绘制的x轴坐标
         * y1: 结束绘制的y轴坐标
         * colors: 渐变的颜色数组
         * positions: 开始渐变的位置，与x0、y0有关
         * tilemode: 瓷砖模式
         */
        int[] colors = new int[]{Color.BLUE,Color.RED,Color.YELLOW};
        float[] positions = new float[]{0.0f,0.5f,1.0f};
        LinearGradient linearGradient = new LinearGradient(0,0,600,600, colors,positions, Shader.TileMode.REPEAT);
        paint.setShader(linearGradient);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(60,0,getWidth()-60,Math.min(getWidth(),getHeight()),paint);
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
