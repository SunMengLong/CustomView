package com.sml.canvas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /**
     * 虽然称Canvas之为画布，但是并不是真的直接在Canvas画，而是bitmap默认会创建一个bitmap，也可以通过构造方法或者setBitmap方法传入，像素所有的信息画在了这个bitmap上。
     * 在draw方法中，有一个很牛逼的方法:drawBitmapMesh，但是一般不会用到。简单处理图片我们一般会用到Metrix，但是遇到Metrix处理不了的，记得还可以研究一下drawBitmapMesh。
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
