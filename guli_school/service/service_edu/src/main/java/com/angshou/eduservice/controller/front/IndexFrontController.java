package com.angshou.eduservice.controller.front;

import com.angshou.commonutils.Result;
import com.angshou.eduservice.entity.EduCourse;
import com.angshou.eduservice.entity.EduTeacher;
import com.angshou.eduservice.service.EduCourseService;
import com.angshou.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-05 17:00
 * @description
 */


@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {


	@Autowired
	private EduCourseService eduCourseService;

	@Autowired
	private EduTeacherService eduTeacherService;


	// 查询前8 热门课程、前4 老师
	@GetMapping("index")
	public Result index() {

		QueryWrapper<EduCourse> wrapper1 = new QueryWrapper<>();
		wrapper1.orderByDesc("id");
		wrapper1.last("limit 8");
		List<EduCourse> eduCourses = eduCourseService.list(wrapper1);


		QueryWrapper<EduTeacher> wrapper2 = new QueryWrapper<>();
		wrapper2.orderByDesc("id");
		wrapper2.last("limit 4");
		List<EduTeacher> eduTeachers = eduTeacherService.list(wrapper2);


		return Result.ok().data("eduList", eduCourses).data("teacherList", eduTeachers);
	}


}
