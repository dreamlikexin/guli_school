package com.angshou.eduservice.controller.front;

import com.angshou.commonutils.Result;
import com.angshou.eduservice.entity.EduCourse;
import com.angshou.eduservice.entity.EduTeacher;
import com.angshou.eduservice.service.EduCourseService;
import com.angshou.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-11 9:47
 * @description
 */


@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin
public class TeacherFrontController {

	@Autowired
	private EduTeacherService eduTeacherService;


	@Autowired
	private EduCourseService eduCourseService;


	// 分页获取讲师信息
	@PostMapping("getTeacherFrontList/{page}/{limit}")
	public Result getTeacherFrontList(@PathVariable long page, @PathVariable long limit) {

		Page<EduTeacher> teacherPage = new Page<>(page, limit);
		Map<String, Object> map = eduTeacherService.getTeacherFrontList(teacherPage);

		// 返回分页数据
		return Result.ok().data(map);
	}


	// 讲师详情功能
	@GetMapping("getTeacherFrontInfo/{teacherId}")
	public Result getTeacherFrontInfo(@PathVariable String teacherId) {

		// 查询讲师基本信息
		EduTeacher eduTeacher = eduTeacherService.getById(teacherId);

		// 所讲课程信息
		QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
		wrapper.eq("teacher_id", teacherId);

		List<EduCourse> eduCourses = eduCourseService.list(wrapper);


		return Result.ok().data("teacher", eduTeacher).data("courseList", eduCourses);
	}

}
