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
    private RelativeLayout rela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        rela = findViewById(R.id.rela);
        tv.postDelayed(new Runnable() {
            @SuppressLint("NewApi")
            @Override
            public void run() {
                Log.i("sss", "onCreate: .........."+tv.getTranslationX());
                Log.i("sss", "onCreate: .........."+tv.getTranslationY());

                Log.i("sss", "onCreate: .........."+tv.getX());
                Log.i("sss", "onCreate: .........."+tv.getY());
                Log.i("sss", "onCreate: .........."+tv.getTop());
                Log.i("sss", "onCreate: .........."+tv.getBottom());
                Log.i("sss", "onCreate: .........."+tv.getLeft());
                Log.i("sss", "onCreate: .........."+tv.getRight());
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
                        Log.i("sss", "onCreate: .........."+tv.getTranslationX());
                        Log.i("sss", "onCreate: .........."+tv.getTranslationY());

                        Log.i("sss", "onCreate: .........."+tv.getX());
                        Log.i("sss", "onCreate: .........."+tv.getY());

                        Log.i("sss", "onCreate: .........."+tv.getTop());
                        Log.i("sss", "onCreate: .........."+tv.getBottom());
                        Log.i("sss", "onCreate: .........."+tv.getLeft());
                        Log.i("sss", "onCreate: .........."+tv.getRight());
                    }
                }, 1500);
            }
        });
    }
}
