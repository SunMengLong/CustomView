package com.sml.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
         * 常量值：
         *      1.boolean inJustDecodeBounds 是否只扫描轮廓
         *      2.int inSample 采样率
         *      3.Bitmap.Config inPreferredConfig 格式、色彩模式
         *      4.int outWidth	bitmap的宽
         *      5.int outHeight	bitmap的高
         *      6.boolean inDither	防抖动，默认false
         *      7.int inDensity	像素密度
         *      8.boolean inScaled	是否可以缩放，默认true
         *      9.boolean inMutable	是否可变，设为ture，decode转换方法返回的结果全部可改变
         * 获取采样率的流程 以获取资源文件为例：
         */
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true; //BitmapFactory只会解析图片的原始信息，并不会加载图片
//        BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, options);
//        options.inJustDecodeBounds = false;
//        Bitmap simplingAftetBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, options);
//        img1.setImageBitmap(simplingAftetBitmap);

        /**
         * 尺寸压缩
         */
        img1.postDelayed(new Runnable() {
            @Override
            public void run() {
                int ivWidth = img1.getWidth();
                int ivHeight = img1.getHeight();
                img1.setImageBitmap(imgComPress(ivWidth,ivHeight));
            }
        }, 1000);

        /**
         * 质量压缩
         */
//        img1.setImageBitmap(quality());

        /**
         * createBitmap共有9个重载方法：
         */
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.img2);

        //第一:
//        Matrix matrix = new Matrix();
//        //bitmap:资源bitmap , x第一个像素的x轴坐标 ， y第一个像素的y轴坐标，宽，高，矩阵，是否过滤资源bitmap
//        //返回一个不可改变的bitmap
//        Bitmap srcBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);

        //第二：
//        Bitmap srcBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight());

        //第三：
//        Bitmap srcBitmap = Bitmap.createBitmap(bitmap);

        //第四、第五都调用了一个没有对外公开的private方法。返回值是一个可改变的Bitmap：
//        Bitmap srcBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.RGB_565);
//        //第五：
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            Bitmap.createBitmap(new DisplayMetrics(),bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.RGB_565);
//        }

        //第六：1.指定初始密度，2.初始化颜色的数组，数量大于width*height，3.offset偏移量，
        // 4.对象的跨距宽度，也称为扫描宽度，https://blog.csdn.net/lxw907304340/article/details/45396419
        //5.宽，6.高 7.格式
        //返回一个补个改变的Bitmap
//        Bitmap.createBitmap(new DisplayMetrics(), int colors[],int offset, int stride, int width, int height, Config config);

        //第七：
//        Bitmap srcBitmap = Bitmap.createBitmap(new int[]{10,20,30,40,50}, bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.RGB_565);

        //第八：
//        createBitmap(DisplayMetrics display, int colors[], int width, int height, Config config)

        //第九：
//        createBitmap(int colors[], int offset, int stride, int width, int height, Config config)

//        img1.setImageBitmap(srcBitmap);

//        srcBitmap.recycle();//释放Bitmap所占的内存
//        Log.i("ssss", "onCreate: ......."+srcBitmap.isRecycled()); //是否回收了内存
//        srcBitmap.getWidth()
//        srcBitmap.getHeight()
//        Log.i("ssss", "onCreate: ......."+srcBitmap.isMutable()); //是否可以改变
//        Log.i("ssss", "onCreate: ......."+srcBitmap.sameAs(bitmap)); //判断两个bitmap是否相同：大小、像素、格式信息
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
        Log.i("sss", "imgComPress: ............"+comPressAfter.getByteCount());
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
     * 质量压缩不会减少图片的像素，它是在保持像素的前提下改变图片的位深及透明度，来达到压缩图片的目的，图片的长，宽，像素都不会改变，那么bitmap所占内存大小是不会变的。
       我们可以看到有个参数：quality，可以调节你压缩的比例，但是还要注意一点就是，质量压缩对png格式这种图片没有作用，因为png是无损压缩。
     */
    public Bitmap quality() {
        Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img3);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024 * 8);
        srcBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565; //mipmap文件夹中的图片，不能通过这种方式改变Config，设置了也不生效
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size(), options);
        Log.i("sss", "quality: 与原图片信息是否相同："+bitmap.sameAs(srcBitmap));
        return bitmap;
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
