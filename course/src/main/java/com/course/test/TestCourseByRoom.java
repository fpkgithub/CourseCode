package com.course.test;

import java.util.Map;
import java.util.Scanner;

import com.course.dto.RoomCourse;

public class TestCourseByRoom
{
	static String cookie;

	public static void main(String[] args)
	{
		RoomCourse roomCourse = new RoomCourse();

		cookie = roomCourse.getCookies();
		System.out.println("cookie:" + cookie);

		roomCourse.getImage(cookie);

		roomCourse.getSemester();

		Map<String, String> XQmap = roomCourse.getXQ();

		roomCourse.getJXL("2");

		roomCourse.getJS("210");

		Scanner scanner = new Scanner(System.in);
		String code = scanner.next();
		roomCourse.getCourseByRoom("20161", "2", "210", "2100104",code, cookie);
		scanner.close();
	}
}
