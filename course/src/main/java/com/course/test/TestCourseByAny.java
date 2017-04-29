package com.course.test;

import java.util.Map;
import java.util.Scanner;

import com.course.dto.AnyCourse;

public class TestCourseByAny
{
	static String cookie;

	public static void main(String[] args)
	{
		AnyCourse anyCourse = new AnyCourse();

		cookie = anyCourse.getCookies();
		System.out.println("cookie:" + cookie);

		anyCourse.getImage(cookie);

		anyCourse.getSemester();

		Map<String, String> XQMap = anyCourse.getXQ();

		Scanner scanner = new Scanner(System.in);
		String code = scanner.next();
		anyCourse.getCourseByAny("20161", "2", code, XQMap, cookie);
		scanner.close();
	}
}
