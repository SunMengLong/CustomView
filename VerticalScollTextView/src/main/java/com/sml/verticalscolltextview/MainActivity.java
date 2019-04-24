package com.sml.verticalscolltextview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {


    List<String> list = new ArrayList<>();
    private TextView verticalScollTv;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add("第一条：“湖北省肠病医学临床研究中心黄石基地”、“黄石市消化病研究所”两个科研机构。编制床位506张，年门诊量约30万人次、年出院病人近2万人次。多次获得“湖北省文明单位”、“湖北省卫生系统先进集体”等荣誉。同时，医院还承担着精准扶贫的重任。新的时期，“五医”人正以执着的信念、高尚的医德、精湛的医术和严谨的学风，为打造“技术先进、专科特色、管理规范、服务优良、员工发展、社会满意”的医院愿景而不懈努力。");
        list.add("第二条：黄石市第五医院是一所集医疗、教学、科研于一体，学科设置齐全。");
        verticalScollTv = findViewById(R.id.vertical_scoll_tv);
        scrollView = findViewById(R.id.scrollView);
        VerticalScrollView.getInstence().startScroll(scrollView,verticalScollTv,list);
    }
}
