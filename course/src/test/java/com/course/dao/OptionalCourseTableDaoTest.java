package com.course.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class OptionalCourseTableDaoTest
{
	@Autowired
	OptionalCourseTableDao op;

	@Test
	public void testInsertOptionalCourseTable()
	{
		op.insertOptionalCourseTable("1", "1", "", "1", "1", "1", "1", "1", "1",
				"1", "1", "1");
	}

}
