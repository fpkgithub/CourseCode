package com.course.test;

import java.util.Scanner;

import com.course.dto.TeacherCourse;

/**
 * @author a boy
 *
 */
public class TestCourseByTeacher
{
	static String cookie;

	public static void main(String[] args)
	{
		TeacherCourse courseInfo = new TeacherCourse();

		cookie = courseInfo.getCookies();
		System.out.println("cookie:" + courseInfo.getCookies());

		courseInfo.getImage(cookie);

		courseInfo.getSemester();

		courseInfo.getTeacher();

		Scanner scanner = new Scanner(System.in);
		String code = scanner.next();
		courseInfo.getCoureByTeacher("20161", "0001805", code, cookie);
		scanner.close();
	}
}
