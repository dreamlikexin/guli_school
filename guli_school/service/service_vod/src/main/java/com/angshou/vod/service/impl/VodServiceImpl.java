package com.angshou.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.angshou.servicebase.exceptionhandler.GuliException;
import com.angshou.vod.service.VodService;
import com.angshou.vod.utils.ConstantVodUtils;
import com.angshou.vod.utils.InitVodClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-03 18:26
 * @description
 */


@Service
public class VodServiceImpl implements VodService {

	@Override
	public String uploadAliyunVideo(MultipartFile file) {


		String fileName = file.getOriginalFilename();
		String title = fileName.substring(0, fileName.lastIndexOf("."));

		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);

		UploadVideoImpl uploader = new UploadVideoImpl();
		UploadStreamResponse response = uploader.uploadStream(request);

		String videoId = "";
		if (response.isSuccess()) {
			videoId = response.getVideoId();
		} else {
			videoId = response.getVideoId();
		}

		return videoId;
	}


	@Override
	public void removeAliyunVideo(String id) {

		try {
			DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);

			DeleteVideoRequest request = new DeleteVideoRequest();
			request.setVideoIds(id);
			client.getAcsResponse(request);


		} catch (ClientException e) {
			e.printStackTrace();
			throw new GuliException(20001, "删除阿里云视频失败！！！");
		}


	}
}
