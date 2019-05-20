package com.sml.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = findViewById(R.id.img1);
        /**
         * 通过BitmapFactory从系统文件、资源、输入流、字节数组中加载到一个Bitmap对象。
         */

        /**
         * BitmapFactory共有6个重载方法，且都是静态的：
         * decodeByteArray()
         * decodeFile()
         * decodeResource()
         * decodeStream()
         * decodeFileDescriptor()
         * decodeResourceStream()
         */

        /**
         * Bitmap高效加载
         * 利用BitmapFactory.options加载图片实际所需要的尺寸
         * BitmapFactory.options是一个静态内部类，且这个类只有一个方法：requestCancelDecode(),剩下的全部是一些常量值
         * BitmapFactory.options缩放图片主要采用inSample采样率
         * inSample = 1 采样后的宽高是原始宽高
         * inSample > 1 例如2，宽高均为原图的宽高的1/2
         * inSample的值最小为1，小于一这个值是无效的。
         * 获取采样率的流程 以获取资源文件为例：
         */
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true; //BitmapFactory只会解析图片的原始信息，并不会加载图片
//        BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, options);
//        Log.i("sss", "onCreate: ........" + options.outWidth + "...." + options.outHeight);//获取图片宽高
//        options.inJustDecodeBounds = false;
//        Bitmap simplingAftetBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, options);
//        img1.setImageBitmap(simplingAftetBitmap);

//        img1.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                int ivWidth = img1.getWidth();
//                int ivHeight = img1.getHeight();
//                img1.setImageBitmap(imgComPress(ivWidth,ivHeight));
//            }
//        }, 1000);

        img1.setImageBitmap(quality());
    }

    /**
     * 图片尺寸压缩实例
     */
    private Bitmap imgComPress(int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; //只查看图片信息，不加载图片
        BitmapFactory.decodeResource(getResources(), R.drawable.img, options);
        options.inPreferredConfig = Bitmap.Config.RGB_565; //通过Options，把图片的格式设置为RGB_565，内存占用会少一般较ARGB_8888
        options.inSampleSize = countInSample(options, width, height);
        options.inJustDecodeBounds = false;
        Bitmap comPressAfter = BitmapFactory.decodeResource(getResources(), R.drawable.img, options);
        return comPressAfter;
    }

    /**
     * 计算inSample
     */
    private int countInSample(BitmapFactory.Options options, int width, int height) {
        int rawWidth = options.outWidth; //图片的原始宽
        int rawHeight = options.outHeight; //图片的原始高
        int inSample = 1;
        if (rawWidth > width || rawHeight > height) { //如果图片的宽高大于view的宽高才压缩
            int halfWidth = rawWidth / 2; //避免过渡压缩 例如图片宽高为250 250 view宽高为200 200
            int halfHeight = rawHeight / 2;
            while ((halfWidth / inSample) >= width && (halfHeight / inSample) >= height) {
                inSample = inSample * 2;
            }
        }
        return inSample;
    }

    /**
     * 图片质量压缩实例
     */
    public Bitmap quality() {
        Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024 * 8);
        srcBitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565; //mipmap文件夹中的图片，不能通过这种方式改变Config，设置了也不生效
        return BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size(), options);
    }

    /**
     * 将一个Bitmap的像素copy到另一个Bitmap
     */
    private Bitmap copy() {
        Bitmap oneBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        Bitmap twoBitmap = oneBitmap.copy(Bitmap.Config.RGB_565, true);//配置信息、是否支持可改变可写入
        return twoBitmap;
    }
}
