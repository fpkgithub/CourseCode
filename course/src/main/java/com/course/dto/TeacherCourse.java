package com.course.dto;

import java.awt.image.BufferedImage;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author a boy
 *
 */

public class TeacherCourse
{

	@Autowired
	HttpServletRequest request;

	//获取Cookies
	public String getCookies()
	{
		String cookie = "";
		try
		{
			URL url = new URL("http://jw.zzti.edu.cn/ZNPK/TeacherKBFB.aspx");
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
	public static BufferedImage getImage(String cookie)
	{
		BufferedImage image = null;
		try
		{
			URL url = new URL("http://jw.zzti.edu.cn/sys/ValidateCode.aspx");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Cookie", cookie);
			conn.setRequestProperty("Referer", "http://jw.zzti.edu.cn/ZNPK/TeacherKBFB.aspx");
			
			InputStream in = conn.getInputStream();
			
			image = ImageIO.read(in);
			
			File file = new File("C:/Users/Administrator/Desktop/img/teacher.jpg");
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
		return image;
	}

	//获取学期信息
	public Map<String, String> getSemester()
	{
		Map<String, String> map = new TreeMap<String, String>();
		String url = "http://jw.zzti.edu.cn/ZNPK/TeacherKBFB.aspx";
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

	//获取所有教师
	public Map<String, String> getTeacher()
	{
		String url = "http://jw.zzti.edu.cn/ZNPK/Private/List_JS.aspx?xnxq=&js=";
		Map<String, String> map = new TreeMap<String, String>();
		try
		{
			Document document = Jsoup.connect(url).get();
			String str = document.select("script").get(0).data();
			Document doc1 = Jsoup.parse(str);
			Elements elements = doc1.getElementsByTag("select").select("[name=Sel_JS] > option");
			for (Element e : elements)
			{
				map.put(e.attr("value"), e.text());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("getTeacher():" + map);
		return map;
	}

	//请求课表内容
	public Map<Integer, List> getCoureByTeacher(String termId, String tid, String code,
			String cookie)
	{
		Map<Integer, List> cmap = new HashMap<>();
		try
		{
			URL url = new URL("http://jw.zzti.edu.cn/ZNPK/TeacherKBFB_rpt.aspx");
			String param = "Sel_XNXQ=" + termId + "&Sel_JS=" + tid + "&type=" + 1 + "&txt_yzm="
					+ code;
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
			conn.setRequestProperty("Referer", "http://jw.zzti.edu.cn/ZNPK/TeacherKBFB.aspx");
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
			System.out.println("info:" + info);
			Document doc = Jsoup.parse(info);
			Elements elements = doc.select("td");

			//获取课表的头部信息
			String str = doc.select("table").select("tr").get(3).select("td").text();

			elements.get(0);

			//初始化List
			List<String> cl0 = new ArrayList<>();
			List<String> cl1 = new ArrayList<>();
			List<String> cl2 = new ArrayList<>();
			List<String> cl3 = new ArrayList<>();
			List<String> cl4 = new ArrayList<>();
			List<String> cl5 = new ArrayList<>();
			List<String> cl6 = new ArrayList<>();
			List<String> cl7 = new ArrayList<>();

			cmap.put(0, cl0);
			cmap.put(1, cl1);
			cmap.put(2, cl2);
			cmap.put(3, cl3);
			cmap.put(4, cl4);
			cmap.put(5, cl5);
			cmap.put(6, cl6);
			cmap.put(7, cl7);

			cmap.get(0).add(str);

			for (Element week : elements)
			{
				if (week.html().equals("星期一"))
				{
					Element wtr = week.parent();
					Elements trs = wtr.siblingElements();
					for (int j = 0; j < trs.size(); j++)
					{

						Element tr = trs.get(j);
						Elements trds = tr.children();
						int ncounter = 0;
						for (int i = 0; i < trds.size(); i++)
						{
							Element td = trds.get(i);

							if (td.attr("valign").equals("top"))
							{
								if (td.text() != null)
									cmap.get(i - ncounter + 1).add(td.text() + "");
								else
									cmap.get(i - ncounter + 1).add("");
							}
							else
								ncounter++;
						}
					}
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		System.out.println("getCoureByTeacher():" + cmap);
		return cmap;

	}

}
