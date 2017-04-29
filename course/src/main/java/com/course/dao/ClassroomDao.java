package com.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.course.entity.Classroom;
import com.course.entity.Teacher;

public interface ClassroomDao
{
	 /**
     * 
     * @param id
     * @return
     */
	int insertClassroom(@Param("crnum") String crnum, @Param("bnum") String bnum, @Param("crname") String crname);
	List<Classroom> queryAll();
}
