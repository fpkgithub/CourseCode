package com.course.service;

import java.awt.image.BufferedImage;
import java.util.List;

import com.course.entity.CourseTable;
import com.course.entity.Semester;
import com.course.entity.Teacher;

/**
 * @author a boy
 * 教师课表
 */
public interface TeacherCourseService
{
	
	
	/**
	 * @return
	 */
	BufferedImage getImage();
	
	/**
	 * 获取学期
	 * @return
	 */
	List<Semester> getSemester();

	/**
	 * 获取教师
	 * @return
	 */
	List<Teacher> getTeacher();

	/**
	 * 获取课表
	 * @return
	 */
	List<CourseTable> getCourseTable(String sid, String tid, String code);
}
