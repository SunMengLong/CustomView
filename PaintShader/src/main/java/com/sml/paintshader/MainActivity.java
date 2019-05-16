package com.sml.paintshader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }


    private Bitmap createInvertedImage() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.timg);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();
        // 设置图片旋转
        matrix.preScale(1, -1);
        //设置图片的缩放比，长度缩小一半
        matrix.postScale(1, 0.5f);
        //图片向右倾斜
        matrix.preSkew(-0.5f, 0);
        //倒影图片
        Bitmap invertedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        //最后合成的图片

        Bitmap finalInvertedBitmap = Bitmap.createBitmap(width + invertedBitmap.getWidth(), height + invertedBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //构建画布，把倒影和原图先画在画布上
        Paint paint = new Paint();
        Canvas canvas = new Canvas(finalInvertedBitmap);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        //设置倒影的透明度
        paint.setAlpha(100);
        canvas.drawBitmap(invertedBitmap, 0, height, paint);
        //线性渐变

        LinearGradient linearGradient = new LinearGradient(0, height, 0, finalInvertedBitmap.getHeight(), 0x70ffffff, 0x70ffffff, Shader.TileMode.MIRROR);
        paint.setShader(linearGradient);
        //不懂啥意思
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, height, width, finalInvertedBitmap.getHeight(), paint);
        return finalInvertedBitmap;
    }
}
