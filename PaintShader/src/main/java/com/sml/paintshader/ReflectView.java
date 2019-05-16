package com.sml.paintshader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sml on 2019/5/16.
 * 图片倒影
 */

public class ReflectView extends View {
    private Paint mPaint;
    private Bitmap dstBitmap, srcBitmap;
    private PorterDuffXfermode xfermode;
    private int x, y;

    public ReflectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //原图Bitmap
        dstBitmap = decodeBitmapFormRes(getResources(), R.drawable.timg,540, 960);
        //垂直翻转
        Matrix matrix = new Matrix();
        matrix.setScale(1f, -1f);
        //图片向右倾斜
        matrix.preSkew(-0.5f, 0);
        //倒影Bitmap
        srcBitmap = Bitmap.createBitmap(dstBitmap, 0, 0, dstBitmap.getWidth(), dstBitmap.getHeight(), matrix, true);
        //初始化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        //屏幕宽度
        int screenW = getResources().getDisplayMetrics().widthPixels;
        //起始点
        x = screenW / 2 - dstBitmap.getWidth() / 2;
        y = 0;
        //设置渐变矩形
        mPaint.setShader(new LinearGradient(x, dstBitmap.getHeight(), x, dstBitmap.getHeight() + dstBitmap.getHeight() / 2, 0xDD000000, Color.TRANSPARENT, Shader.TileMode.CLAMP));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        //绘制背景
        canvas.drawColor(Color.BLACK);
        //绘制原图
        canvas.drawBitmap(dstBitmap, x, y, null);
        //绘制倒影图片
        canvas.drawBitmap(srcBitmap, x, dstBitmap.getHeight(), null);
        mPaint.setXfermode(xfermode);
        //绘制渐变层
        canvas.drawRect(x, dstBitmap.getHeight(), x + dstBitmap.getWidth()+x , dstBitmap.getHeight() * 2, mPaint);
        mPaint.setXfermode(null);
    }

    /**
     * 测量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (wSpecMode == MeasureSpec.AT_MOST && hSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, 900);
        } else if (wSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, hSpecSize);
        } else if (hSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(wSpecSize, 900);
        }
    }

    /**
     * 图片的缩放
     */
    private Bitmap decodeBitmapFormRes(Resources resources, int resId, int targetWidth, int targetHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = false;
        BitmapFactory.decodeResource(resources, resId, options);
        int inSample = calculateInSample(options, targetWidth, targetHeight);
        options.inSampleSize = inSample;
        return BitmapFactory.decodeResource(resources, resId, options);
    }

    private int calculateInSample(BitmapFactory.Options options, int targetWidth, int targetHeight) {
        if (targetWidth <= 0 || targetHeight <= 0) {
            return 1;
        }
        int inSample = 1;
        final int rawWidth = options.outWidth;
        final int rawHeight = options.outHeight;
        if (rawWidth > targetWidth || rawHeight > targetHeight) {
            final int halfWidth = rawWidth / 2;
            final int halfHeight = rawHeight / 2;
            while ((halfWidth / inSample >= targetWidth) && (halfHeight / inSample >= targetHeight)) {
                inSample *= 2;
            }
        }
        return inSample;
    }
}

