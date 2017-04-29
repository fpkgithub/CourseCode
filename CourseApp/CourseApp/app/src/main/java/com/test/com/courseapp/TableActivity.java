package com.test.com.courseapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课表
 * Created by Administrator on 2017/4/15.
 */

public class TableActivity extends AppCompatActivity
{
    Handler handler;
    GetCourse getCourse = new GetCourse();
    String cookie;
    String code;
    String termId;
    String tid;
    String teacherName;

    //标题信息
    TextView txt_infor;


    //课表信息
    Map<Integer, List> course = new HashMap<Integer, List>();

    //每个数组表示的是每天（从第一节到第五节课）的课程，每个数组的长度必须是5，没课的要设为“”
    String[] xingqi1 = new String[]{"无", "无", "无", "无", "无"};
    String[] xingqi2 = new String[]{"无", "无", "无", "无", "无"};
    String[] xingqi3 = new String[]{"无", "无", "无", "无", "无"};
    String[] xingqi4 = new String[]{"无", "无", "无", "无", "无"};
    String[] xingqi5 = new String[]{"无", "无", "无", "无", "无"};

    TextView xingqi1_jie1, xingqi1_jie2, xingqi1_jie3, xingqi1_jie4, xingqi1_jie5;
    TextView xingqi2_jie1, xingqi2_jie2, xingqi2_jie3, xingqi2_jie4, xingqi2_jie5;
    TextView xingqi3_jie1, xingqi3_jie2, xingqi3_jie3, xingqi3_jie4, xingqi3_jie5;
    TextView xingqi4_jie1, xingqi4_jie2, xingqi4_jie3, xingqi4_jie4, xingqi4_jie5;
    TextView xingqi5_jie1, xingqi5_jie2, xingqi5_jie3, xingqi5_jie4, xingqi5_jie5;


