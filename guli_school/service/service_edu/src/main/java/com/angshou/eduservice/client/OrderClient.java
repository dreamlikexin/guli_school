package com.angshou.eduservice.client;

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
@FeignClient(name = "service-order", fallback = OrderDegradeFeignClient.class)
public interface OrderClient {


	// 根据课程id 和 用户id 查询订单表中订单状态
	@GetMapping("/eduorder/order/isBuyCourse/{courseId}/{memberId}")
	public boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);


}
