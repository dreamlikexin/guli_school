package com.angshou.eduservice.service.impl;

import com.angshou.eduservice.client.VodClient;
import com.angshou.eduservice.entity.EduChapter;
import com.angshou.eduservice.entity.EduCourse;
import com.angshou.eduservice.entity.EduCourseDescription;
import com.angshou.eduservice.entity.frontvo.CourseFrontVo;
import com.angshou.eduservice.entity.frontvo.CourseWebVo;
import com.angshou.eduservice.entity.vo.CourseInfoVo;
import com.angshou.eduservice.entity.vo.CoursePublicVo;
import com.angshou.eduservice.mapper.EduCourseMapper;
import com.angshou.eduservice.service.EduChapterService;
import com.angshou.eduservice.service.EduCourseDescriptionService;
import com.angshou.eduservice.service.EduCourseService;
import com.angshou.eduservice.service.EduVideoService;
import com.angshou.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author angshou
 * @since 2021-07-01
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

	@Autowired
	private EduCourseDescriptionService eduCourseDescriptionService;

	// 删除
	@Autowired
	private EduChapterService eduChapterService;
	@Autowired
	private EduVideoService eduVideoService;
	// @Autowired
	// private VodClient vodClient;


	@Override
	public String saveCourseInfo(CourseInfoVo courseInfoVo) {

		EduCourse eduCourse = new EduCourse();
		BeanUtils.copyProperties(courseInfoVo, eduCourse);
		int insert = baseMapper.insert(eduCourse);
		if (insert == 0) {
			throw new GuliException(20001, "添加课程信息失败");
		}

		EduCourseDescription eduCourseDescription = new EduCourseDescription();
		// 修改 1--1 关系
		eduCourseDescription.setId(eduCourse.getId());
		eduCourseDescription.setDescription(courseInfoVo.getDescription());
		eduCourseDescriptionService.save(eduCourseDescription);

		return eduCourse.getId();
	}


	@Override
	public CourseInfoVo getCourseInfo(String courseId) {
		CourseInfoVo courseInfoVo = new CourseInfoVo();

		EduCourse eduCourse = baseMapper.selectById(courseId);
		BeanUtils.copyProperties(eduCourse, courseInfoVo);
		EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);
		BeanUtils.copyProperties(eduCourseDescription, courseInfoVo);

		return courseInfoVo;
	}


	@Override
	public void updateCourseInfo(CourseInfoVo courseInfoVo) {
		EduCourse eduCourse = new EduCourse();
		BeanUtils.copyProperties(courseInfoVo, eduCourse);
		int update = baseMapper.updateById(eduCourse);
		if (update == 0) {
			throw new GuliException(20001, "修改课程信息失败");
		}

		EduCourseDescription eduCourseDescription = new EduCourseDescription();
		BeanUtils.copyProperties(courseInfoVo, eduCourseDescription);
		boolean update1 = eduCourseDescriptionService.updateById(eduCourseDescription);
		if (!update1) {
			throw new GuliException(20001, "修改课程详情信息失败");
		}


	}

	@Override
	public CoursePublicVo publishCourseInfo(String courseId) {

		CoursePublicVo publishCourseInfo = baseMapper.getPublishCourseInfo(courseId);

		return publishCourseInfo;
	}


	@Override
	public void removeCourse(String courseId) {


		// 删除小节（删除视频）
		eduVideoService.removeVideoByCourseId(courseId);

		// 删除章节
		eduChapterService.removeChapterByCourseId(courseId);

		// 删除详情
		eduCourseDescriptionService.removeById(courseId);

		// 删除课程
		int delete = baseMapper.deleteById(courseId);
		if (delete == 0) {
			throw new GuliException(20001, "删除课程信息失败");
		}


	}

	@Override
	public Map<String, Object> getCourseFrontList(Page<EduCourse> coursePage, CourseFrontVo courseFrontVo) {

		QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
		if (!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) {   // 一级
			queryWrapper.eq("subject_parent_id", courseFrontVo.getSubjectParentId());
		}

		if (!StringUtils.isEmpty(courseFrontVo.getSubjectId())) {        // 二级
			queryWrapper.eq("subject_id", courseFrontVo.getSubjectId());
		}

		if (!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) {     // 关注度
			queryWrapper.orderByDesc("buy_count");
		}

		if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) {    // 最新
			queryWrapper.orderByDesc("gmt_create");
		}

		if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {       // 价格
			queryWrapper.orderByDesc("price");
		}

		baseMapper.selectPage(coursePage, queryWrapper);

		List<EduCourse> records = coursePage.getRecords();
		long current = coursePage.getCurrent();
		long pages = coursePage.getPages();
		long size = coursePage.getSize();
		long total = coursePage.getTotal();
		boolean hasNext = coursePage.hasNext();
		boolean hasPrevious = coursePage.hasPrevious();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("items", records);
		map.put("current", current);
		map.put("pages", pages);
		map.put("size", size);
		map.put("total", total);
		map.put("hasNext", hasNext);
		map.put("hasPrevious", hasPrevious);


		return map;
	}

	@Override
	public CourseWebVo getBaseCourseInfo(String courseId) {
		return baseMapper.getBaseCourseInfo(courseId);
	}
}
