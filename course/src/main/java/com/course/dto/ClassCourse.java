package com.course.dto;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author a boy
 *
 */
public class ClassCourse
{
	//获取Cookies
	public String getCookies()
	{
		String cookie = "";
		try
		{
			URL url = new URL("http://jw.zzti.edu.cn/ZNPK/KBFB_ClassSel.aspx");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			Map<String, List<String>> map = conn.getHeaderFields();
			cookie = map.get("Set-Cookie").get(0);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return cookie;
	}

	//获取验证码
	public void getImage(String cookie)
	{
		try
		{
			URL url = new URL("http://jw.zzti.edu.cn/sys/ValidateCode.aspx");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Cookie", cookie);
			conn.setRequestProperty("Referer", "http://jw.zzti.edu.cn/ZNPK/KBFB_ClassSel.aspx");
			InputStream in = conn.getInputStream();
			
			
			
			File file = new File("C:/Users/Administrator/Desktop/img/class.jpg");
			if (!file.exists())
			{
				file.createNewFile();
			}
			FileOutputStream fo = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int length = 0;
			while ((length = in.read(buf, 0, buf.length)) != -1)
			{
				fo.write(buf, 0, length);
			}
			in.close();
			fo.close();
			conn.disconnect();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	//获取学期信息
	public Map<String, String> getSemester()
	{
		Map<String, String> map = new TreeMap<String, String>();
		String url = "http://jw.zzti.edu.cn/ZNPK/KBFB_ClassSel.aspx";
		try
		{
			Document document = Jsoup.connect(url).get();
			Elements elements = document.select("[name=Sel_XNXQ] > option");
			for (Element e : elements)
			{
				map.put(e.attr("value"), e.text());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("getSemester():" + map);
		return map;
	}

	//获取行政班级 Sel_XZBJ
	public Map<String, String> getAdminClass()
	{
		Map<String, String> map = new TreeMap<String, String>();
		String url = "http://jw.zzti.edu.cn/ZNPK/KBFB_ClassSel.aspx";
		try
		{
			Document document = Jsoup.connect(url).get();
			Elements elements = document.select("[name=Sel_XZBJ] > option");
			for (Element e : elements)
			{
				map.put(e.attr("value"), e.text());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("getCourse():" + map);
		return map;
	}

	//获取行政班级课表
	//请求课表内容 
	public boolean getCoureByClass(String Sel_XNXQ, String Sel_XZBJ, String txt_yzm, String cookie)
	{
		try
		{
			URL url = new URL("http://jw.zzti.edu.cn/ZNPK/KBFB_ClassSel_rpt.aspx");
			String param = "Sel_XNXQ=" + Sel_XNXQ + "&txtxzbj=&Sel_XZBJ=" + Sel_XZBJ + "&type=1"
					+ "&txt_yzm=" + txt_yzm;

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Cookie", cookie);
			conn.setRequestProperty("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36");
			conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
			conn.setRequestProperty("Referer", "http://jw.zzti.edu.cn/ZNPK/KBFB_ClassSel.aspx");
			conn.setRequestProperty("Origin", "http://jw.zzti.edu.cn");
			conn.setRequestProperty("Cache-Control", "max-age=0");

			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			dos.writeBytes(param);
			dos.flush();
			dos.close();
			InputStream in = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "gbk"));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				builder.append(line);
			}
			String info = builder.toString();
			Document doc = Jsoup.parse(info);
			Elements elements = doc.select("img");
			for (Element week : elements)
			{
				String img = week.attr("src");
				URL u = new URL("http://jw.zzti.edu.cn/ZNPK/" + img);
				InputStream in2 = u.openStream();
				String pathImge = "";
				FileOutputStream fo = new FileOutputStream(
						new File("C:/Users/Administrator/Desktop/img/classtable.jpg"));
				byte[] buf = new byte[1024];
				int length = 0;
				while ((length = in2.read(buf, 0, buf.length)) != -1)
				{
					fo.write(buf, 0, length);
				}
				in.close();
				fo.close();
			}
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;

	}

}
