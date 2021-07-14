package com.angshou.eduservice.controller;


import com.angshou.commonutils.Result;
import com.angshou.eduservice.entity.EduChapter;
import com.angshou.eduservice.entity.chapter.ChapterVo;
import com.angshou.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

	@Autowired
	private EduChapterService eduChapterService;

	// 课程大纲列表（章、节）
	@GetMapping("getChapterVideo/{courseId}")
	public Result getChapterVideo(@PathVariable String courseId) {
		List<ChapterVo> list = eduChapterService.getChapterVideoByCourseId(courseId);
		return Result.ok().data("allChapterVideo", list);
	}

	// 添加章节
	@PostMapping("addChapter")
	public Result addChapter(@RequestBody EduChapter eduChapter) {
		eduChapterService.save(eduChapter);
		return Result.ok();
	}


	// 根据章节id 查询
	@GetMapping("getChapterInfo/{chapterId}")
	public Result getChapterInfo(@PathVariable String chapterId) {
		EduChapter eduChapter = eduChapterService.getById(chapterId);
		return Result.ok().data("chapter", eduChapter);
	}

	// 修改章节
	@PostMapping("updateChapter")
	public Result updateChapter(@RequestBody EduChapter eduChapter) {
		eduChapterService.updateById(eduChapter);
		return Result.ok();
	}


	// 删除章节
	@DeleteMapping("{chapterId}")
	public Result updateChapter(@PathVariable String chapterId) {
		boolean flag = eduChapterService.deleteChapter(chapterId);
		if(flag){
			return Result.ok();
		}else{
			return Result.error();
		}

	}


}

