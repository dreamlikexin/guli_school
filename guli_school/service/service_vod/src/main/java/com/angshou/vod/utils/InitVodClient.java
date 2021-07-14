package com.angshou.vod.utils;

import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-03 20:32
 * @description
 */
public class InitVodClient {

	public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
		String regionId = "cn-shanghai";  // 点播服务接入区域
		DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
		DefaultAcsClient client = new DefaultAcsClient(profile);
		return client;
	}

}
