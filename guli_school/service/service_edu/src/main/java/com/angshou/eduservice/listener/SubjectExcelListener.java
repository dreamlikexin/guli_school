package com.angshou.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.angshou.eduservice.entity.EduSubject;
import com.angshou.eduservice.entity.excel.SubjectData;
import com.angshou.eduservice.service.EduSubjectService;
import com.angshou.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-01 16:46
 * @description
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

	// 不能交给spring管理。不能注入，只能自己new
	public EduSubjectService subjectService;

	public SubjectExcelListener() {
	}

	public SubjectExcelListener(EduSubjectService subjectService) {
		this.subjectService = subjectService;
	}


	// 一行一行读（不读表头）
	@Override
	public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

		if(subjectData == null) {
			throw new GuliException(20001,"添加失败");
		}
		//添加一级分类
		EduSubject existOneSubject = this.existOneSubject(subjectService,subjectData.getOneSubjectName());
		if(existOneSubject == null) {//没有相同的
			existOneSubject = new EduSubject();
			existOneSubject.setTitle(subjectData.getOneSubjectName());
			existOneSubject.setParentId("0");
			subjectService.save(existOneSubject);
		}

		//获取一级分类id值
		String pid = existOneSubject.getId();

		//添加二级分类
		EduSubject existTwoSubject = this.existTwoSubject(subjectService,subjectData.getTwoSubjectName(), pid);
		if(existTwoSubject == null) {
			existTwoSubject = new EduSubject();
			existTwoSubject.setTitle(subjectData.getTwoSubjectName());
			existTwoSubject.setParentId(pid);
			subjectService.save(existTwoSubject);
		}

	}

	// 最后做的事情
	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {

	}


	//判断一级分类是否重复
	private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
		QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
		wrapper.eq("title", name);
		wrapper.eq("parent_id", "0");
		EduSubject subjectData = subjectService.getOne(wrapper);
		return subjectData;
	}

	//判断二级分类是否重复
	private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
		QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
		wrapper.eq("title", name);
		wrapper.eq("parent_id", pid);
		EduSubject subjectData = subjectService.getOne(wrapper);
		return subjectData;
	}


}