    //存数据库
    CourseDao courseDao;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_layout);

        //接受数据
        Bundle bundle = this.getIntent().getExtras();
        termId = bundle.getString("termId");
        tid = bundle.getString("tid");
        code = bundle.getString("code");

        //System.out.println(termId + " " + tid + " " + code);


        txt_infor = (TextView) findViewById(R.id.txt_infor);
        txt_infor.setText("没有此人信息...");

        Toast.makeText(TableActivity.this, "正在加载课表...", Toast.LENGTH_SHORT).show();


        xingqi1_jie1 = (TextView) findViewById(R.id.xingqi1_jie1);
        xingqi1_jie2 = (TextView) findViewById(R.id.xingqi1_jie2);
        xingqi1_jie3 = (TextView) findViewById(R.id.xingqi1_jie3);
        xingqi1_jie4 = (TextView) findViewById(R.id.xingqi1_jie4);
        xingqi1_jie5 = (TextView) findViewById(R.id.xingqi1_jie5);
        xingqi2_jie1 = (TextView) findViewById(R.id.xingqi2_jie1);
        xingqi2_jie2 = (TextView) findViewById(R.id.xingqi2_jie2);
        xingqi2_jie3 = (TextView) findViewById(R.id.xingqi2_jie3);
        xingqi2_jie4 = (TextView) findViewById(R.id.xingqi2_jie4);
        xingqi2_jie5 = (TextView) findViewById(R.id.xingqi2_jie5);
        xingqi3_jie1 = (TextView) findViewById(R.id.xingqi3_jie1);
        xingqi3_jie2 = (TextView) findViewById(R.id.xingqi3_jie2);
        xingqi3_jie3 = (TextView) findViewById(R.id.xingqi3_jie3);
        xingqi3_jie4 = (TextView) findViewById(R.id.xingqi3_jie4);
        xingqi3_jie5 = (TextView) findViewById(R.id.xingqi3_jie5);
        xingqi4_jie1 = (TextView) findViewById(R.id.xingqi4_jie1);
        xingqi4_jie2 = (TextView) findViewById(R.id.xingqi4_jie2);
        xingqi4_jie3 = (TextView) findViewById(R.id.xingqi4_jie3);
        xingqi4_jie4 = (TextView) findViewById(R.id.xingqi4_jie4);
        xingqi4_jie5 = (TextView) findViewById(R.id.xingqi4_jie5);
        xingqi5_jie1 = (TextView) findViewById(R.id.xingqi5_jie1);
        xingqi5_jie2 = (TextView) findViewById(R.id.xingqi5_jie2);
        xingqi5_jie3 = (TextView) findViewById(R.id.xingqi5_jie3);
        xingqi5_jie4 = (TextView) findViewById(R.id.xingqi5_jie4);
        xingqi5_jie5 = (TextView) findViewById(R.id.xingqi5_jie5);

        //开启数据库
        courseDao = new CourseDao(TableActivity.this);


        //在线程里面加载数据
        new Thread()
        {
            @Override
            public void run()
            {
                course = getCourse.getCourse(termId, tid, code, courseDao);

                if (course == null)
                {
                    handler.sendEmptyMessage(2);
                }
                else if(course.size()!=0)
                {
                    handler.sendEmptyMessage(1);
                }
                else
                {
                    handler.sendEmptyMessage(3);
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
                    int col = 0;//星期*
                    for (Map.Entry<Integer, List> l : course.entrySet())
                    {
                        int time = 0;//第几节课
                        //取第一条数据
                        if (col == 0)
                        {
                            //System.out.println("头部信息。。。");
                            for (Object o : l.getValue())
                            {
                                txt_infor.setText("课程信息:" + o.toString());
                            }
                        }
                        //去星期一到星期五的数据
                        else if (col == 1)
                        {
                            System.out.println("星期" + (col) + ":");
                            for (Object o : l.getValue())
                            {
                                System.out.println((time) + ":" + o.toString());
                                if (time == 5)
                                {
                                    break;
                                }
                                xingqi1[time] = o.toString() + "";

                                time++;
                            }
                            //填充星期一课程
                            if (xingqi1[0] != null && (!xingqi1[0].equals("")))
                            {//星期1第1节
                                xingqi1_jie1.setText(xingqi1[0]);
                                xingqi1_jie1.setBackgroundResource(R.drawable.bg_11);
                            } else
                            {
                                xingqi1_jie1.setText(xingqi1[0]);
                                xingqi1_jie1.setBackgroundColor(Color.TRANSPARENT);
                            }

                            if (xingqi1[1] != null && (!xingqi1[1].equals("")))
                            {//星期1第2节--星期2第1节
                                xingqi2_jie1.setText(xingqi1[1]);
                                xingqi2_jie1.setBackgroundResource(R.drawable.bg_12);
                            } else
                            {
                                xingqi2_jie1.setText(xingqi1[1]);
                                xingqi2_jie1.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi1[2] != null && (!xingqi1[2].equals("")))
                            {//星期1第3节
                                xingqi3_jie1.setText(xingqi1[2]);
                                xingqi3_jie1.setBackgroundResource(R.drawable.bg_13);
                            } else
                            {
                                xingqi3_jie1.setText(xingqi1[2]);
                                xingqi3_jie1.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi1[3] != null && (!xingqi1[3].equals("")))
                            {//星期1第4节
                                xingqi4_jie1.setText(xingqi1[3]);
                                xingqi4_jie1.setBackgroundResource(R.drawable.bg_14);
                            } else
                            {
                                xingqi4_jie1.setText(xingqi1[3]);
                                xingqi4_jie1.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi1[4] != null && (!xingqi1[4].equals("")))
                            {//星期1第5节
                                xingqi5_jie1.setText(xingqi1[4]);
                                xingqi5_jie1.setBackgroundResource(R.drawable.bg_15);
                            } else
                            {
                                xingqi5_jie1.setText(xingqi1[4]);
                                xingqi5_jie1.setBackgroundColor(Color.TRANSPARENT);
                            }
                        } else if (col == 2)
                        {
                            System.out.println("星期" + (col) + ":");
                            for (Object o : l.getValue())
                            {
                                System.out.println((time) + ":" + o.toString());
                                xingqi2[time++] = o.toString();
                                if (time == 5)
                                {
                                    break;
                                }
                            }
                            //填充星期二课程
                            if (xingqi2[0] != null && (!xingqi2[0].equals("")))
                            {//星期2第1节
                                xingqi1_jie2.setText(xingqi2[0]);
                                xingqi1_jie2.setBackgroundResource(R.drawable.bg_15);
                            } else
                            {
                                xingqi1_jie2.setText(xingqi2[0]);
                                xingqi1_jie2.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi2[1] != null && (!xingqi2[1].equals("")))
                            {//星期2第2节
                                xingqi2_jie2.setText(xingqi2[1]);
                                xingqi2_jie2.setBackgroundResource(R.drawable.bg_16);
                            } else
                            {
                                xingqi2_jie2.setText(xingqi2[1]);
                                xingqi2_jie2.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi2[2] != null && (!xingqi2[2].equals("")))
                            {//星期2第3节
                                xingqi3_jie2.setText(xingqi2[2]);
                                xingqi3_jie2.setBackgroundResource(R.drawable.bg_17);
                            } else
                            {
                                xingqi3_jie2.setText(xingqi2[2]);
                                xingqi3_jie2.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi2[3] != null && (!xingqi2[3].equals("")))
                            {//星期2第4节
                                xingqi4_jie2.setText(xingqi2[3]);
                                xingqi4_jie2.setBackgroundResource(R.drawable.bg_18);
                            } else
                            {
                                xingqi4_jie2.setText(xingqi2[3]);
                                xingqi4_jie2.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi2[4] != null && (!xingqi2[4].equals("")))
                            {//星期2第5节
                                xingqi5_jie2.setText(xingqi2[4]);
                                xingqi5_jie2.setBackgroundResource(R.drawable.bg_11);
                            } else
                            {
                                xingqi5_jie2.setText(xingqi2[4]);
                                xingqi5_jie2.setBackgroundColor(Color.TRANSPARENT);
                            }
                        } else if (col == 3)
                        {
                            System.out.println("星期" + (col) + ":");
                            for (Object o : l.getValue())
                            {
                                System.out.println((time) + ":" + o.toString());
                                xingqi3[time] = o.toString();
                                time++;
                                if (time == 5)
                                {
                                    break;
                                }

                            }
                            //填充星期三课程
                            if (xingqi3[0] != null && (!xingqi3[0].equals("")))
                            {//星期3第1节
                                xingqi1_jie3.setText(xingqi3[0]);
                                xingqi1_jie3.setBackgroundResource(R.drawable.bg_14);
                            } else
                            {
                                xingqi1_jie3.setText(xingqi3[0]);
                                xingqi1_jie3.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi3[1] != null && (!xingqi3[1].equals("")))
                            {//星期3第2节
                                xingqi2_jie3.setText(xingqi3[1]);
                                xingqi2_jie3.setBackgroundResource(R.drawable.bg_13);
                            } else
                            {
                                xingqi2_jie3.setText(xingqi3[1]);
                                xingqi2_jie3.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi3[2] != null && (!xingqi3[2].equals("")))
                            {//星期3第3节
                                xingqi3_jie3.setText(xingqi3[2]);
                                xingqi3_jie3.setBackgroundResource(R.drawable.bg_12);
                            } else
                            {
                                xingqi3_jie3.setText(xingqi3[2]);
                                xingqi3_jie3.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi3[3] != null && (!xingqi3[3].equals("")))
                            {//星期3第4节
                                xingqi4_jie3.setText(xingqi3[3]);
                                xingqi4_jie3.setBackgroundResource(R.drawable.bg_11);
                            } else
                            {
                                xingqi4_jie3.setText(xingqi3[3]);
                                xingqi4_jie3.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi3[4] != null && (!xingqi3[4].equals("")))
                            {//星期3第5节
                                xingqi5_jie3.setText(xingqi3[4]);
                                xingqi5_jie3.setBackgroundResource(R.drawable.bg_18);
                            } else
                            {
                                xingqi5_jie3.setText(xingqi3[4]);
                                xingqi5_jie3.setBackgroundColor(Color.TRANSPARENT);
                            }
                        } else if (col == 4)
                        {
                            System.out.println("星期" + (col) + ":");
                            for (Object o : l.getValue())
                            {
                                System.out.println((time) + ":" + o.toString());
                                xingqi4[time] = o.toString();
                                time++;
                                if (time == 5)
                                {
                                    break;
                                }

                            }
                            // //填充星期四课程
                            if (xingqi4[0] != null && (!xingqi4[0].equals("")))
                            {//星期4第1节
                                xingqi1_jie4.setText(xingqi4[0]);
                                xingqi1_jie4.setBackgroundResource(R.drawable.bg_18);
                            } else
                            {
                                xingqi1_jie4.setText(xingqi4[0]);
                                xingqi1_jie4.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi4[1] != null && (!xingqi4[1].equals("")))
                            {//星期4第2节
                                xingqi2_jie4.setText(xingqi4[1]);
                                xingqi2_jie4.setBackgroundResource(R.drawable.bg_17);
                            } else
                            {
                                xingqi2_jie4.setText(xingqi4[1]);
                                xingqi2_jie4.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi4[2] != null && (!xingqi4[2].equals("")))
                            {//星期4第3节
                                xingqi3_jie4.setText(xingqi4[2]);
                                xingqi3_jie4.setBackgroundResource(R.drawable.bg_16);
                            } else
                            {
                                xingqi3_jie4.setText(xingqi4[2]);
                                xingqi3_jie4.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi4[3] != null && (!xingqi4[3].equals("")))
                            {//星期4第4节
                                xingqi4_jie4.setText(xingqi4[3]);
                                xingqi4_jie4.setBackgroundResource(R.drawable.bg_15);
                            } else
                            {
                                xingqi4_jie4.setText(xingqi4[3]);
                                xingqi4_jie4.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi4[4] != null && (!xingqi4[4].equals("")))
                            {//星期4第5节
                                xingqi5_jie4.setText(xingqi4[4]);
                                xingqi5_jie4.setBackgroundResource(R.drawable.bg_14);
                            } else
                            {
                                xingqi5_jie4.setText(xingqi4[4]);
                                xingqi5_jie4.setBackgroundColor(Color.TRANSPARENT);
                            }
                        } else if (col == 5)
                        {
                            System.out.println("星期" + (col) + ":");
                            for (Object o : l.getValue())
                            {
                                System.out.println((time) + ":" + o.toString());
                                xingqi5[time++] = o.toString();
                                if (time == 5)
                                {
                                    break;
                                }

                            }
                            //填充星期五课程
                            if (xingqi5[0] != null && (!xingqi5[0].equals("")))
                            {//星期5第1节
                                xingqi1_jie5.setText(xingqi5[0]);
                                xingqi1_jie5.setBackgroundResource(R.drawable.bg_14);
                            } else
                            {
                                xingqi1_jie5.setText(xingqi5[0]);
                                xingqi1_jie5.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi5[1] != null && (!xingqi5[1].equals("")))
                            {//星期5第2节
                                xingqi2_jie5.setText(xingqi5[1]);
                                xingqi2_jie5.setBackgroundResource(R.drawable.bg_15);
                            } else
                            {
                                xingqi2_jie5.setText(xingqi5[1]);
                                xingqi2_jie5.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi5[2] != null && (!xingqi5[2].equals("")))
                            {//星期5第3节
                                xingqi3_jie5.setText(xingqi5[2]);
                                xingqi3_jie5.setBackgroundResource(R.drawable.bg_11);
                            } else
                            {
                                xingqi3_jie5.setText(xingqi5[2]);
                                xingqi3_jie5.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi5[3] != null && (!xingqi5[3].equals("")))
                            {//星期5第4节
                                xingqi4_jie5.setText(xingqi5[3]);
                                xingqi4_jie5.setBackgroundResource(R.drawable.bg_12);
                            } else
                            {
                                xingqi4_jie5.setText(xingqi5[3]);
                                xingqi4_jie5.setBackgroundColor(Color.TRANSPARENT);
                            }
                            if (xingqi5[4] != null && (!xingqi5[4].equals("")))
                            {//星期5第5节
                                xingqi5_jie5.setText(xingqi5[4]);
                                xingqi5_jie5.setBackgroundResource(R.drawable.bg_13);
                            } else
                            {
                                xingqi5_jie5.setText(xingqi5[4]);
                                xingqi5_jie5.setBackgroundColor(Color.TRANSPARENT);
                            }
                        } else
                        {
                            break;
                        }
                        col++;
                    }
                }
                if (msg.what == 2)
                {
                    Toast.makeText(TableActivity.this, "此教师没有课程/输入信息错误", Toast.LENGTH_SHORT).show();
                }
                if (msg.what == 3)
                {
                    Toast.makeText(TableActivity.this, "此教师没有课程/输入信息错误", Toast.LENGTH_SHORT).show();
                }
            }
        };

    }


    //返回
    public void close(View view)
    {
        finish();
    }
}
