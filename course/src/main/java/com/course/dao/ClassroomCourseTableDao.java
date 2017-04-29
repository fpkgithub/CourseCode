package com.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.course.entity.ClassroomCourseTable;

public interface ClassroomCourseTableDao
{
	/**
	 * @return
	 */
	int insertClassroomCourseTable(@Param("sid") String sid, @Param("crnum") String crnum,
			@Param("ccdescr") String ccdescr, @Param("ccone") String ccone,
			@Param("cctwo") String cctwo, @Param("ccthree") String ccthree,
			@Param("ccfour") String ccfour, @Param("ccfive") String ccfive);

	List<ClassroomCourseTable> queryAll(@Param("sid") String sid, @Param("crnum") String crnum);
}
