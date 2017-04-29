package com.course.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.course.entity.Semester;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class SemesterDaoTest
{
	@Resource
	private SemesterDao semesterDao;

	@Test
	public void test1()
	{
		System.out.println(123777);
	}

	@Test
	public void testInsertSemester()
	{
		String sid = "22322";
		String sname = "3";
		int insertCount = semesterDao.insertSemester(sid, sname);
		System.out.println("insertCount:" + insertCount);
	}

	@Test
	public void testQuerySemester()
	{

		System.out.println("********************");
		List<Semester> list = semesterDao.queryAll();
		System.out.println("semester:" + list.toString());
	}

}
