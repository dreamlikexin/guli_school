package com.angshou.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-03 18:39
 * @description
 */

@Component
public class ConstantVodUtils implements InitializingBean {

	@Value("${aliyun.vod.file.keyid}")
	private String keyid;

	@Value("${aliyun.vod.file.keysecret}")
	private String keysecret;


	public static String ACCESS_KEY_ID;
	public static String ACCESS_KEY_SECRET;

	@Override
	public void afterPropertiesSet() throws Exception {
		ACCESS_KEY_ID = keyid;
		ACCESS_KEY_SECRET = keysecret;
	}


}
