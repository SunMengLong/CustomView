package com.sml.verticalscolltextview;

import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sml on 2019/4/23.
 */

public class VerticalScrollView {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    scrollFlag++;
                    if (scrollFlag != max) {
                        //没有滚动到底部，继续滚动
                        scrollView.post(new Runnable() {
                            @Override
                            public void run() {
                                scrollView.scrollTo(0, scrollFlag);
                            }
                        });
                        sendEmptyMessageDelayed(0, speed);
                    } else {
                        //滚动到底部，切换数据
                        sendEmptyMessageDelayed(1, stopTime);
                    }
                    break;
                case 1:
                    //数据切换到最后一条，从第一条开始展示
                    dataPosition++;
                    if (dataPosition == datas.size()) {
                        dataPosition = 0;
                    }
                    //每次切换数据后，从0开始滚动
                    scrollFlag = 0;
                    //设置新数据
                    textView.setText(datas.get(dataPosition));
                    textView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //归位
                            scrollView.scrollTo(0, 0);
                            //判断需不需要滚动
                            max = textView.getHeight() - scrollView.getHeight();
                            if (max > 0) {
                                handler.sendEmptyMessageDelayed(0, stopTime);
                            } else {
                                handler.sendEmptyMessageDelayed(1, stopTime);
                            }
                        }
                    }, 500);
                    break;
                default:
                    break;
            }
        }
    };

    //开始前、结束后停留的时间
    private int stopTime = 5000;
    //滚动的速度 数值越大速度越慢
    private int speed = 40;
    //滚动游标
    private int scrollFlag = 0;
    //最大滚动值
    private int max = 0;
    //展示数据的下标
    private int dataPosition = 0;
    private ScrollView scrollView;
    private TextView textView;
    private List<String> datas;
    private volatile static VerticalScrollView instance;

    public static VerticalScrollView getInstence() {
        if (instance == null) {
            synchronized (VerticalScrollView.class) {
                if (instance == null) {
                    instance = new VerticalScrollView();
                }
            }
        }
        return instance;
    }

    public void startScroll(final ScrollView scrollView, final TextView textView, List<String> datas) {
        this.scrollView = scrollView;
        this.textView = textView;
        this.datas = datas;
        //设置第一条数据
        textView.setText(datas.get(0));
        //获取scrollView高和textView高
        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                max = textView.getHeight() - scrollView.getHeight();
                if (max > 0) {
                    handler.sendEmptyMessageDelayed(0, stopTime);
                } else {
                    handler.sendEmptyMessageDelayed(1, stopTime);
                }
            }
        }, 500);
        //禁止手动滑动
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //禁止滑动
                return true;
                //可以滑动
//                return false;
            }
        });
    }
}
