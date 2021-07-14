package com.angshou.eduservice.controller;


import com.angshou.commonutils.Result;
import com.angshou.eduservice.client.VodClient;
import com.angshou.eduservice.entity.EduChapter;
import com.angshou.eduservice.entity.EduVideo;
import com.angshou.eduservice.service.EduVideoService;
import com.angshou.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author angshou
 * @since 2021-07-01
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

	@Autowired
	private EduVideoService eduVideoService;

	@Autowired
	private VodClient vodClient;

	// 添加小节
	@PostMapping("addVideo")
	public Result addVideo(@RequestBody EduVideo eduVideo) {
		eduVideoService.save(eduVideo);
		return Result.ok();
	}

	// 根据小节id 查询
	@GetMapping("getVideoInfo/{videoId}")
	public Result getVideoInfo(@PathVariable String videoId) {
		EduVideo eduVideo = eduVideoService.getById(videoId);
		return Result.ok().data("video", eduVideo);
	}


	// 修改小节
	@PostMapping("updateVideo")
	public Result updateVideo(@RequestBody EduVideo eduVideo) {
		eduVideoService.updateById(eduVideo);
		return Result.ok();
	}


	// 删除小节
	// 删除小节的时候，同时把里面的视频删除
	@DeleteMapping("{videoId}")
	public Result updateVideo(@PathVariable String videoId) {

		EduVideo eduVideo = eduVideoService.getById(videoId);
		String videoSourceId = eduVideo.getVideoSourceId();

		// 根据视频id，远程调用实现视频删除
		if (!StringUtils.isEmpty(videoSourceId)) {
			Result result = vodClient.removeAliyunVideo(videoSourceId);
			if (result.getCode() == 20001) {
				throw new GuliException(20001, result.getMessage());
			}
		}

		boolean flag = eduVideoService.removeById(videoId);
		if (flag) {
			return Result.ok();
		} else {
			return Result.error();
		}

	}
}

