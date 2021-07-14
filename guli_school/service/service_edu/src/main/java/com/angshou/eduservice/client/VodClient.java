package com.angshou.eduservice.client;

import com.angshou.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-04 16:14
 * @description
 */

@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

	// 定义调用的方法路径

	// 根据小节id 删除阿里云视频
	@DeleteMapping("/eduvod/video/removeAliyunVideo/{id}")
	public Result removeAliyunVideo(@PathVariable("id") String id);

}
