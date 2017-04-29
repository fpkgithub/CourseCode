package com.course.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.course.entity.AdminClass;
import com.course.entity.Semester;
import com.course.service.ClassCourseService;

@Controller
@RequestMapping("/class")
public class ClassCourseController
{
	//班级课表service
	@Autowired
	ClassCourseService classCourseService;

	//加载班级课表数据
	@RequestMapping(value = "/getClass", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String, List> KBFB_ClassSel()
	{
		Map<String, List> mapResult = new HashMap<>();

		try
		{
			List<Semester> listSemester = classCourseService.getSemester();
			mapResult.put("Semester", listSemester);

			List<AdminClass> listAdmin = classCourseService.getAdminClass();
			mapResult.put("AdminClass", listAdmin);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("KBFB_ClassSel()错误...");
		}
		return mapResult;
	}

	//加载班级课表
	@RequestMapping(value = "/getCourse", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public String getClassCourse()
	{
		String str = "";
		//classCourseService.getClassTableImg();
		return null;
	}

}
