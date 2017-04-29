package com.course.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
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

import com.course.dto.RoomCourse;
import com.course.dto.TeacherCourse;
import com.course.entity.Building;
import com.course.entity.Campus;
import com.course.entity.Classroom;
import com.course.entity.ClassroomCourseTable;
import com.course.entity.Semester;
import com.course.entity.Teacher;
import com.course.service.RoomCourseService;

@Controller
@RequestMapping("/room")
public class RoomCourseController
{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static String cookie;
	RoomCourse roomCourse = new RoomCourse();

	
	//教室课表service
	@Autowired
	RoomCourseService roomCourseService;
	
	//跳转到roomcourse.jsp
		@RequestMapping(value = "/roomcourse", method = RequestMethod.GET)
		public String list(Model model)
		{
			List<Semester> listSemester = roomCourseService.getSemester();
			model.addAttribute("listSemester", listSemester);

			List<Campus> listCampus = roomCourseService.getCampus();
			model.addAttribute("listCampus", listCampus);

			return "roomcourse";
		}

	

	//加载班级课表数据(学期 验证码 cookie 校区)
	@RequestMapping(value = "/KBFB_RoomSel", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String, List> KBFB_RoomSel(String xq, String jxl)
	{
		Map<String, List> mapResult = new HashMap<>();

		try
		{
			List<Campus> listCampus = roomCourseService.getCampus();
			mapResult.put("Campus", listCampus);

			List<Semester> listSemester = roomCourseService.getSemester();
			mapResult.put("Semester", listSemester);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("KBFB_ClassSel()错误...");
		}
		return mapResult;
	}

	//加载班级课表数据(教学楼)
	@RequestMapping(value = "/BuildingInfo/{xq}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String, List> BuildingInfo(@PathVariable("xq") String xq)
	{
		Map<String, List> mapResult = new HashMap<>();

		try
		{
			List<Building> listBuilding = roomCourseService.getBuilding(xq);
			mapResult.put("Building", listBuilding);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("BuildingInfo()错误...");
		}
		System.out.println("*):"+mapResult);
		return mapResult;
	}

	//加载班级课表数据(教室)
	@RequestMapping(value = "/Classroom/{jxl}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String, List> Classroom(@PathVariable("jxl") String jxl)
	{
		Map<String, List> mapResult = new HashMap<>();

		try
		{
			List<Classroom> listClassroom = roomCourseService.getClassroom(jxl);
			mapResult.put("Classroom", listClassroom);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Classroom()错误...");
		}
		return mapResult;
	}

	//加载班级课表数据(课表)
	@RequestMapping(value = "/ClassCourse/{termId}/{xq}/{jxl}/{room}/{code}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String, List> Classroom(@PathVariable("termId") String termId,
			@PathVariable("xq") String xq, @PathVariable("jxl") String jxl,
			@PathVariable("room") String room, @PathVariable("code") String code)
	{
		
		//   /20161/2/210/2100104
		Map<String, List> mapResult = new HashMap<>();

		try
		{
			List<ClassroomCourseTable> listClassroom = roomCourseService.getCourseTable(termId, xq,
					jxl, room, code);
			mapResult.put("Classroom", listClassroom);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Classroom()错误...");
		}
		return mapResult;
	}
	
	

	@RequestMapping(value = "/getRoomImage", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String, Object> getImage(HttpServletResponse response)
	{
		Map<String, Object> map = new HashMap<>();
		BufferedImage image = roomCourseService.getImage();
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
		//System.out.println("map:" + map);
		return map;
	}

	@RequestMapping(value = "/getImgByte", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String, Object> getImageIn()
	{
		Map<String, Object> map = new HashMap<>();
		BufferedImage image = roomCourseService.getImage();
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
		//System.out.println("map:" + map);
		return map;
	}

}
