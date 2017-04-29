package com.course.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.dao.AdminClassDao;
import com.course.dao.SemesterDao;
import com.course.dto.ClassCourse;
import com.course.entity.AdminClass;
import com.course.entity.Semester;
import com.course.service.ClassCourseService;

@Service
public class ClassCourseServiceImpl implements ClassCourseService
{
	public static String cookie;

	//学期DAO
	@Autowired
	private SemesterDao semesterDao;

	//行政班级DAO
	@Autowired
	private AdminClassDao adminClassDao;

	//班级课表DTO
	ClassCourse classCourse = new ClassCourse();

	@Override
	public List<Semester> getSemester()
	{
		//cookie
		cookie = classCourse.getCookies();
		//验证码
		classCourse.getImage(cookie);
		//查询学期从数据库
		List<Semester> listSemester = semesterDao.queryAll();
		int temp = listSemester.size();
		if (temp == 0)
		{
			//从网络加载学期
			Map<String, String> list = classCourse.getSemester();
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
	public List<AdminClass> getAdminClass()
	{
		List<AdminClass> listAdminClass = adminClassDao.queryAll();
		int temp = listAdminClass.size();
		if (temp == 0)
		{
			//从网上获取行政班级
			Map<String, String> mapAdmin = classCourse.getAdminClass();
			//插入数据库
			for (String key : mapAdmin.keySet())
			{
				adminClassDao.insertAdminClass(key.toString(), mapAdmin.get(key).toString());
			}
			//从数据库读取
			listAdminClass = adminClassDao.queryAll();
		}
		return listAdminClass;
	}

	@Override
	public String getCourseTable()
	{
		
		return "";
	}

}
