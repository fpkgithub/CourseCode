package com.course.test;

import java.util.Scanner;

import com.course.dto.ClassCourse;

/**
 * @author a boy
 *
 */
public class TestCourseByClass
{
	static String cookie;

	public static void main(String[] args)
	{
		ClassCourse classCourse = new ClassCourse();

		cookie = classCourse.getCookies();
		System.out.println("cookie:" + cookie);

		classCourse.getImage(cookie);

		classCourse.getSemester();

		classCourse.getAdminClass();

		Scanner scanner = new Scanner(System.in);
		String code = scanner.next();
		classCourse.getCoureByClass("20161", "2013000000", code, cookie);
		scanner.close();

	}

}
