package com.test.com.courseapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

/**
 * Created by Administrator on 2017/4/28.
 */

public class GetAnyCourse
{
    //获取验证码
    public Bitmap getImage()
    {
        Bitmap bitmap = null;
        try
        {
            URL url = new URL("http://acoolboy.me/any/getAnyImgage");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream in = conn.getInputStream();

            System.out.println("img--->");
            File dirFile = new File(Environment.getExternalStorageDirectory(), "test.jpg");
            if (!dirFile.exists())
            {
                dirFile.createNewFile();
            }
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

    //获取学期和校区
    public List<Map<String, String>> getInit()
    {
        List<Map<String, String>> listResult = new ArrayList<Map<String, String>>();

        Map<String, String> mapSemester = new TreeMap<String, String>(new Comparator<String>()
        {
            public int compare(String o1, String o2)
            {
                return o2.compareTo(o1);
            }
        });
        Map<String, String> mapCampus = new TreeMap<>();


        String str = "http://acoolboy.me/any/getStart";

        try
        {
            URL url = new URL(str);
            //获取json数据
            String jsonData = doPostNoParam(url,"GET");
            JSONObject jsoo = new JSONObject(jsonData);
            //得到Semester
            JSONArray jsaSemester = (JSONArray) jsoo.get("listSemester");
            for (int i = 0; i < jsaSemester.length(); i++)
            {
                JSONObject jso = (JSONObject) jsaSemester.get(i);
                mapSemester.put(jso.getString("sid"), jso.getString("sname"));
            }
            listResult.add(mapSemester);

            //得到Campus
            JSONArray jsaCampus = (JSONArray) jsoo.get("listCampus");
            for (int i = 0; i < jsaCampus.length(); i++)
            {
                JSONObject jso = (JSONObject) jsaCampus.get(i);
                mapCampus.put(jso.getString("canum"), jso.getString("caname"));
            }
            listResult.add(mapCampus);


        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }


        return listResult;
    }

    public String doPostNoParam(URL url,String method)
    {
        String str = "";
        try
        {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
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

    public Map<Integer, List> getCourse(String semeserId, String campusId, String code)
    {
        Map<Integer, List> cmap = new LinkedHashMap<>();

        String str = "http://acoolboy.me/any/anyCourse/";
        str = str + semeserId + "/" + campusId + "/" + code;
        try
        {
            System.out.println("url:"+str);
            URL url = new URL(str);
            String jsonData = doPostNoParam(url,"POST");

            JSONObject jsoo = new JSONObject(jsonData);
            //System.out.println("jsoo:"+jsoo);

            JSONArray jsaAnyCourse = (JSONArray) jsoo.get("AnyCourse");
            //System.out.println("jsaAnyCourse:"+jsaAnyCourse);

            for (int i = 0; i < jsaAnyCourse.length(); i++)
            {
                JSONObject jso = (JSONObject)jsaAnyCourse.get(i);
                List<String> cl = new ArrayList<>();
                cl.add(jso.getString("coursename"));
                cl.add(jso.getString("score"));
                cl.add(jso.getString("cteacher"));
                cl.add(jso.getString("cweek"));
                cl.add(jso.getString("ctime"));
                cl.add(jso.getString("caddress"));
                cmap.put(i,cl);
            }


        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        System.out.println("cmap:"+cmap);
        return cmap;
    }

}
