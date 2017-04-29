package com.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.course.entity.AdminClass;

/**
 * @author a boy
 *行政班级
 */
public interface AdminClassDao
{
	/**
	* 
	* @param id
	* @return
	*/
	int insertAdminClass(@Param("acnum") String acnum, @Param("acname") String acname);

	/**
	 * @return
	 */
	List<AdminClass> queryAll();
}
