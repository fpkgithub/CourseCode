package com.course.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.course.entity.Teacher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class TeacherDaoTest
{

	@Resource
	TeacherDao teacherDao;

	@Test
	public void testQueryAllTeacher()
	{
		List<Teacher> list = teacherDao.queryAll();
		System.out.println(list);
	}

	@Test
	public void testInsertTeacher()
	{
		String tnum = "122";
		String tname = "777";
		int count = teacherDao.insertTeacher(tnum, tname);
		System.out.println("count:" + count);
	}

}
