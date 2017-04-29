package com.test.com.courseapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/4/16.
 */

public class CourseActivity extends AppCompatActivity
{

    private Spinner xqSp;
    private Spinner crsSp;

    private List<String> xqList = new ArrayList<String>();
    private List<String> crsList = new ArrayList<String>();

    private ArrayAdapter<String> xqAdapter;
    private ArrayAdapter<String> crsAdapter;

    GetAnyCourse getAnyCourse = new GetAnyCourse();
    Handler handler;

    Bitmap imgBitmap;

    private ImageView img;
    private EditText edt_id;

    Map<String, String> mapSemester = new HashMap<>();
    Map<String, String> mapCampus = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crs);

        img = (ImageView) findViewById(R.id.img_check);
        edt_id = (EditText) findViewById(R.id.edt_id);

        //图片
        new Thread()
        {
            @Override
            public void run()
            {
                imgBitmap = getAnyCourse.getImage();
                handler.sendEmptyMessage(1);
            }
        }.start();


        new Thread()
        {
            @Override
            public void run()
            {
                List<Map<String, String>> listResult = getAnyCourse.getInit();
                if (listResult.size() != 1 && listResult != null)
                {
                    mapSemester = listResult.get(0);
                    mapCampus = listResult.get(1);
                    handler.sendEmptyMessage(2);
                }
            }
        }.start();

        handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {

                if (msg.what == 1)
                {
                    //绑定验证码
                    img.setImageBitmap(imgBitmap);
                }


                if (msg.what == 2)
                {
                    //绑定学期
                    if (mapSemester.size() != 0)
                    {
                        //添加学期数据
                        for (String str1 : mapSemester.keySet())
                        {
                            xqList.add(mapSemester.get(str1));
                        }

                        xqSp = (Spinner) findViewById(R.id.spinner_term);
                        xqAdapter = new ArrayAdapter<String>(CourseActivity.this, android.R.layout.simple_spinner_item, xqList);
                        xqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        xqSp.setAdapter(xqAdapter);

                    }
                    if (mapCampus.size() != 0)
                    {
                        //添加校区数据
                        for (String str1 : mapCampus.keySet())
                        {
                            crsList.add(mapCampus.get(str1));
                        }

                        //校区列表
                        crsSp = (Spinner) findViewById(R.id.spinner_tch);
                        crsAdapter = new ArrayAdapter<String>(CourseActivity.this, android.R.layout.simple_spinner_item, crsList);
                        crsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        crsSp.setAdapter(crsAdapter);

                    }
                }

            }
        };

    }


    public void close(View view)
    {
        finish();
    }

    public void search(View view)
    {
        Intent intent = new Intent();
        intent.setClass(CourseActivity.this, AnyCourseActivity.class);

        String code = edt_id.getText().toString();
        if (code.equals(""))
        {
            Toast.makeText(CourseActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        System.out.println("SemeserId---" + getSemeserId());
        System.out.println("CampusId---" + getCampusId());

        //传sid和tid
        Bundle bundle = new Bundle();
        bundle.putString("semeserId", getSemeserId());
        bundle.putString("campusId", getCampusId());
        bundle.putString("code", code);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //获取学期的ID
    public String getSemeserId()
    {
        String termId = "";
        String selectTerm = xqSp.getSelectedItem().toString();
        for (Map.Entry entry : mapSemester.entrySet())
        {
            if (selectTerm.equals(entry.getValue()))
            {
                termId = entry.getKey().toString();
            }
        }
        return termId;
    }

    //获取校区的ID
    public String getCampusId()
    {
        String termId = "";
        String selectTerm = crsSp.getSelectedItem().toString();
        for (Map.Entry entry : mapCampus.entrySet())
        {
            if (selectTerm.equals(entry.getValue()))
            {
                termId = entry.getKey().toString();
            }
        }
        return termId;
    }

}
