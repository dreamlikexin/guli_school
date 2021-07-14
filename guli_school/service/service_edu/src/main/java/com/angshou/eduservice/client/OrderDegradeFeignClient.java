package com.angshou.eduservice.client;

import com.angshou.commonutils.ordervo.UcenterMemberOrder;
import com.angshou.servicebase.exceptionhandler.GuliException;
import org.springframework.stereotype.Component;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-04 17:31
 * @description
 */

@Component
public class OrderDegradeFeignClient implements OrderClient {


	@Override
	public boolean isBuyCourse(String courseId, String memberId) {
		throw new GuliException(20001, "熔断机制，获取订单信息发送异常！");
	}
}
