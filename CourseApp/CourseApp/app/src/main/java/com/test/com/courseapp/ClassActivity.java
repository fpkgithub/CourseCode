package com.test.com.courseapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */

public class ClassActivity extends AppCompatActivity {

    private Spinner xqSp;
    private Spinner clsSp;

    private List<String> xqList = new ArrayList<String>();
    private List<String> clsList = new ArrayList<String>();

    private ArrayAdapter<String> xqAdapter;
    private ArrayAdapter<String> clsAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cls);

        xqList.add("2011");
        xqList.add("2012");
        xqList.add("2013");
        xqList.add("2014");
        xqList.add("2015");

        clsList.add("数学");
        clsList.add("语文");
        clsList.add("英语");
        clsList.add("历史");
        clsList.add("地理");


        xqSp = (Spinner) findViewById(R.id.spinner_term);
        xqAdapter = new ArrayAdapter<String>(ClassActivity.this, android.R.layout.simple_spinner_item, xqList);
        xqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        xqSp.setAdapter(xqAdapter);
        //教室列表
        clsSp = (Spinner) findViewById(R.id.spinner_tch);
        clsAdapter = new ArrayAdapter<String>(ClassActivity.this, android.R.layout.simple_spinner_item, clsList);
        clsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clsSp.setAdapter(clsAdapter);
    }

    public void close(View view)
    {
        finish();
    }
}
