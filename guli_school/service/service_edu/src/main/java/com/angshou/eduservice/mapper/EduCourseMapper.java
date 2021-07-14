package com.angshou.eduservice.mapper;

import com.angshou.eduservice.entity.EduCourse;
import com.angshou.eduservice.entity.frontvo.CourseWebVo;
import com.angshou.eduservice.entity.vo.CoursePublicVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author angshou
 * @since 2021-07-01
 */


public interface EduCourseMapper extends BaseMapper<EduCourse> {

	public CoursePublicVo getPublishCourseInfo(String courseId);

	CourseWebVo getBaseCourseInfo(String courseId);

}
