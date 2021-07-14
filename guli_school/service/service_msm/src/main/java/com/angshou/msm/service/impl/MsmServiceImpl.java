package com.angshou.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.angshou.msm.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.rmi.ServerException;
import java.util.Map;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-06 16:58
 * @description
 */

@Service
public class MsmServiceImpl implements MsmService {


	@Override
	public boolean sendMsm(Map<String, Object> param, String phone) {


		if (StringUtils.isEmpty(phone)) return false;

		DefaultProfile profile =
				DefaultProfile.getProfile("default", "LTAI5tGiX2s6NxQAJFCqcM3B", "N4gGZT4IQIo6w4OlCaB7sk32XsyV3G");
		IAcsClient client = new DefaultAcsClient(profile);

		// 固定参数
		CommonRequest request = new CommonRequest();
		//request.setProtocol(ProtocolType.HTTPS);
		request.setMethod(MethodType.POST);
		request.setDomain("dysmsapi.aliyuncs.com");
		request.setVersion("2017-05-25");
		request.setAction("SendSms");


		request.putQueryParameter("PhoneNumbers", phone);
		request.putQueryParameter("SignName", "我的谷粒在线教育网站");
		request.putQueryParameter("TemplateCode", "SMS_180051135");
		request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

		try {
			CommonResponse response = client.getCommonResponse(request);
			System.out.println(response.getData());
			return response.getHttpResponse().isSuccess();
		} catch (ClientException e) {
			e.printStackTrace();
		}


		return false;
	}


}
