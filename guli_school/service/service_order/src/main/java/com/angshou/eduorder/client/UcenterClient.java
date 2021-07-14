package com.angshou.eduorder.client;

import com.angshou.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-12 9:55
 * @description
 */


@Component
@FeignClient("service-ucenter")
public interface UcenterClient {


	//根据用户id 获取用户信息
	@GetMapping("/eduucenter/member/getUcenterInfo/{memberId}")
	public UcenterMemberOrder getInfo(@PathVariable("memberId") String memberId);

}
