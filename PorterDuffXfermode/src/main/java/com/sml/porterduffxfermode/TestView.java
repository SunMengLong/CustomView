package com.sml.porterduffxfermode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sml on 2019/5/5.
 */

public class TestView extends View {

    Paint paint = new Paint();

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //设置背景色
        canvas.drawARGB(255, 139, 197, 186);
        int width = canvas.getWidth();
        int height = canvas.getHeight();

//        int layerId = canvas.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG); //产生的layer初始时为完全透明
        //绘制黄色的圆形
        paint.setColor(0xFFFFCC44);
        int r = width / 3;
        canvas.drawCircle(r,r,r,paint);

        //绘制蓝色的矩形
        paint.setColor(0xFF66AAFF);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));  //不支持硬件加速，坑
        canvas.drawRect(r, r, r * 2.7f, r * 2.7f, paint);
        paint.setXfermode(null);
//        canvas.restoreToCount(layerId); //将layer绘制到默认的layer上
    }
}
