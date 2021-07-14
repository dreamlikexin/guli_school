package com.angshou.eduservice.client;

import com.angshou.commonutils.Result;
import org.springframework.stereotype.Component;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-04 17:31
 * @description
 */

@Component
public class VodFileDegradeFeignClient implements VodClient {

	// 出错后执行
	@Override
	public Result removeAliyunVideo(String id) {
		return Result.error().message("熔断机制，删除阿里云视频出错了");
	}

}
