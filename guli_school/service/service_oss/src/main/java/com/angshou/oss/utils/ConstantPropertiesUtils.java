package com.angshou.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-01 10:36
 * @description
 */

// InitializingBean 初始化加载
@Component
public class ConstantPropertiesUtils  implements InitializingBean {

	@Value("${aliyun.oss.file.endpoint}")
	private String endpoint;

	@Value("${aliyun.oss.file.keyid}")
	private String keyId;

	@Value("${aliyun.oss.file.keysecret}")
	private String keySecret;

	@Value("${aliyun.oss.file.bucketname}")
	private String bucketName;

	public static String END_POINT;
	public static String ACCESS_KEY_ID;
	public static String ACCESS_KEY_SECRET;
	public static String BUCKET_NAME;

	@Override
	public void afterPropertiesSet() throws Exception {
		END_POINT = endpoint;
		ACCESS_KEY_ID = keyId;
		ACCESS_KEY_SECRET = keySecret;
		BUCKET_NAME = bucketName;
	}

}
