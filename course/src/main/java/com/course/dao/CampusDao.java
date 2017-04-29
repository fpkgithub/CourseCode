package com.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.course.entity.Campus;
import com.course.entity.Teacher;

public interface CampusDao
{
	 /**
     * 
     * @param id
     * @return
     */
	int insertCampus(@Param("canum") String canum, @Param("caname") String caname);
	List<Campus> queryAll();
}
