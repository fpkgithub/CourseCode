package com.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.course.entity.Semester;

public interface SemesterDao
{
	/**
	* 
	* @param id
	* @return
	*/
	int insertSemester(@Param("sid") String sid, @Param("sname") String sname);

	List<Semester> queryAll();
}
