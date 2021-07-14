package com.angshou.eduservice.service.impl;

import com.angshou.eduservice.entity.EduChapter;
import com.angshou.eduservice.entity.EduVideo;
import com.angshou.eduservice.entity.chapter.ChapterVo;
import com.angshou.eduservice.entity.chapter.VideoVo;
import com.angshou.eduservice.mapper.EduChapterMapper;
import com.angshou.eduservice.service.EduChapterService;
import com.angshou.eduservice.service.EduVideoService;
import com.angshou.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author angshou
 * @since 2021-07-01
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

	@Autowired
	private EduVideoService eduVideoService;


	@Override
	public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

		QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
		wrapperChapter.eq("course_id", courseId);
		List<EduChapter> eduChapters = baseMapper.selectList(wrapperChapter);

		QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
		wrapperVideo.eq("course_id", courseId);
		List<EduVideo> eduVideos = eduVideoService.list(wrapperVideo);


		List<ChapterVo> finallyChapterVideo = new ArrayList<>();
		for (EduChapter chapter : eduChapters) {
			ChapterVo chapterVo = new ChapterVo();
			BeanUtils.copyProperties(chapter, chapterVo);

			List<VideoVo> videoVoList = new ArrayList<>();
			for (EduVideo video : eduVideos) {
				if (video.getChapterId().equals(chapter.getId())) {
					VideoVo videoVo = new VideoVo();
					BeanUtils.copyProperties(video, videoVo);
					videoVoList.add(videoVo);
				}
			}

			chapterVo.setChildren(videoVoList);
			finallyChapterVideo.add(chapterVo);
		}

		return finallyChapterVideo;
	}


	@Override
	public boolean deleteChapter(String chapterId) {
		QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
		wrapper.eq("chapter_id", chapterId);

		int count = eduVideoService.count(wrapper);
		if (count > 0) {
			throw new GuliException(20001, "存在子节点，无法删除");
		} else {
			int delete = baseMapper.deleteById(chapterId);
			return delete > 0;
		}
	}

	@Override
	public void removeChapterByCourseId(String courseId) {
		QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
		wrapper.eq("course_id", courseId);
		baseMapper.delete(wrapper);
	}


}
