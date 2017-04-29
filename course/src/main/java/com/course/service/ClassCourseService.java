package com.course.service;

import java.util.List;

import com.course.entity.AdminClass;
import com.course.entity.Semester;

/**
 * @author a boy
 *班级课表
 */
public interface ClassCourseService
{
	/**
	 * 获取学期
	 * @return
	 */
	List<Semester> getSemester();

	/**
	 * @return
	 * 获取行政班级
	 */
	List<AdminClass> getAdminClass();

	/**
	 * @param Sel_XNXQ
	 * @param Sel_XZBJ
	 * @param txt_yzm
	 * @param cookie
	 * @return
	 */
	String getCourseTable();
}
