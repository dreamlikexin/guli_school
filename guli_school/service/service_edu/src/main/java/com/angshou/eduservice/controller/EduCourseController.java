package com.angshou.eduservice.controller;


import com.angshou.commonutils.Result;
import com.angshou.eduservice.entity.EduCourse;
import com.angshou.eduservice.entity.EduTeacher;
import com.angshou.eduservice.entity.vo.CourseInfoVo;
import com.angshou.eduservice.entity.vo.CoursePublicVo;
import com.angshou.eduservice.entity.vo.CourseQuery;
import com.angshou.eduservice.entity.vo.TeacherQuery;
import com.angshou.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author angshou
 * @since 2021-07-01
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

	@Autowired
	private EduCourseService eduCourseService;


	// 分页查询课程信息，注意使用PutMapping
	@PostMapping("pageCourseCondition/{current}/{limit}")
	public Result pageListCourseCondition(@PathVariable long current,
	                                       @PathVariable long limit,
	                                       @RequestBody(required = false) CourseQuery courseQuery) {

		// 创建page 对象
		Page<EduCourse> pageCourse = new Page<>(current, limit);
		// 构建条件
		QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

		String title = courseQuery.getTitle();
		String status = courseQuery.getStatus();
		String subjectParentId = courseQuery.getSubjectParentId();
		String subjectId = courseQuery.getSubjectId();
		// Mybatis 动态 SQL 或者 拼接条件
		if (!StringUtils.isEmpty(title)) {
			wrapper.like("title", title);
		}
		if (!StringUtils.isEmpty(status)) {
			wrapper.eq("status", status);
		}
		if (!StringUtils.isEmpty(subjectParentId)) {
			wrapper.ge("subject_parent_id", subjectParentId);
		}
		if (!StringUtils.isEmpty(subjectId)) {
			wrapper.le("subject_id", subjectId);
		}

		// 排序
		wrapper.orderByDesc("gmt_create");

		eduCourseService.page(pageCourse, wrapper);
		List<EduCourse> records = pageCourse.getRecords();
		long total = pageCourse.getTotal();
		return Result.ok().data("total", total).data("rows", records);
	}




	// 添加课程信息
	@PostMapping("addCourseInfo")
	public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {

		String id = eduCourseService.saveCourseInfo(courseInfoVo);

		return Result.ok().data("courseId", id);
	}


	// 根据课程id 查询课程基本信息
	@GetMapping("getCourseInfo/{courseId}")
	public Result getCourseInfo(@PathVariable String courseId) {
		CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
		return Result.ok().data("courseInfoVo", courseInfoVo);
	}


	// 修改课程信息
	@PostMapping("updateCourseInfo")
	public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
		eduCourseService.updateCourseInfo(courseInfoVo);
		return Result.ok();
	}


	// 根据课程id 查询课程确认信息
	@GetMapping("getPublishCourseInfo/{courseId}")
	public Result getPublishCourseInfo(@PathVariable String courseId) {

		CoursePublicVo coursePublicVo = eduCourseService.publishCourseInfo(courseId);

		return Result.ok().data("publishCourse", coursePublicVo);
	}


	// 修改课程信息，课程最终发布
	@PostMapping("publishCourse/{courseId}")
	public Result publishCourse(@PathVariable String courseId) {
		EduCourse eduCourse = new EduCourse();
		eduCourse.setId(courseId);
		eduCourse.setStatus("Normal");
		eduCourseService.updateById(eduCourse);
		return Result.ok();
	}


	// 删除课程信息
	@DeleteMapping("{courseId}")
	public Result deleteCourse(@PathVariable String courseId) {
		eduCourseService.removeCourse(courseId);
		return Result.ok();
	}


}

