package com.test.com.courseapp;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GetCourse
{

    // 发送请求 把学期 老师
    public Map<Integer, List> getCourse(String termId, String tid, String code, CourseDao courseDao)
    {
        Map<Integer, List> cmap = new LinkedHashMap<>();
        List<String> cl0 = new ArrayList<>();
        List<String> cl1 = new ArrayList<>();
        List<String> cl2 = new ArrayList<>();
        List<String> cl3 = new ArrayList<>();
        List<String> cl4 = new ArrayList<>();
        List<String> cl5 = new ArrayList<>();

        cmap.put(6, cl0);
        cmap.put(1, cl1);
        cmap.put(2, cl2);
        cmap.put(3, cl3);
        cmap.put(4, cl4);
        cmap.put(5, cl5);

        //System.out.println("termId:" + termId + "---tid:" + tid);
        int temp = courseDao.queryData(termId, tid);
        System.out.println("temp.size（）:" + temp);
        if (temp == 0)
        {
            //从网上加载数据
            try
            {

                String tempStr = "http://acoolboy.me/ZNPK/TeacherKBFB_table/";
                tempStr += (termId + "/" + tid + "/" + code);
                URL url = new URL(tempStr);

                //获得json数据
                String jsonData = doPostNoParam(url);
                //System.out.println("jsondata:  " + jsonData);

                JSONObject jsoo = new JSONObject(jsonData);
                JSONArray jsaTeacherCourse = (JSONArray) jsoo.get("TeacherCourse");

                for (int i = 0; i < jsaTeacherCourse.length(); i++)
                {
                    JSONObject jso = (JSONObject) jsaTeacherCourse.get(i);

                    //插入数据库
                    Course course = new Course();
                    course.setSid(termId);
                    course.setTid(tid);

                    //插入信息
                    course.setCdesc(jso.getString("descr"));
                    course.setCone(jso.getString("cone"));
                    course.setCtwo(jso.getString("ctwo"));
                    course.setCthree(jso.getString("cthree"));
                    course.setCfour(jso.getString("cfour"));
                    course.setCfive(jso.getString("cfive"));
                    //插入course
                    courseDao.insertData(course);

                   /* //取表头信息
                    List<String> tempSttr = new ArrayList<>();
                    tempSttr.add(jso.getString("descr"));
                    cmap.put(6, tempSttr);

                    //去周一/五
                    List<String> tempList = new ArrayList<>();
                    tempList.add(jso.getString("cone"));
                    tempList.add(jso.getString("ctwo"));
                    tempList.add(jso.getString("cthree"));
                    tempList.add(jso.getString("cfour"));
                    tempList.add(jso.getString("cfive"));

                    //System.out.println(jso);
                    cmap.put(i + 1, tempList);*/
                }

            } catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }

        //从数据库读
        try
        {
            List<Course> listCourse = courseDao.queryData2(termId, tid);
            cmap.get(6).add(listCourse.get(0).getCdesc() + "");
            for (int i = 0; i < 5; i++)
            {
                //System.out.println("listCourse.get(i).getCone()" + listCourse.get(i).getCone());
                cmap.get(i + 1).add(listCourse.get(i).getCone() + "");
                cmap.get(i + 1).add(listCourse.get(i).getCtwo() + "");
                cmap.get(i + 1).add(listCourse.get(i).getCthree() + "");
                cmap.get(i + 1).add(listCourse.get(i).getCfour() + "");
                cmap.get(i + 1).add(listCourse.get(i).getCfive() + "");
                //System.out.println("cmap.get(i+1)：" + cmap.get(i + 1));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        System.out.println("cmap:" + cmap);
        return cmap;

    }

    //获取验证码
    public Bitmap getImage()
    {
        Bitmap bitmap = null;
        try
        {
            URL url = new URL("http://acoolboy.me/ZNPK/get_teimage");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //conn.setRequestProperty("Cookie", cookie);
            //conn.setRequestProperty("Referer", "http://jw.zzti.edu.cn/ZNPK/TeacherKBFB.aspx");
            InputStream in = conn.getInputStream();
            String filePath = "/sdcard/AA/";

            System.out.println("img--->");
            File dirFile = new File(Environment.getExternalStorageDirectory(), "test.jpg");
            if (!dirFile.exists())
            {
                dirFile.createNewFile();
            }
            System.out.println("---->" + dirFile);
            FileOutputStream fo = new FileOutputStream(dirFile);
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = in.read(buf, 0, buf.length)) != -1)
            {
                fo.write(buf, 0, length);

            }

            in.close();
            fo.close();
            conn.disconnect();

            //加载
            File file2 = new File(dirFile.toString());
            if (file2.exists())
            {
                bitmap = BitmapFactory.decodeFile(dirFile.toString());
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }

    //从服务器获取学期和教师
    public List<Map<String, String>> getCourseInfo(SemesterDao semesterDao, TeacherDao teacherDao)
    {
        List<Map<String, String>> listResult = new ArrayList<Map<String, String>>();

        Map<String, String> mapSemester = new HashMap<>();
        Map<String, String> mapTeacher = new HashMap<>();

        int temp = semesterDao.queryDataList("semesterinfo");
        System.out.println("temp:" + temp);
        if (temp == 0)
        {
            try
            {

                URL url = new URL("http://acoolboy.me/ZNPK/TeacherKBFB");

                //获取json数据
                String jsonData = doPostNoParam(url);

                JSONObject jsoo = new JSONObject(jsonData);

                //得到Semester
                JSONArray jsaSemester = (JSONArray) jsoo.get("Semester");
                for (int i = 0; i < jsaSemester.length(); i++)
                {
                    JSONObject jso = (JSONObject) jsaSemester.get(i);
                    mapSemester.put(jso.getString("sid"), jso.getString("sname"));

                    //插入数据
                    Semester semester = new Semester();
                    semester.setSid(jso.getString("sid"));
                    semester.setSname(jso.getString("sname"));
                    semesterDao.insertData(semester);
                }

                //得到Teacher
                JSONArray jsaTeacher = (JSONArray) jsoo.get("Teacher");
                //开启事务
                SQLiteDatabase mDatabase = teacherDao.mDatabase;
                for (int i = 0; i < jsaTeacher.length(); i++)
                {
                    JSONObject jso = (JSONObject) jsaTeacher.get(i);
                    mapTeacher.put(jso.getString("tnum"), jso.getString("tname"));

                    //插入数据 返回后插入
                    Teacher teacher = new Teacher();
                    teacher.setTid(jso.getString("tnum"));
                    teacher.setTname(jso.getString("tname"));
                    teacherDao.insertData(teacher);

                }
                //结束事务
                mDatabase.setTransactionSuccessful();
                mDatabase.endTransaction();

                System.out.println("size 0: " + mapSemester.size() + "; size 1: " + mapTeacher.size());
                listResult.add(mapSemester);
                listResult.add(mapTeacher);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }


        //从数据库查询Semester
        List<Semester> lsitSemester = semesterDao.queryDataList2("semesterinfo");
        int lenSemester = lsitSemester.size();
        for (int i = 0; i < lenSemester; i++)
        {
            mapSemester.put(lsitSemester.get(i).getSid(), lsitSemester.get(i).getSname());
        }
        listResult.add(mapSemester);

        //从数据库查询Teacher
        List<Teacher> lsitTeacher = teacherDao.queryDataList2();
        int lenTeacher = lsitTeacher.size();
        for (int i = 0; i < lenTeacher; i++)
        {
            mapTeacher.put(lsitTeacher.get(i).getTid(), lsitTeacher.get(i).getTname());
        }
        listResult.add(mapTeacher);

        return listResult;
    }


    public String doPostNoParam(URL url)
    {
        String str = "";
        try
        {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                builder.append(line);
            }
            str = builder.toString();
            reader.close();
            conn.disconnect();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return str;

    }

}
