package com.angshou.eduservice.controller;


import com.angshou.commonutils.Result;
import com.angshou.eduservice.service.EduSubjectService;
import com.angshou.eduservice.entity.subject.OneSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author angshou
 * @since 2021-07-01
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {


	@Autowired
	private EduSubjectService eduSubjectService;


	// 添加课程分类
	@PostMapping("addSubject")
	public Result addSubject(MultipartFile file){

		eduSubjectService.saveSubject(file,eduSubjectService);
		return Result.ok();
	}


	// 课程分类列表（树形）
	@GetMapping("getAllSubject")
	public Result getAllSubject(){

		List<OneSubject> list = eduSubjectService.getAllOneTwoSubject();
		return Result.ok().data("list",list);
	}




}

