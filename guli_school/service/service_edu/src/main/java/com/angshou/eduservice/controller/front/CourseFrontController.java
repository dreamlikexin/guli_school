package com.angshou.eduservice.controller.front;

import com.angshou.commonutils.JwtUtils;
import com.angshou.commonutils.Result;
import com.angshou.commonutils.ordervo.CourseWebVoOrder;
import com.angshou.eduservice.client.OrderClient;
import com.angshou.eduservice.entity.EduCourse;
import com.angshou.eduservice.entity.EduTeacher;
import com.angshou.eduservice.entity.chapter.ChapterVo;
import com.angshou.eduservice.entity.frontvo.CourseFrontVo;
import com.angshou.eduservice.entity.frontvo.CourseWebVo;
import com.angshou.eduservice.service.EduChapterService;
import com.angshou.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-11 11:16
 * @description
 */

@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {

	@Autowired
	private EduCourseService eduCourseService;


	@Autowired
	private EduChapterService eduChapterService;


	@Autowired
	private OrderClient orderClient;


	// 带条件的分页查询
	@PostMapping("getCourseFrontList/{page}/{limit}")
	public Result getFrontCourseList(@PathVariable long page, @PathVariable long limit,
	                                 @RequestBody(required = false) CourseFrontVo courseFrontVo) {

		Page<EduCourse> coursePage = new Page<>(page, limit);
		Map<String, Object> map = eduCourseService.getCourseFrontList(coursePage, courseFrontVo);

		// 返回分页数据
		return Result.ok().data(map);
	}


	// 课程详情方法
	@GetMapping("getCourseFrontInfo/{courseId}")
	public Result getCourseFrontInfo(@PathVariable String courseId, HttpServletRequest request) {
		// 课程基本信息
		CourseWebVo courseWebVo = eduCourseService.getBaseCourseInfo(courseId);

		// 课程章节、小节
		List<ChapterVo> chapterVideoByCourseId = eduChapterService.getChapterVideoByCourseId(courseId);


		// 新增————根据课程id、会员id 查询课程是否购买
		String memberId = JwtUtils.getMemberIdByJwtToken(request);
		boolean buyCourse = memberId == null ? false : orderClient.isBuyCourse(courseId, memberId);


		return Result.ok().data("courseWebVo", courseWebVo).data("chapterVideoList", chapterVideoByCourseId).data("isBuy", buyCourse);
	}


	// 课程详情方法
	@GetMapping("getCourseInfoInfo/{courseId}")
	public CourseWebVoOrder getCourseInfoInfo(@PathVariable String courseId) {
		// 课程基本信息
		CourseWebVo courseWebVo = eduCourseService.getBaseCourseInfo(courseId);

		CourseWebVoOrder webVoOrder = new CourseWebVoOrder();
		BeanUtils.copyProperties(courseWebVo, webVoOrder);

		return webVoOrder;
	}


}
