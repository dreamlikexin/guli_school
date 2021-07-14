package com.angshou.eduservice.service.impl;

import com.angshou.eduservice.client.VodClient;
import com.angshou.eduservice.entity.EduVideo;
import com.angshou.eduservice.mapper.EduVideoMapper;
import com.angshou.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author angshou
 * @since 2021-07-01
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

	@Autowired
	private VodClient vodClient;

	// 删除小节，删除对应的视频文件
	@Override
	public void removeVideoByCourseId(String courseId) {
		QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
		wrapper.eq("course_id", courseId);


		List<EduVideo> eduVideos = baseMapper.selectList(wrapper);
		for (EduVideo eduVideo : eduVideos) {
			String videoSourceId = eduVideo.getVideoSourceId();
			if (!StringUtils.isEmpty(videoSourceId)) {
				vodClient.removeAliyunVideo(videoSourceId);
			}
		}

		baseMapper.delete(wrapper);
	}


}
