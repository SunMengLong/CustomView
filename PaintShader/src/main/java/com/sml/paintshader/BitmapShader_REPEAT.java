package com.sml.paintshader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sml on 2019/5/16.
 * 瓷砖模式 - 重复模式
 */

public class BitmapShader_REPEAT extends View{

    private Paint paint;

    public BitmapShader_REPEAT(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, android.graphics.Shader.TileMode.REPEAT);
        paint.setShader(bitmapShader);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        setLayerType(View.LAYER_TYPE_SOFTWARE,null); //去掉硬件加速

        canvas.drawRect(0,0,getWidth(),getHeight(),paint);
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
