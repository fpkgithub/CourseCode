package com.test.com.courseapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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

public class TeacherActivity extends AppCompatActivity
{

    private Spinner xqSp;
    private Spinner tchSp;

    private List<String> xqList = new ArrayList<String>();
    private List<String> tchList = new ArrayList<String>();

    private ArrayAdapter<String> xqAdapter;
    private ArrayAdapter<String> tchAdapter;

    GetCourse getCourse = new GetCourse();
    Handler handler;

    Bitmap imgBitmap;

    private ImageView img;
    private EditText edt_check;


    public Map<String, String> mapGrade = new TreeMap<String, String>(new Comparator<String>()
    {
        public int compare(String o1, String o2)
        {
            return o2.compareTo(o1);
        }
    });
    Map<String, String> mapTeacher = new HashMap<String, String>();

    //教师姓名
    public String teacherName;

    // 创建数据库

    CourseDao courseDao;
    SemesterDao semesterDao;
    TeacherDao teacherDao;
    DBHelper dbHelper;
    SQLiteDatabase db;

    SharedPreferences sp;

    EditText tch_name;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_main);

        img = (ImageView) findViewById(R.id.img_check);
        edt_check = (EditText) findViewById(R.id.edt_id);
        tch_name = (EditText) findViewById(R.id.tch_name);


        courseDao = new CourseDao(TeacherActivity.this);
        semesterDao = new SemesterDao(TeacherActivity.this);
        teacherDao = new TeacherDao(TeacherActivity.this);

        Toast.makeText(TeacherActivity.this, "正在加载学期和教师...", Toast.LENGTH_SHORT).show();

        //图片
        new Thread()
        {
            @Override
            public void run()
            {
                imgBitmap = getCourse.getImage();
                handler.sendEmptyMessage(2);
            }
        }.start();


        //获取学期和教室
        TermTeacherTask task = new TermTeacherTask();
        task.execute();

        handler = new Handler()
        {
            @Override
            public void handleMessage(android.os.Message msg)
            {

                if (msg.what == 1)
                {
                    //绑定学期
                    if (mapGrade.size() != 0)
                    {
                        //添加学期数据
                        for (String str1 : mapGrade.keySet())
                        {
                            xqList.add(mapGrade.get(str1));
                        }

                        //绑定学期列表
                        xqSp = (Spinner) findViewById(R.id.spinner_term);
                        xqAdapter = new ArrayAdapter<String>(TeacherActivity.this, android.R.layout.simple_spinner_item, xqList);
                        xqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        xqSp.setAdapter(xqAdapter);
                        //选择学期点击事件
                        xqSp.setOnItemSelectedListener(new MySpinnerOnClick1());
                    }

                    //绑定教师
                    if (mapTeacher.size() != 0)
                    {
                        for (String str2 : mapTeacher.keySet())
                        {
                            tchList.add(mapTeacher.get(str2));
                        }
                        //教师列表
                        tchSp = (Spinner) findViewById(R.id.spinner_tch);
                        tchAdapter = new ArrayAdapter<String>(TeacherActivity.this, android.R.layout.simple_spinner_item, tchList);
                        tchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        tchSp.setAdapter(tchAdapter);

                        //选择学期点击事件
                        tchSp.setOnItemSelectedListener(new MySpinnerOnClick2());

                    }
                }

                if (msg.what == 2)
                {
                    img.setImageBitmap(imgBitmap);
                }

            }

        };


    }

    //监听spinner 获取学期ID
    class MySpinnerOnClick1 implements Spinner.OnItemSelectedListener
    {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
        {
            String str = xqSp.getSelectedItem().toString();
            if (!getTermId().equals(""))
            {
                Toast.makeText(TeacherActivity.this, getTermId(), Toast.LENGTH_SHORT).show();

            } else
            {
                Toast.makeText(TeacherActivity.this, "请选择学期", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView)
        {
            Toast.makeText(TeacherActivity.this, "null", Toast.LENGTH_SHORT).show();
        }
    }


    ////监听spinner 获取教师ID
    class MySpinnerOnClick2 implements Spinner.OnItemSelectedListener
    {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
        {

            String str = tchSp.getSelectedItem().toString();
            if (!getTeacherId().equals(""))
            {
                Toast.makeText(TeacherActivity.this, getTeacherId(), Toast.LENGTH_SHORT).show();
            } else
            {
                Toast.makeText(TeacherActivity.this, "请选择教师", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView)
        {
            Toast.makeText(TeacherActivity.this, "null", Toast.LENGTH_SHORT).show();
        }
    }

    //检索
    public void search(View view)
    {
        Intent intent = new Intent();
        intent.setClass(TeacherActivity.this, TableActivity.class);

        if (getTeacherId().equals(""))
        {
            Toast.makeText(TeacherActivity.this, "请选择教师", Toast.LENGTH_SHORT).show();
            return;
        }

        String code = edt_check.getText().toString();
        if (code.equals(""))
        {
            Toast.makeText(TeacherActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        System.out.println("termId---" + getTermId());
        System.out.println("tid---" + getTeacherId());

        //传sid和tid
        Bundle bundle = new Bundle();
        bundle.putString("termId", getTermId());
        bundle.putString("tid", getTeacherId());
        bundle.putString("code", code);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    //获取学期的ID
    public String getTermId()
    {
        String termId = "";
        String selectTerm = xqSp.getSelectedItem().toString();
        for (Map.Entry entry : mapGrade.entrySet())
        {
            if (selectTerm.equals(entry.getValue()))
            {
                termId = entry.getKey().toString();
            }
        }
        return termId;
    }

    //获取教师的ID
    public String getTeacherId()
    {
        String tid = "";
        teacherName = tchSp.getSelectedItem().toString();

        for (Map.Entry entry : mapTeacher.entrySet())
        {
            if (teacherName.equals(entry.getValue()))
            {
                tid = entry.getKey().toString();
            }
        }
        return tid;
    }

    //搜索老师
    public void searchTea(View view)
    {
        //获取文本框内容进行模糊查询
        String strTName = tch_name.getText().toString();
        //int temp = teacherDao.queryTeacher(strTName);
        //System.out.println("temp:"+temp);

        List<Teacher> listTeacher = teacherDao.queryTeacher2(strTName);

        if (listTeacher != null)
        {
            //清空tchList数据
            tchList.clear();
            for (int i = 0; i < listTeacher.size(); i++)
            {
                tchList.add(listTeacher.get(i).getTname());
            }
            //教室列表
            tchSp = (Spinner) findViewById(R.id.spinner_tch);
            tchAdapter = new ArrayAdapter<String>(TeacherActivity.this, android.R.layout.simple_spinner_item, tchList);
            tchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            tchSp.setAdapter(tchAdapter);
            //选择学期点击事件
            tchSp.setOnItemSelectedListener(new MySpinnerOnClick2());

        } else
        {
            Toast.makeText(TeacherActivity.this, "没有这个教师", Toast.LENGTH_SHORT).show();
        }
    }


    //返回
    public void close(View view)
    {
        courseDao.closeDataBase();
        finish();
    }


    //获取学期老师列表
    class TermTeacherTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... arg0)
        {

            //获取学期和教师
            List<Map<String, String>> list = getCourse.getCourseInfo(semesterDao, teacherDao);

            if (!list.isEmpty())
            {
                //学期
                mapGrade = list.get(0);

                mapTeacher = list.get(1);

            }

            handler.sendEmptyMessage(1);

            return null;
        }

    }


}
