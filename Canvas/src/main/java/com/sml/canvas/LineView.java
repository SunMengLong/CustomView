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

public class LineView extends View{

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(3f);
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
         * 1.画一条线
         * 参数是两点的坐标
         */
//        canvas.drawLine(0,0,500,500,paint);

        /**
         * 画多条线
         * 四个数为一组，分别代表两点坐标；
         * 源码中注解是这样的：@Size(multiple = 4) ； 不满足4个不生效
         */
//        canvas.drawLines(new float[]{0,0,500,500,10,0,900,200},paint);

        /**
         * 画多条线
         * 参数1：坐标数组
         * 参数2：要跳过的数组中数的个数，为4，则代表数组中前4位无效
         * 参数3：数组中有效数的数量 总共8个，跳过4个，count就为4
         * 参数4：画笔
         */
        canvas.drawLines(new float[]{0,0,500,500,10,0,900,200},4,4,paint);
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
