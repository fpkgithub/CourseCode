package com.course.service.impl;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.ls.LSInput;

import com.course.dao.CampusDao;
import com.course.dao.OptionalCourseTableDao;
import com.course.dao.SemesterDao;
import com.course.dto.AnyCourse;
import com.course.entity.Campus;
import com.course.entity.OptionalCourseTable;
import com.course.entity.Semester;
import com.course.service.AnyCourseService;

@Service
public class AnyCourseServiceImpl implements AnyCourseService
{
	public static String cookie;

	//学期DAO
	@Autowired
	private SemesterDao semesterDao;

	//校区DAO
	@Autowired
	private CampusDao campusDao;

	AnyCourse anyCourse = new AnyCourse();

	//课程DAO
	@Autowired
	private OptionalCourseTableDao optionalCourseTableDao;

	public static Map<String, String> mapXQ = new HashMap<String, String>();

	//获取验证码和cookie
	@Override
	public BufferedImage getImage()
	{
		//cookie和验证码
		cookie = anyCourse.getCookies();
		BufferedImage image = AnyCourse.getImage(cookie);
		return image;
	}

	@Override
	@Transactional
	public List<Semester> getSemester()
	{
		List<Semester> listSemester = semesterDao.queryAll();

		int temp = listSemester.size();
		System.out.println("temp:" + temp);

		if (temp == 0)
		{
			//从网络加载学期
			Map<String, String> list = anyCourse.getSemester();
			//插入数据库
			for (String key : list.keySet())
			{
				semesterDao.insertSemester(key.toString(), list.get(key).toString());
			}
			listSemester = semesterDao.queryAll();
		}
		return listSemester;
	}

	@Override
	@Transactional
	public List<Campus> getCampus()
	{
		//校区
		List<Campus> listCampus = campusDao.queryAll();
		int temp = listCampus.size();
		if (temp == 0)
		{
			//从网上获取
			mapXQ = anyCourse.getXQ();
			
			for (String key : mapXQ.keySet())
			{
				campusDao.insertCampus(key.toString(), mapXQ.get(key).toString());
			}
			listCampus = campusDao.queryAll();
		}
		for (int i = 0; i < listCampus.size(); i++)
		{
			mapXQ.put(listCampus.get(i).getCanum(), listCampus.get(i).getCaname());
		}
		System.out.println("mapXQ1:" + mapXQ);
		return listCampus;
	}

	@Override
	@Transactional
	public List<OptionalCourseTable> getCourse(String sid, String canum, String code)
	{
		List<OptionalCourseTable> listAny = optionalCourseTableDao.queryAll(sid, canum);
		int temp = listAny.size();
		System.out.println("temp:" + temp);
		System.out.println("mapXQ2:" + mapXQ);
		if (temp == 0)
		{
			List<List<String>> clist = anyCourse.getCourseByAny(sid, canum, code, mapXQ, cookie);
			System.out.println("clist" + clist);
			for (int i = 1; i < clist.size(); i++)
			{
				optionalCourseTableDao.insertOptionalCourseTable(sid, canum, "",
						clist.get(i).get(1), clist.get(i).get(2), clist.get(i).get(3),
						clist.get(i).get(4), clist.get(i).get(5), clist.get(i).get(6),
						clist.get(i).get(7), clist.get(i).get(8), "");

			}

			listAny = optionalCourseTableDao.queryAll(sid, canum);
		}
		return listAny;
	}

}
