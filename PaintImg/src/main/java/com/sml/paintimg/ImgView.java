package com.sml.paintimg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sml on 2019/4/24.
 */

public class ImgView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ImgView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.parseColor("#FF4081"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        canvas.drawCircle(width / 2, 150, Math.min(width, 300) / 2, paint);

        /**-------------------------------------------------------------------------------------------设置过滤器（3种）-----------------------------------------------------------*/
//        canvas.translate(0,350);
//        PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.OVERLAY); //一个指定单一颜色和特定模式的过滤器
//        LightingColorFilter filter = new LightingColorFilter(Color.RED,Color.BLUE); //一个可以模仿简单光照的色彩过滤器
//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                1.3F,0,0,0,0,
//                0,1.5F,0,0,0,
//                0,0,1.6F,0,0,
//                0,0,0,1.9F,0});
//        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix); //一个通过 4 * 5 色彩矩阵计算进行变换颜色的过滤器
//        paint.setColorFilter(filter);
//        canvas.drawCircle(width/2,150,Math.min(width,300)/2,paint);

        /**-------------------------------------------------------------------------------------------色彩矩阵ColorMartix---------------------------------------------------------*/
        //1.Android中图片处理最常用的数据结构是Bitmap，包含整个图片所有的数据。整个图片由点阵和颜色值组成。
        //  1.1点阵：一个包含像素的矩阵，每一个元素对应着图片的像素
        //  1.2颜色值：ARGB，分别对应透明度、红、黄、蓝这四个通道分量。四个共同决定着每一个像素点显示的颜色
        //2.在色彩处理中，一般用色调。颜色饱和度、亮度来描述一个图像
        //  2.1色调：物体传播的颜色
        //  2.2饱和度：颜色的纯度，从0%（灰）到100%饱和
        //  2.3亮度，颜色相对的明暗程度
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureView(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    private void measureView(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(720, 1280);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(720, height);
        } else if (height == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width, 1280);
        }
    }
}
