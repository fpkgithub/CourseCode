package com.course.service;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import com.course.entity.Campus;
import com.course.entity.OptionalCourseTable;
import com.course.entity.Semester;

/**
 * @author a boy
 *
 */

public interface AnyCourseService
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
	 * 获取校区
	 * @return
	 */
	List<Campus> getCampus();

	/**
	 * 获取课表
	 * @return
	 */
	List<OptionalCourseTable> getCourse(String sid, String canum, String code);

}
