package com.angshou.statistics.client;

import com.angshou.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-12 20:35
 * @description
 */


@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

	// 查询某一天的注册人数
	@GetMapping("/eduucenter/member/countRegister/{day}")
	public Result countRegister(@PathVariable("day") String day);
}
