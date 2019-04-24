package com.sml.customview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * 测量
 * * View的三种测量模式
 * 1.精准模式 EXACTLY
 * 2.最大值模式 AT_MOST
 * 3.未指定模式 UNSPICIFIED
 * 验证：View类默认的onMeasure()方法只支持EXACTLY模式
 * 例如：在onMeasure方法中不指定测试模式且在布局中width和height都设置为wrap_parent，其测量值与match_parent相同
 */

public class MeasureActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_measure);
    }
}
