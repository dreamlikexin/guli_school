package com.angshou.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.angshou.commonutils.Result;
import com.angshou.servicebase.exceptionhandler.GuliException;
import com.angshou.vod.service.VodService;
import com.angshou.vod.utils.ConstantVodUtils;
import com.angshou.vod.utils.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-03 18:25
 * @description
 */

@RestController
@RequestMapping("eduvod/video")
@CrossOrigin
public class VodController {

	@Autowired
	private VodService vodService;

	// 上传视频到阿里云
	@PostMapping("uploadAliyunVideo")
	public Result uploadAliyunVideo(MultipartFile file) {
		String videoId = vodService.uploadAliyunVideo(file);
		return Result.ok().data("videoId", videoId);
	}


	// 根据小节id 删除阿里云视频
	@DeleteMapping("removeAliyunVideo/{id}")
	public Result removeAliyunVideo(@PathVariable String id) {

		vodService.removeAliyunVideo(id);
		return Result.ok();
	}

	// 获取视频播放凭证
	@GetMapping("getPlayAuth/{id}")
	public Result getPlayAuth(@PathVariable String id) {

		try {
			DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);

			GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
			request.setVideoId(id);

			GetVideoPlayAuthResponse acsResponse = client.getAcsResponse(request);
			String playAuth = acsResponse.getPlayAuth();

			return Result.ok().data("playAuth", playAuth);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuliException(20001, "获取视频播放凭证失败！");
		}

	}

}
