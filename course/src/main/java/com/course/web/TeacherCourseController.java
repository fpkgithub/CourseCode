package com.course.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.course.dto.TeacherCourse;
import com.course.entity.CourseTable;
import com.course.entity.Semester;
import com.course.entity.Teacher;
import com.course.service.TeacherCourseService;

/**
 * @author a boy
 *
 */

@Controller
@RequestMapping("/ZNPK")
public class TeacherCourseController
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static String cookie;
	TeacherCourse teacherCourse = new TeacherCourse();

	@Autowired
	TeacherCourseService teacherCourseService;

	//跳转到teacher.jsp
	@RequestMapping(value = "/teacher", method = RequestMethod.GET)
	public String list(Model model)
	{
		List<Semester> listSemester = teacherCourseService.getSemester();
		model.addAttribute("listSemester", listSemester);

		List<Teacher> listTeacher = teacherCourseService.getTeacher();
		model.addAttribute("listTeacher", listTeacher);

		return "teacher";
	}

	//TeacherKBFB
	@RequestMapping(value = "/TeacherKBFB", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String, List> teacherKBFB()
	{
		Map<String, List> mapResult = new HashMap<>();
		//Map<String, List> map = new HashMap<String, List>();
		try
		{
			//获取学期
			List<Semester> listSemester = teacherCourseService.getSemester();
			//添加学期到data中
			mapResult.put("Semester", listSemester);
			//获取教师
			List<Teacher> listTeacher = teacherCourseService.getTeacher();
			//将教师添加到data中
			mapResult.put("Teacher", listTeacher);
			//resultSemester = new TeacherResult<Map<String, List>>(true, map);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//resultSemester = new TeacherResult<Map<String, List>>(false, e.getMessage());
		}

		return mapResult;
	}

	//课表内容
	@SuppressWarnings("null")
	@RequestMapping(value = "/TeacherKBFB_table/{sid}/{tnum}/{code}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String, List> GetTeacherCourse(@PathVariable("sid") String sid,
			@PathVariable("tnum") String tnum, @PathVariable("code") String code)
	{
		Map<String, List> resultCourse = new HashMap<>();
		try
		{
			List<CourseTable> listCourseTable = new ArrayList<>();
			listCourseTable = teacherCourseService.getCourseTable(sid, tnum, code);
			resultCourse.put("TeacherCourse", listCourseTable);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		//System.out.println("****************888888");
		return resultCourse;
	}

	@RequestMapping(value = "/get_teimage", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String, Object> getImage(HttpServletResponse response)
	{
		Map<String, Object> map = new HashMap<>();
		BufferedImage image = teacherCourseService.getImage();
		byte b[] = new byte[1024];
		response.setContentType("image/jpg");
		try
		{
			OutputStream sos = response.getOutputStream();
			ImageIO.write(image, "jpg", sos);
			b = ((ByteArrayOutputStream) sos).toByteArray();
			sos.close();

			//新建流。
			//ByteArrayOutputStream os = new ByteArrayOutputStream();
			//利用ImageIO类提供的write方法，将image以jpg图片的数据模式写入流。
			//ImageIO.write(image, "jpg", os);
			//从流中获取数据数组。
		}
		catch (Exception e)
		{

		}

		map.put("img", b);
		System.out.println("map:" + map);
		return map;
	}

	@RequestMapping(value = "/getImgByte", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String, Object> getImageIn()
	{
		Map<String, Object> map = new HashMap<>();
		BufferedImage image = teacherCourseService.getImage();
		byte b[] = new byte[1024];
		
		try
		{
			ByteArrayOutputStream sos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", sos);
			b = ((ByteArrayOutputStream) sos).toByteArray();
			sos.close();

		}
		catch (Exception e)
		{

		}

		map.put("img", b);
		System.out.println("map:" + map);
		return map;
	}

}
