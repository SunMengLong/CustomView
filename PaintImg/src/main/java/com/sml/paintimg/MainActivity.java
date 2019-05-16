package com.sml.paintimg;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener {

    private ImageView iv;
    private SeekBar sb_rotate;
    private SeekBar sb_saturation;
    private SeekBar sb_scale;
    private Bitmap bitmap;

    private float hue;
    private float saturation;
    private float lum;
    private final float MID_VALUE = 100.0F;
    private Bitmap b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initView();
    }

    private void initView() {
//        iv = findViewById(R.id.img);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.timg);

        //通过矩阵改变图片颜色
        Bitmap b = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        Canvas canvas = new Canvas(b);
        Paint paint = new Paint();
        colorMatrix.set(new float[]{1.0f,0.5f,0.5f,0.5f,0.0f,
                                    0.0f,1.0f,0.0f,0.0f,0.0f,
                                    0.0f,0.0f,1.0f,0.0f,0.0f,
                                    10.0f,0.9f,0.9f,1.0f,0.9f});
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap,0,0,paint);
        iv.setImageBitmap(b);



//        sb_rotate = (SeekBar) findViewById(R.id.seekbar_sediao);
//        sb_saturation = (SeekBar) findViewById(R.id.seekbar_baohedu);
//        sb_scale = (SeekBar) findViewById(R.id.seekbar_liangdu);
//
//        sb_scale.setOnSeekBarChangeListener(this);
//        sb_saturation.setOnSeekBarChangeListener(this);
//        sb_rotate.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
//            case R.id.seekbar_sediao:
//                hue = (progress - MID_VALUE) * 1.0f / MID_VALUE * 180;
//                break;
//            case R.id.seekbar_baohedu:
//                saturation = progress * 1.0f / MID_VALUE;
//                break;
//            case R.id.seekbar_liangdu:
//                lum = progress * 1.0F / MID_VALUE;
//                break;
        }
        iv.setImageBitmap(handleImageEffect(bitmap, hue, saturation, lum));
    }

    private Bitmap handleImageEffect(Bitmap bitmap, float hue, float saturation, float lum) {
        if (b == null) {
            b = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(b);
        Paint paint = new Paint();
        //色调
        ColorMatrix rotateMatrix = new ColorMatrix();
        rotateMatrix.setRotate(0, hue);
        rotateMatrix.setRotate(1, hue);
        rotateMatrix.setRotate(2, hue);
        //饱和度
        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);
        //亮度
        ColorMatrix scaleMatrix = new ColorMatrix();
        scaleMatrix.setScale(lum, lum, lum, 1);


        ColorMatrix imgMatrix = new ColorMatrix();
//        imgMatrix.postConcat(rotateMatrix);
//        imgMatrix.postConcat(saturationMatrix);
        imgMatrix.postConcat(scaleMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imgMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return b;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
