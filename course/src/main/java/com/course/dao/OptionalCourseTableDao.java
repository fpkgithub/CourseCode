package com.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.course.entity.OptionalCourseTable;

/**
 * @author a boy
 *
 */
public interface OptionalCourseTableDao
{

	/**
	 * @param sid
	 * @param canum
	 * @param ocdescr
	 * @param coursename
	 * @param score
	 * @param cteacher
	 * @param cno
	 * @param cnum
	 * @param cweek
	 * @param ctime
	 * @param caddress
	 * @param cn
	 * @return
	 */
	int insertOptionalCourseTable(@Param("sid") String sid, @Param("canum") String canum,
			@Param("ocdescr") String ocdescr, @Param("coursename") String coursename,
			@Param("score") String score, @Param("cteacher") String cteacher,
			@Param("cno") String cno, @Param("cnum") String cnum, @Param("cweek") String cweek,
			@Param("ctime") String ctime, @Param("caddress") String caddress,
			@Param("cn") String cn);

	/**
	 * @param sid
	 * @param canum
	 * @return
	 */
	List<OptionalCourseTable> queryAll(@Param("sid") String sid, @Param("canum") String canum);
}
