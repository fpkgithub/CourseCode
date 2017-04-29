package com.course.service.impl;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.dao.BuildingDao;
import com.course.dao.CampusDao;
import com.course.dao.ClassroomCourseTableDao;
import com.course.dao.ClassroomDao;
import com.course.dao.SemesterDao;
import com.course.dto.RoomCourse;
import com.course.dto.TeacherCourse;
import com.course.entity.Building;
import com.course.entity.Campus;
import com.course.entity.Classroom;
import com.course.entity.ClassroomCourseTable;
import com.course.entity.Semester;
import com.course.service.RoomCourseService;

@Service
public class RoomCourseServiceImpl implements RoomCourseService
{

	public static String cookie;

	//学期DAO
	@Autowired
	private SemesterDao semesterDao;

	//校区DAO
	@Autowired
	private CampusDao campusDao;

	//教学楼
	@Autowired
	private BuildingDao buildingDao;

	//教室
	@Autowired
	private ClassroomDao classroomDao;

	//教室课表DAO
	@Autowired
	private ClassroomCourseTableDao classroomCourseTableDao;

	RoomCourse roomCourse = new RoomCourse();
	
	
	//获取验证码和cookie
		public BufferedImage getImage()
		{
			//cookie和验证码
			cookie = roomCourse.getCookies();
			//System.out.println("cookie:1--"+cookie);
			BufferedImage image = RoomCourse.getImage(cookie);
			return image;
			
		}

	@Override
	@Transactional
	public List<Semester> getSemester()
	{
		//cookie
		//cookie = roomCourse.getCookies();
		//验证码
		//roomCourse.getImage(cookie);
		//查询学期从数据库
		List<Semester> listSemester = semesterDao.queryAll();
		int temp = listSemester.size();
		if (temp == 0)
		{
			//从网络加载学期
			Map<String, String> list = roomCourse.getSemester();
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
			Map<String, String> mapXQ = roomCourse.getXQ();
			for (String key : mapXQ.keySet())
			{
				campusDao.insertCampus(key.toString(), mapXQ.get(key).toString());
			}
			listCampus = campusDao.queryAll();
		}
		return listCampus;
	}

	@Override
	@Transactional
	public List<Building> getBuilding(String canum)
	{
		//教学楼
		List<Building> listBuilding = buildingDao.queryAll(canum);
		int temp = listBuilding.size();
		if (temp == 0)
		{
			//从网上获取
			Map<String, String> mapBuilding = roomCourse.getJXL(canum);
			for (String key : mapBuilding.keySet())
			{
				buildingDao.insertBuilding(key.toString(), canum, mapBuilding.get(key).toString());
			}
			listBuilding = buildingDao.queryAll(canum);
		}

		return listBuilding;
	}

	@Override
	@Transactional
	public List<Classroom> getClassroom(String bnum)
	{
		//教室
		List<Classroom> listClassroom = classroomDao.queryAll();
		int temp = listClassroom.size();
		if (temp == 0)
		{
			//从网上获取
			Map<String, String> mapClassroom = roomCourse.getJS(bnum);
			for (String key : mapClassroom.keySet())
			{
				classroomDao.insertClassroom(key.toString(), bnum,
						mapClassroom.get(key).toString());
			}
			listClassroom = classroomDao.queryAll();
		}
		return listClassroom;
	}

	@Override
	@Transactional
	public List<ClassroomCourseTable> getCourseTable(String termId, String xq, String jxl,
			String room, String code)
	{

		System.out.println("jxl:" + jxl);
		System.out.println("room:" + room);
		List<ClassroomCourseTable> listCourse = classroomCourseTableDao.queryAll(jxl, room);
		int temp1 = listCourse.size();
		System.out.println("temp1:" + temp1);
		if (temp1 == 0)
		{

			Map<Integer, List> map = new HashMap<Integer, List>();
			map = roomCourse.getCourseByRoom(termId, xq, jxl, room, code, cookie);
			System.out.println("*****************" + map);
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
					classroomCourseTableDao.insertClassroomCourseTable(jxl, room, str, temp[0],
							temp[1], temp[2], temp[3], temp[4]);
					count++;
				}
			}
			listCourse = classroomCourseTableDao.queryAll(jxl, room);
		}
		return listCourse;
	}

}
