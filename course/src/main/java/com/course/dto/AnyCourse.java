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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author a boy
 *
 */
public class AnyCourse
{
	//获取cookie
	public String getCookies()
	{
		String cookie = "";
		try
		{
			URL url = new URL("http://jw.zzti.edu.cn/ZNPK/KBFB_RXKBSel.aspx");
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
			conn.setRequestProperty("Referer", "http://jw.zzti.edu.cn/ZNPK/KBFB_RXKBSel.aspx");

			InputStream in = conn.getInputStream();
			image = ImageIO.read(in);

			File file = new File("C:/Users/Administrator/Desktop/img/any.jpg");
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

	//获取学期
	public Map<String, String> getSemester()
	{
		Map<String, String> map = new TreeMap<String, String>();
		String url = "http://jw.zzti.edu.cn/ZNPK/KBFB_RXKBSel.aspx";
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

	//获取校区
	public Map<String, String> getXQ()
	{
		String url = "http://jw.zzti.edu.cn/ZNPK/KBFB_RXKBSel.aspx";
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			Document document = Jsoup.connect(url).get();
			Elements elements = document.select("[name=Sel_XQXX] > option");
			for (Element e : elements)
			{
				if (e.attr("value") != "")
					map.put(e.attr("value"), e.text());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("map:" + map);
		return map;

	}

	//获取任选课表
	public List<List<String>> getCourseByAny(String termId, String tid, String code,
			Map<String, String> map, String cookie)
	{
		List<List<String>> clist = new ArrayList<List<String>>();
		try
		{
			URL url = new URL("http://jw.zzti.edu.cn/ZNPK/KBFB_RXKBSel_rpt.aspx");
			String param = "Sel_XNXQ=" + termId + "&txtxzbj=&Sel_XZBJ=" + tid + "&type=1"
					+ "&txt_yzm=" + code;
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
			conn.setRequestProperty("Referer", "http://jw.zzti.edu.cn/ZNPK/KBFB_RXKBSel.aspx");
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
			Elements elements = doc.select("td");
			String str = doc.select("table").select("tr").get(3).select("td").text();

			elements.get(0);

			String XQ = map.get(tid);
			String target = "校区：" + XQ;
			List<String> temp = new ArrayList<String>();
			String[] tempStr = str.split(" ");
			for (String s : tempStr)
				temp.add(s);
			clist.add(temp);
			int count = temp.size();
			int num = 0;
			boolean flag = false;
			boolean xqFlag = false;
			temp = new ArrayList<String>();

			for (Element week : elements)
			{
				if (flag == true)
				{
					if (num == 0)
						temp.add(Integer.toString(clist.size()));
					else
					{
						temp.add(week.html().replaceAll("<br />", "").replaceAll("<br>", ""));
					}
					num++;
				}
				if (num == count)
				{
					clist.add(temp);
					num = 0;
					temp = new ArrayList<String>();
				}
				if (week.html().equals("上课地点") && xqFlag == true)
					flag = true;
				if (week.html().contains("校区：") && flag == true)
					break;
				if (week.html().equals(target))
					xqFlag = true;
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		System.out.println("clist:" + clist);
		return clist;

	}

}
