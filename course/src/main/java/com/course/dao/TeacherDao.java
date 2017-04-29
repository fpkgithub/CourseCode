package com.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.course.entity.Teacher;

public interface TeacherDao
{
	/**
	* 
	* @param id
	* @return
	*/
	int insertTeacher(@Param("tnum") String tnum, @Param("tname") String tname);

	List<Teacher> queryAll();
}
