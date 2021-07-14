package com.angshou.eduservice.service;

import com.angshou.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author angshou
 * @since 2021-07-01
 */
public interface EduVideoService extends IService<EduVideo> {

	void removeVideoByCourseId(String courseId);
}
