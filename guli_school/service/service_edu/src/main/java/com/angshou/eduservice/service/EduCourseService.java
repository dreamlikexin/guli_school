package com.angshou.eduservice.service;

import com.angshou.eduservice.entity.EduCourse;
import com.angshou.eduservice.entity.frontvo.CourseFrontVo;
import com.angshou.eduservice.entity.frontvo.CourseWebVo;
import com.angshou.eduservice.entity.vo.CourseInfoVo;
import com.angshou.eduservice.entity.vo.CoursePublicVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author angshou
 * @since 2021-07-01
 */
public interface EduCourseService extends IService<EduCourse> {

	String saveCourseInfo(CourseInfoVo courseInfoVo);

	CourseInfoVo getCourseInfo(String courseId);

	void updateCourseInfo(CourseInfoVo courseInfoVo);

	CoursePublicVo publishCourseInfo(String courseId);

	void removeCourse(String courseId);

	Map<String, Object> getCourseFrontList(Page<EduCourse> coursePage, CourseFrontVo courseFrontVo);

	CourseWebVo getBaseCourseInfo(String courseId);
}
