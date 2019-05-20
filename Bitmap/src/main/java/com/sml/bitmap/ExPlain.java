package com.sml.bitmap;

/**
 * Created by sml on 2019/5/20.
 */

public class ExPlain {
    /**
     * 官方API文档：http://www.android-doc.com/reference/android/graphics/Bitmap.html
     * Bitmap就是指一张图片，一般是png和jpg格式的
     * Bitmap中有一个enum类型的Config，其中有6个值，分别为：
     *  1.ALPHA_8
     *      8位位图，每个像素占用1个字节，只有透明度，没有颜色值。只单纯做透明度的处理。
     *  2.RGB_565
     *      16位位图，每个像素占用2个字节。r=5,g=6,b=5, 一个像素点 = 5+6+5 = 16。用于不透明的图片。
     *  3.ARGB_4444
     *      16位位图，每个像素占用2个字节。a=4,r=4,g=4,b=4, 一个像素点 = 4+4+4+4 = 16
     *  4.ARGB_8888
     *      32位位图，每个像素占用4个字节。a=8,r=8,g=8,b=8, 一个像素点 = 8+8+8+8 = 32 被推荐使用的一种格式，综合透明苏及色值两个通道。
     *  5.RGBA_F16
     *      Android 8.0 新增（更丰富的色彩表现HDR）
     *      每个像素占用8个字节，“F”代表的就是以半浮点数存储。Google的注释中这个属性非常适用于广色域宽屏和HDR(高动态范围的图片)，所以它占用的内存最高，显示也是最好的。
     *  6.HARDWARE
     *      Android 8.0 新增 （Bitmap直接存储在graphic memory）
     *
     * Glide加载图片默认格式为RGB_565，Picasso加载图片默认格式为ARGB_8888。默认情况下，Glide加载图片会比Picassso占用内存大，当然清晰度会比Picasso高。
     */
}
