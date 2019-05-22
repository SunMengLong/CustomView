package com.sml.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sml on 2019/5/21.
 */

public class PathView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path path;

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5f);
        paint.setStyle(Paint.Style.STROKE); //设置风格为空心
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
         * 绘制等腰三角形
         * moveTo 绘制路径的第一个坐标点
         * lineTo 路径经过的坐标点
         * close 路径绘制关闭，将第一个点和最后一个点连接起来
         * lineTo、rLineTo起始点默认是屏幕左上角的坐标系原点（0,0）！
         */
//        path = new Path();
//        path.moveTo(360,20);
//        path.lineTo(200,220);
//        path.lineTo(520,220);
//        path.close();//关闭
//        canvas.drawPath(path,paint);

        /**
         * quadTo 可以来绘制二阶贝塞尔曲线，也就是3个点确定一个曲线
         * 由3个点来确定：（100,100），（200,200），（900,100）
         */
//        Path path = new Path();
//        path.moveTo(100f,100f);
//        float[] floats = new float[]{200f,200f,900f,100f};
//        path.quadTo(200f,200f,900f,100f);
//        canvas.drawPath(path,paint);
//        canvas.drawPoints(floats,paint);

        /**
         * cubicTo 和quadTo的区别就是多了一个点
         * 可以来绘制二阶贝塞尔曲线，也就是4个点确定一个曲线
         * 由3个点来确定：（100,100），（200,200），（400,100），（700,220）
         */
//        Path path = new Path();
//        path.moveTo(100f,100f);
//        path.cubicTo(200f,200f,400f,100f,700f,200f);
//        canvas.drawPath(path,paint);

        /**
         * arcTo 方法
         * 在规定区域内绘制一个圆弧，并判断圆弧的起始点和路径的起始点是否为同一点，不同则连接
         */
//        Path path = new Path();
//        path.moveTo(100f,100f);
//        RectF rect = new RectF(200, 200, 400, 400);
//        path.arcTo(rect,0,90);
//        canvas.drawPath(path,paint);

        /**
         * rLineTo方法
         * r是Relative的缩写，相对的意思。rLineTo绘制时以path最后一个点为坐标原点（0,0），这里是（100，100）；
         * lineTo、rLineTo起始点默认是屏幕左上角的坐标系原点（0,0）！
         */
//        Path path = new Path();
//        path.moveTo(100f, 100f);
//        path.rLineTo(300, 200);
//        canvas.drawPath(path, paint);
//        canvas.drawPoint(300, 200, paint);

        /**
         * addArc方法
         * 与arcTo方法的区别是：不会把圆弧的起始点和路径的起始点连接在一起
         */
//        Path path = new Path();
//        path.moveTo(100,100);
//        path.lineTo(200,200);
//        RectF rect = new RectF(300,300,500,400);
//        path.addArc(rect,0,90);
//        canvas.drawPath(path,paint);

        /**
         * 在绘制路径上绘制文字
         */
//        Path path = new Path();
//        path.moveTo(100f,100f);
//        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        textPaint.setTextSize(30f);
//        RectF rectF = new RectF(300,300,490,490);
//        path.addOval(rectF, Path.Direction.CW);
////        path.addOval(rectF, Path.Direction.CCW);//改变闭合方向，文字会内侧且逆时针绘制
//        canvas.drawPath(path,paint);
//        char[] chaars = new char[]{'a','b','c','d','e'};
//        canvas.drawTextOnPath(chaars,0,5,path,0f,5,textPaint);

        /**
         * drawTextRun方法
         * isRtl 为true ： 文字倒着绘制
         */
//        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        textPaint.setTextSize(60f);
//        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e'};
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            canvas.drawTextRun(chars, 0, chars.length, 0, chars.length, 100f, 100f, true, textPaint);
//        }

        /**
         * clipRect裁剪规则区域
         */
//        Rect rect = new Rect(0,0,300,300);
//        canvas.drawColor(Color.BLUE); //绘制背景色
//        canvas.rotate(30); //画布旋转
//        canvas.clipRect(rect); //剪切画布
//        canvas.drawColor(Color.RED); //剪切后的画布背景色
//        canvas.drawRect(100,100,400,400,new Paint(Paint.ANTI_ALIAS_FLAG)); //看一下剪切的画布是否生效

        /**
         * op方法
         */
//        Rect rect1 = new Rect(100,100,300,300);
//        Rect rect2 = new Rect(200,200,400,400);
//        canvas.drawColor(Color.BLUE);//背景色  蓝色
//        canvas.save(); //保存画布
//        canvas.clipRect(rect1); //绘制第一块区域
//        //Region.Op.DIFFERENCE 取第一次剪切的非交集部分
//        //Region.Op.INTERSECT 取第一次剪切的交集部分
//        //Region.Op.REPLACE 第二次代替第一次剪切
//        //Region.Op.UNION 两次剪切的和
//        //Region.Op.REVERSE_DIFFERENCE 第二次剪切的非交集部分
//        //Region.Op.XOR 两次绘制的非交集部分
//        canvas.clipRect(rect2, Region.Op.REVERSE_DIFFERENCE); //绘制第二块区域
//        //截取后有效区域绘制红色
//        canvas.drawColor(Color.RED);
//        canvas.restore();
//        //绘制辅助区域
//        canvas.drawRect(100,100,300,300,paint);
//        canvas.drawRect(200,200,400,400,paint);

        /**
         * clipPath 裁剪不规则画布
         */
//        Path path = new Path();
//        path.addCircle(100,100,100, Path.Direction.CCW); //裁剪不规则图形
//        canvas.drawColor(Color.BLUE);
//        canvas.clipPath(path);
//        canvas.drawColor(Color.RED);
//        //绘制辅助圆
//        canvas.drawCircle(100,100,100,paint);

        /**
         * save 和 restore方法
         * 关于保存图层，还有一个更加强大的saveLayer()，这个方法就等用到时，再进行学习
         */
//        Rect path1 = new Rect(100,100,300,300);
//        Rect path2 = new Rect(150,150,200,200);
//        canvas.translate(500,200); //画布平移
//        canvas.drawColor(Color.BLUE);
//        canvas.clipRect(path1);
//        canvas.drawColor(Color.RED);
//        canvas.save(); //保存当前层
//        canvas.rotate(10); //旋转
//        canvas.clipRect(path2);
//        canvas.drawColor(Color.GREEN);
//        canvas.restore(); //把图层恢复到最后一次save()前的状态

        /**
         * scale缩放
         */
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img2);
////        canvas.scale(0.2f,0.2f); //缩放的值为 0 - 1f 1代表不缩放 默认的缩放中心是（0,0）
//        canvas.scale(0.2f, 0.2f, 360, 0); //指定缩放中心 （360,0）
//        canvas.drawBitmap(bitmap, 0, 0, paint);

        /**
         * 错切
         */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img2);
        canvas.skew(0.3f,0);
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    private void mMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(720, 500);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(720, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, 500);
        }
    }
}
