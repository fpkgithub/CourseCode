package com.course.web;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.course.entity.Campus;
import com.course.entity.OptionalCourseTable;
import com.course.entity.Semester;
import com.course.service.AnyCourseService;

/**
 * @author a boy
 * 
 */

@Controller
@RequestMapping("/any")
public class AnyCourseController
{

	@Autowired
	AnyCourseService anyCourseService;

	//跳转到any.jsp
	@RequestMapping(value = "/anystart", method = RequestMethod.GET)
	public String list(Model model)
	{
		List<Semester> listSemester = anyCourseService.getSemester();
		//System.out.println("listSemester:" + listSemester);
		model.addAttribute("listSemester", listSemester);

		List<Campus> listCampus = anyCourseService.getCampus();
		//System.out.println("listCampus:" + listCampus);
		model.addAttribute("listCampus", listCampus);

		return "any";
	}

	@RequestMapping(value = "/getStart", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String, List> getStart()
	{
		Map<String, List> mapResult = new HashMap<>();
		List<Semester> listSemester = anyCourseService.getSemester();
		mapResult.put("listSemester", listSemester);
		List<Campus> listCampus = anyCourseService.getCampus();
		mapResult.put("listCampus", listCampus);
		return mapResult;
	}

	//验证码
	@RequestMapping(value = "/getAnyImgage", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public void getImage(HttpServletResponse response)
	{
		Map<String, Object> map = new HashMap<>();
		BufferedImage image = anyCourseService.getImage();

		response.setContentType("image/jpg");
		try
		{
			OutputStream sos = response.getOutputStream();
			ImageIO.write(image, "jpg", sos);
			sos.close();

		}
		catch (Exception e)
		{

		}

	}

	//获取课程
	@RequestMapping(value = "/anyCourse/{sid}/{canum}/{code}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String, List> GetCourseByAny(@PathVariable("sid") String sid,
			@PathVariable("canum") String canum, @PathVariable("code") String code)
	{

		Map<String, List> resultCourse = new HashMap<>();
		try
		{
			List<OptionalCourseTable> listCourseTable = new ArrayList<>();
			listCourseTable = anyCourseService.getCourse(sid, canum, code);
			resultCourse.put("AnyCourse", listCourseTable);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return resultCourse;
	}

}
