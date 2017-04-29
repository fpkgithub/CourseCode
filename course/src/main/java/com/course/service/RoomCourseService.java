package com.course.service;

import java.awt.image.BufferedImage;
import java.util.List;

import com.course.entity.Building;
import com.course.entity.Campus;
import com.course.entity.Classroom;
import com.course.entity.ClassroomCourseTable;
import com.course.entity.CourseTable;
import com.course.entity.Semester;

public interface RoomCourseService
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
	 * @return
	 */
	List<Campus> getCampus();

	/**
	 * @return
	 */
	List<Building> getBuilding(String canum);

	/**
	 * @return
	 */
	List<Classroom> getClassroom(String bnum);

	/**
	 * 获取课表
	 * @return
	 */
	 List<ClassroomCourseTable> getCourseTable(String termId,String xq,String jxl,String room,String code);

}
