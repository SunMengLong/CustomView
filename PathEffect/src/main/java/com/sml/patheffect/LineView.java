package com.sml.patheffect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sml on 2019/4/24.
 */

public class LineView extends View {

    private Paint textPaint;
    private Paint linePaint;
    private Path path;
    private PathEffect[] pathEffects;
    private String[] pathEffectName = {
            "默认", "CornerPathEffect", "DashPathEffect", "PathDashPathEffect",
            "SumPathEffect", "DiscretePathEffect", "ComposePathEffect"
    };

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        //字体画笔
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40f);

        //线画笔
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.RED);
        linePaint.setStrokeWidth(5f); //设置线宽
        linePaint.setStyle(Paint.Style.STROKE); //设置为空心

        //设置路径
        path = new Path();
        path.moveTo(0,0); //设置起点
        for (int i = 0; i < 37; i++) { //设置连接的点
            path.lineTo(i*30,(float) (Math.random() * 100));
        }

        //初始化路径效果 - 共有七种
        pathEffects = new PathEffect[7];
        pathEffects[0] = new PathEffect(); //1.默认效果
        pathEffects[1] = new CornerPathEffect(20f); //2.将各线段之间的夹角变成圆角，radius是圆角的半径
        pathEffects[2] = new DashPathEffect(new float[]{10f, 5f, 20f, 15f},10); //3.绘制虚线，intervals最少为2个，phase是偏移量 依次绘制为：实线10f + 虚线5f + 实线20f + 虚线10f

        Path p = new Path();
        p.addRect(0, 0, 30, 5, Path.Direction.CCW);
        pathEffects[3] = new PathDashPathEffect(p, 60, 0, PathDashPathEffect.Style.ROTATE); //4.和DashPathEffect差不多，只是能自定义图形来绘制Path。 shape是绘制的图形、adwance是图形之间的间距、phase是偏移量、样式
        pathEffects[4] = new SumPathEffect(pathEffects[1], pathEffects[2]); //5.将两种效果分别展示再重合
        pathEffects[5] = new DiscretePathEffect(5f, 10f);//6.切断线段，segmentLength是指切断线段的长度。deviation是偏移量，随机，小于等于deviation
        pathEffects[6] = new ComposePathEffect(pathEffects[3], pathEffects[5]);//将多个效果组合起来

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
        //绘制路径
        for (int i = 0; i < pathEffects.length; i++) {
            linePaint.setPathEffect(pathEffects[i]); //设置路径效果
            canvas.drawPath(path,linePaint);
            canvas.drawText(pathEffectName[i],0,140,textPaint);
            //每绘制一条将画布向下平移180像素
            canvas.translate(0,150);
        }
        invalidate(); //绘制刷新
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
