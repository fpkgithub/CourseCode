package com.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.course.entity.ClassCourseTable;

public interface ClassCourseTableDao
{
	/**
	 * @return
	 */
	int insertClassCourseTable(@Param("sid") String sid, @Param("acnum") String acnum,
			@Param("cdescr") String cdescr, @Param("classone") String classone,
			@Param("classtwo") String classtwo, @Param("classthree") String classthree,
			@Param("classfour") String classfour, @Param("classfive") String classfive);

	List<ClassCourseTable> queryAll(@Param("sid") String sid, @Param("acnum") String acnum);
}
