package com.sml.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sml on 2019/5/21.
 */

public class BitmapView extends View{

    Paint paint;

    public BitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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
        /**
         * drawBitmap()共有6个重载方法，但是其中两个参数最多的已经废弃掉了，那么就学习4个吧，哈哈
         */
        Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.img2);

        /**
         * 1.
         * 参数1：资源图片
         * 参数2：图片的左上角X轴坐标
         * 参数3：图片的左上角Y轴坐标
         * 参数4：画笔
         */
        canvas.drawBitmap(srcBitmap,0,0,paint);

        /**
         * 2.
         * 3.
         * 参数1：资源图片
         * 参数2：剪切图片，通过Rect指定裁剪区域
         * 参数3：图片在view中的显示位置 可以实现拉伸或缩放
         * 参数4：画笔
         * 这两种方法区别为参数3 ：Rect 与 RectF（浮点数类型）
         */
//        canvas.drawBitmap(srcBitmap,new Rect(0,0,720,500), new Rect(0,0,720,500),paint);
//        canvas.drawBitmap(srcBitmap,new Rect(0,0,720,500), new RectF(0,0,720,500),paint);

        /**
         * 4.
         * 参数1：原始图片
         * 参数2：是一个3*3的矩阵
         * 参数3：画笔
         */
//        Matrix matrix = new Matrix();
//        matrix.setTranslate(20f,20f);
//        canvas.drawBitmap(srcBitmap,matrix,paint);

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
