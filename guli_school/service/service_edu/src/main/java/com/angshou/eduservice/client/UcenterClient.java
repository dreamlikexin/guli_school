package com.angshou.eduservice.client;

import com.angshou.commonutils.Result;
import com.angshou.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-11 20:00
 * @description
 */

@Component
@FeignClient(name="service-ucenter",fallback = UcenterDegradeFeignClient.class)
public interface UcenterClient {


	//根据用户id获取用户信息
	@GetMapping("/eduucenter/member/getUcenterInfo/{memberId}")
	public UcenterMemberOrder getUcenterInfo(@PathVariable("memberId") String memberId);


}
