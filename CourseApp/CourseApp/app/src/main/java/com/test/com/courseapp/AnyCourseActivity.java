package com.test.com.courseapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/4/28.
 */

public class AnyCourseActivity extends AppCompatActivity
{

    //标题信息
    TextView txt_infor;
    String semeserId;
    String campusId;
    String code;

    GetAnyCourse getAnyCourse = new GetAnyCourse();
    //课表信息
    Map<Integer, List> course = new TreeMap<Integer, List>();

    Handler handler;

    //声明一个ListView对象
    ListView listView;
    //声明一个list，动态存储要显示的信息
    private List<Info> mlistInfo = new ArrayList<Info>();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_any);

        //接受数据
        Bundle bundle = this.getIntent().getExtras();
        semeserId = bundle.getString("semeserId");
        campusId = bundle.getString("campusId");
        code = bundle.getString("code");

        System.out.println("semeserId:" + semeserId + " campusId:" + campusId + " code " + code);

        Toast.makeText(AnyCourseActivity.this, "正在加载课表...", Toast.LENGTH_SHORT).show();

        //txt_infor = (TextView) findViewById(R.id.txt_infor);
        //txt_infor.setText("没有此人信息...");

        //将listView与布局对象关联
        listView = (ListView) this.findViewById(R.id.listView);

        //获取
        //在线程里面加载数据
        new Thread()
        {
            @Override
            public void run()
            {
                course = getAnyCourse.getCourse(semeserId, campusId, code);

                if (course != null && course.size() != 0)
                {
                    handler.sendEmptyMessage(1);
                } else
                {
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
                    //将map中的数据赋值到lsitview中
                    //给信息赋值函数，用来测试
                    //setInfo(course);
                    listView.setAdapter(new ListViewAdapter(course));

                }
                if (msg.what == 2)
                {
                    Toast.makeText(AnyCourseActivity.this, "此学区没有课表...", Toast.LENGTH_SHORT).show();
                }
            }
        };

    }

    public void close(View view)
    {
        finish();
    }


    public void setInfo(Map<Integer, List> course)
    {
        //mlistInfo.clear();
        int i = 0;
        while (i < course.size())
        {
            Info information = new Info();
            information.setId(1000 + i);
            information.setTitle("标题" + i);
            information.setDetails("详细信息" + i);
            information.setAvatar(R.drawable.logo7);

            //将新的info对象加入到信息列表中
            mlistInfo.add(information);
            i++;
        }
    }

    public class ListViewAdapter extends BaseAdapter
    {
        View[] itemViews;

        public ListViewAdapter(Map<Integer, List> course)
        {
            itemViews = new View[course.size()];
            for (int i = 0; i < course.size(); i++)
            {
                //获取第i个课程
                List<String> list = course.get(i);
                //调用makeItemView，实例化一个Item
                String cnum = (i+1)+"";
                itemViews[i] = makeItemView(cnum, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
            }
        }

        public int getCount()
        {
            return itemViews.length;
        }

        public View getItem(int position)
        {
            return itemViews[position];
        }

        public long getItemId(int position)
        {
            return position;
        }

        //绘制Item的函数
        private View makeItemView(String cnum, String ccourse, String csore, String teacher, String week, String time, String add)
        {

            LayoutInflater inflater = (LayoutInflater) AnyCourseActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // 使用View的对象itemView与R.layout.item关联
            View itemView = inflater.inflate(R.layout.layout_item, null);

            // 通过findViewById()方法实例R.layout.item内各组件
            TextView txt_num = (TextView) itemView.findViewById(R.id.txt_num);
            txt_num.setBackgroundResource(R.drawable.bg_11);
            txt_num.setText(cnum+"");

            TextView txt_course = (TextView) itemView.findViewById(R.id.txt_course);
            txt_course.setBackgroundResource(R.drawable.bg_12);
            txt_course.setText(ccourse);

            TextView txt_score = (TextView) itemView.findViewById(R.id.txt_score);
            txt_score.setBackgroundResource(R.drawable.bg_13);
            txt_score.setText(csore);

            TextView txt_teacher = (TextView) itemView.findViewById(R.id.txt_teacher);
            txt_teacher.setBackgroundResource(R.drawable.bg_14);
            txt_teacher.setText(teacher);

            TextView txt_week = (TextView) itemView.findViewById(R.id.txt_week);
            txt_week.setBackgroundResource(R.drawable.bg_15);
            txt_week.setText(week);

            TextView txt_time = (TextView) itemView.findViewById(R.id.txt_time);
            txt_time.setBackgroundResource(R.drawable.bg_16);
            txt_time.setText(time);

            TextView txt_add = (TextView) itemView.findViewById(R.id.txt_add);
            txt_add.setBackgroundResource(R.drawable.bg_17);
            txt_add.setText(add);

            return itemView;
        }


        public View getView(int position, View convertView, ViewGroup parent)
        {
            return itemViews[position];
        }
    }
}
