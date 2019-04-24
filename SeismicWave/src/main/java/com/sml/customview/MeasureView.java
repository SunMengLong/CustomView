package com.sml.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sml on 2019/4/11.
 */

public class MeasureView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        paint.setColor(Color.BLUE);
    }

    /**
     * 测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec); //默认模式
        setMeasuredDimension(measureWidth(widthMeasureSpec), measuredHeight(heightMeasureSpec)); //指定测量模式
    }

    /**
     * 绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画矩形
        int left = getLeft();
        int right = getRight();
        int top = getTop();
        int bottom = getBottom();
        canvas.drawRect(left,top,right,bottom,paint);

//        //画圆
//        int radius = Math.min(getWidth(),getHeight());
//        paint.setColor(Color.RED);
//        canvas.drawCircle(radius/2,radius/2,radius/2,paint);//cx为圆点的x坐标，cy为圆点的y轴坐标，radius为圆的半径值

//        //画扇形
//        paint.setColor(Color.GREEN);
//        RectF rect = new RectF(0f,0f,400f,400f); //圆心坐标（(right-left)/2,(bottom-top)/2）
//        canvas.drawArc(rect,0,60,false,paint);//圆点位置，开始角度，扫过的角度，是否要焦点圆心，画笔。注意：结束角度 = sweepAngle + startAngle;

        //绘制Bitmap
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
//        int width = (getWidth() - bitmap.getWidth())/2;
//        int height = (getHeight() - bitmap.getHeight())/2;
//        canvas.drawBitmap(bitmap,width,height,paint);

//        //绘制文字
//        paint.setColor(Color.GREEN);
//        paint.setTextSize(30);
//        canvas.drawText("欢迎光临达利世纪酒店",100,100,paint);

        //绘制路径
//        paint.setColor(Color.GREEN);
//        Path p = new Path();
//        p.moveTo(100, 100);
//        p.lineTo(200, 50);
//        p.lineTo(300, 100);
//        p.lineTo(200,400);
//        canvas.drawPath(p,paint);

        //paint能够拿到：所要绘制的集合图形、文字或者Bitmap的颜色、风格等信息
        //画笔有3种构建方法:
        //1.public Paint() { this(0); } 创建一个默认属性的画笔
        //2.public Paint(int flags) {...} 创建一个带有标记的画笔，也可通过setFlags设置已创建画笔的标签
        //3.public Paint(Paint paint) {...} 通过一个已配置好的画笔来创建一个新的画笔
    }

    /**
     * 测量宽
     * @param widthMeasureSpec
     * @return
     */
    private int measureWidth(int widthMeasureSpec){
        int result;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if(mode == MeasureSpec.EXACTLY){
            result = size;
        }else{
            result = 200;
            if(mode == MeasureSpec.AT_MOST){
                result = Math.min(result,size);
            }
        }
        return result;
    }

    /**
     * 测量高
     * @param heightMeasureSpec
     * @return
     */
    private int measuredHeight(int heightMeasureSpec){
        int result;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if(mode == MeasureSpec.EXACTLY){
            result = size;
        }else{
            result = 200;
            if(mode == MeasureSpec.AT_MOST){
                result = Math.min(result,size);
            }
        }
        return result;
    }
}
