package com.angshou.eduorder.controller;


import com.angshou.commonutils.JwtUtils;
import com.angshou.commonutils.Result;
import com.angshou.eduorder.entity.TOrder;
import com.angshou.eduorder.service.TOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author angshou
 * @since 2021-07-12
 */
@RestController
@RequestMapping("/eduorder/order")
@CrossOrigin
public class TOrderController {

	@Autowired
	private TOrderService tOrderService;

	// 生成订单的方法
	@PostMapping("createOrder/{courseId}")
	public Result saveOrder(@PathVariable String courseId, HttpServletRequest request) {
		String memberId = JwtUtils.getMemberIdByJwtToken(request);
		String orderNo = tOrderService.createOrder(courseId, memberId);

		return Result.ok().data("orderId", orderNo);
	}


	// 根据订单id 查询订单信息__order_no
	@GetMapping("getOrderInfo/{orderId}")
	public Result getOrderInfo(@PathVariable String orderId) {
		QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
		wrapper.eq("order_no", orderId);
		TOrder order = tOrderService.getOne(wrapper);
		return Result.ok().data("item", order);
	}


	// 根据课程id 和 用户id 查询订单表中订单状态
	@GetMapping("isBuyCourse/{courseId}/{memberId}")
	public boolean isBuyCourse(@PathVariable String courseId, @PathVariable String memberId) {
		QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
		wrapper.eq("course_id", courseId);
		wrapper.eq("member_id", memberId);
		wrapper.eq("status", 1);
		int count = tOrderService.count(wrapper);

		return count > 0 ? true : false;
	}


}

