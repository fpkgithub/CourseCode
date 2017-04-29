package com.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.course.entity.CourseTable;

public interface CourseTableDao
{
	/**
	 * @return
	 */
	int insertCourseTable(@Param("sid") String sid,@Param("tnum") String tnum,@Param("descr") String descr,@Param("cone") String cone,@Param("ctwo") String ctwo,@Param("cthree") String cthree,@Param("cfour") String cfour,@Param("cfive") String cfive);
	List<CourseTable> queryAll(@Param("sid") String sid, @Param("tnum") String tnum);
}
