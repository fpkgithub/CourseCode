package com.course.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.course.entity.CourseTable;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class CourseTableDaoTest
{

	@Autowired
	private CourseTableDao courseTableDao;

	@Test
	public void testInsertCourseTable()
	{

		/*CourseTable courseTable = new CourseTable();
		courseTable.setSid("20161");
		courseTable.setTnum("0001805");
		courseTable.setFirst("12");
		courseTable.setSecond("234");
		courseTable.setThird("23");
		courseTable.setFour("234");
		courseTable.setFive("23");
		courseTable.setDescr("23");*/
		//int count = courseTableDao.insertCourseTable(courseTable);
		//System.out.println("count:" + count);

	}

	@Test
	public void test()
	{
		List<CourseTable> list = courseTableDao.queryAll("1", "1");
		for (CourseTable courseTable : list)
		{

			System.out.println("**********:" + courseTable);
		}
	}

}
