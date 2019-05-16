package com.sml.porterduffxfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sml on 2019/5/13.
 */

public class CircleView extends View{

    Paint paint = null;

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mmeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        //绘制上层图片
        canvas.drawCircle(getWidth() / 2,getHeight() / 2,Math.min(getWidth(),getHeight()) / 2,paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //绘制底层图片
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.img);
        canvas.drawBitmap(bitmap,0,0,paint);
        paint.setXfermode(null);
    }

    public void mmeasure(int widthMeasureSpec, int heightMeasureSpec){
       int widthMode = MeasureSpec.getMode(widthMeasureSpec);
       int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;
        if(widthMode == MeasureSpec.AT_MOST){
            width = 720;
        }else{
            width = widthSize;
        }

        if(heightMode == MeasureSpec.AT_MOST){
            height = 450;
        }else{
            height = heightSize;
        }
        setMeasuredDimension(width,height);
    }
}
