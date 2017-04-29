package com.course.service.impl;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.dao.CourseTableDao;
import com.course.dao.SemesterDao;
import com.course.dao.TeacherDao;
import com.course.dto.TeacherCourse;
import com.course.entity.CourseTable;
import com.course.entity.Semester;
import com.course.entity.Teacher;
import com.course.service.TeacherCourseService;

@Service
public class TeacherCourseServiceImpl implements TeacherCourseService
{
	public static String cookie;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SemesterDao semesterDao;

	@Autowired
	private TeacherDao teacherDao;

	@Autowired
	private CourseTableDao courseTableDao;

	TeacherCourse teacherCourse = new TeacherCourse();

	//获取验证码和cookie
	public BufferedImage getImage()
	{
		//cookie和验证码
		cookie = teacherCourse.getCookies();
		//System.out.println("cookie:1--"+cookie);
		BufferedImage image = TeacherCourse.getImage(cookie);
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
			Map<String, String> list = teacherCourse.getSemester();
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
	public List<Teacher> getTeacher()
	{
		List<Teacher> listTeacher = teacherDao.queryAll();
		int temp = listTeacher.size();
		if (temp == 0)
		{
			//从网络获取教师
			Map<String, String> map = teacherCourse.getTeacher();
			for (String key : map.keySet())
			{
				teacherDao.insertTeacher(key.toString(), map.get(key).toString());
			}
			listTeacher = teacherDao.queryAll();
		}

		return listTeacher;
	}

	@Override
	@Transactional
	public List<CourseTable> getCourseTable(String sid, String tnum, String code)
	{

		List<CourseTable> listCourse = courseTableDao.queryAll(sid, tnum);
		int temp1 = listCourse.size();
		System.out.println("temp1:" + temp1);
		if (temp1 == 0)
		{

			System.out.println("cookie:1--"+cookie);
			Map<Integer, List> map = new HashMap<Integer, List>();
			map = teacherCourse.getCoureByTeacher(sid, tnum, code, cookie);
			
			//插入数据库课表
			boolean flag = true;
			//纪录表头信息
			String str = "";
			int count = 1;
			for (Entry<Integer, List> list : map.entrySet())
			{
				if (count == 6)
				{
					break;
				}
				String[] temp = new String[5];
				int i = 0;
				//取一天的5节课程
				if (flag)
				{
					//去第一条信息
					for (Object object : list.getValue())
					{
						str = object.toString();
						i++;
						if (i == 1)
						{
							flag = false;
							break;
						}
					}
				}
				else
				{
					for (Object object : list.getValue())
					{
						temp[i++] = object.toString();
						if (i == 5)
						{
							break;
						}
					}
					//插入数据库
					courseTableDao.insertCourseTable(sid, tnum, str, temp[0], temp[1], temp[2],
							temp[3], temp[4]);
					count++;
				}
			}
			listCourse = courseTableDao.queryAll(sid, tnum);
		}
		return listCourse;
	}

}
