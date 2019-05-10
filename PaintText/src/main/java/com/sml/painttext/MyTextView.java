package com.sml.painttext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by sml on 2019/4/22.
 */

public class MyTextView extends View {

    Paint paint = new Paint();
    private String str = "好好学习，努力奋斗，abcdefg";

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.RED);
        paint.setTextSize(25);
        setBackgroundColor(Color.BLUE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureView(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //------------------------------------------------------1.设置文字居中--------------------------------------
        //拿到字符串的宽度 设置X轴居中
        float strWidth = paint.measureText(str);
        float textLeftMargin = (getWidth() - strWidth) / 2;

        //拿到字符串的高度
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float y = getHeight() / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2;

        //-----------------------------------------------------2.设置对齐方式----------------------------------------
        paint.setTextAlign(Paint.Align.LEFT);    //左对齐
//        paint.setTextAlign(Paint.Align.RIGHT); //右对齐
//        paint.setTextAlign(Paint.Align.CENTER);//居中对齐

        //-----------------------------------------------------3.设置字体-------------------------------------------
        //系统提供了五中字体
        //Typeface.BOLD 粗体
        //Typeface.NORMAL 默认
        //Typeface.ITALIC 斜体
        //Typeface.BOLD_ITALIC 粗斜体
//        Typeface typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC);
//        paint.setTypeface(typeface);

//        paint.setTextScaleX(1); //设置缩放
//        paint.setUnderlineText(true); //设置下划线
//        paint.setStrikeThruText(true); //设置文本删除线
//        Log.i("sss", "onDraw: .........."+paint.getFontSpacing());  //获取行间距
//        Log.i("sss", "onDraw: "+paint.descent());
//        Log.i("sss", "onDraw: "+paint.ascent());


        /**
         * text：要绘制的文字
         * x：右上角横坐标
         * y：右上角纵坐标
         */
        canvas.drawText(str, textLeftMargin, y, paint);
    }

    /**
     * 测量
     */
    private void measureView(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(720, 300);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(720, height);
        } else if (heightMeasureSpec == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width, 300);
        }
    }
}
