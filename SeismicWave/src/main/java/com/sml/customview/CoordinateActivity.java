package com.sml.customview;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 坐标
 * window - width:720
 * window - height:1184 + 虚拟键96
 */

public class CoordinateActivity extends Activity {

    private TextView tv;
    private String TAG = "CustomViewTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        tv.postDelayed(new Runnable() {
            @SuppressLint("NewApi")
            @Override
            public void run() {
                Log.i(TAG, "动画前: "+tv.getTranslationX());
                Log.i(TAG, "动画前: "+tv.getTranslationY());
                Log.i(TAG, "动画前: "+tv.getX());
                Log.i(TAG, "动画前: "+tv.getY());
                Log.i(TAG, "动画前: "+tv.getTop());
                Log.i(TAG, "动画前: "+tv.getBottom());
                Log.i(TAG, "动画前: "+tv.getLeft());
                Log.i(TAG, "动画前: "+tv.getRight());
            }
        }, 1000);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(tv,"translationX",100f).setDuration(1000).start();
                ObjectAnimator.ofFloat(tv,"translationY",100f).setDuration(1000).start();
                tv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "动画后: "+tv.getTranslationX());
                        Log.i(TAG, "动画后: "+tv.getTranslationY());
                        Log.i(TAG, "动画后: "+tv.getX());
                        Log.i(TAG, "动画后: "+tv.getY());
                        Log.i(TAG, "动画后: "+tv.getTop());
                        Log.i(TAG, "动画后: "+tv.getBottom());
                        Log.i(TAG, "动画后: "+tv.getLeft());
                        Log.i(TAG, "动画后: "+tv.getRight());
                    }
                }, 1000);
            }
        });
    }
}
