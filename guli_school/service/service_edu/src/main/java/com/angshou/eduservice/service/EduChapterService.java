package com.angshou.eduservice.service;

import com.angshou.eduservice.entity.EduChapter;
import com.angshou.eduservice.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author angshou
 * @since 2021-07-01
 */
public interface EduChapterService extends IService<EduChapter> {

	List<ChapterVo> getChapterVideoByCourseId(String courseId);

	boolean deleteChapter(String chapterId);

	void removeChapterByCourseId(String courseId);
}
