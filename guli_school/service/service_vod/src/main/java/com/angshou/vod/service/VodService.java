package com.angshou.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-03 18:26
 * @description
 */
public interface VodService {
	String uploadAliyunVideo(MultipartFile file);

	void removeAliyunVideo(String id);
}
