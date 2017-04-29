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

public class ClassroomActivity extends AppCompatActivity {

    private Spinner xqSp;
    private Spinner tchbudSp;
    private Spinner schSp;
    private Spinner clsrSp;


    private List<String> xqList = new ArrayList<String>();
    private List<String> tchbudList = new ArrayList<String>();
    private List<String> schList = new ArrayList<String>();
    private List<String> clsrList = new ArrayList<String>();


    private ArrayAdapter<String> xqAdapter;
    private ArrayAdapter<String> tchbudAdapter;
    private ArrayAdapter<String> schAdapter;
    private ArrayAdapter<String> clsrAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clsr);

        xqList.add("2011");
        xqList.add("2012");
        xqList.add("2013");
        xqList.add("2014");
        xqList.add("2015");

        schList.add("北校区");
        schList.add("南校区");


        tchbudList.add("北大楼");
        tchbudList.add("中一楼");
        tchbudList.add("中二楼");
        tchbudList.add("主一楼");
        tchbudList.add("主二楼");


        clsrList.add("B101");
        clsrList.add("B104");
        clsrList.add("B105");
        clsrList.add("B106");
        clsrList.add("B102");


        xqSp = (Spinner) findViewById(R.id.spinner_term);
        xqAdapter = new ArrayAdapter<String>(ClassroomActivity.this, android.R.layout.simple_spinner_item, xqList);
        xqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        xqSp.setAdapter(xqAdapter);
        //教室列表
        schSp = (Spinner) findViewById(R.id.spinner_sch);
        schAdapter = new ArrayAdapter<String>(ClassroomActivity.this, android.R.layout.simple_spinner_item, schList);
        schAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schSp.setAdapter(schAdapter);

        tchbudSp = (Spinner) findViewById(R.id.spinner_tchbud);
        tchbudAdapter = new ArrayAdapter<String>(ClassroomActivity.this, android.R.layout.simple_spinner_item, tchbudList);
        tchbudAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tchbudSp.setAdapter(tchbudAdapter);

        clsrSp = (Spinner) findViewById(R.id.spinner_clsr);
        clsrAdapter = new ArrayAdapter<String>(ClassroomActivity.this, android.R.layout.simple_spinner_item,clsrList);
        clsrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clsrSp.setAdapter(clsrAdapter);

    }

    public void close(View view)
    {
        finish();
    }
}
