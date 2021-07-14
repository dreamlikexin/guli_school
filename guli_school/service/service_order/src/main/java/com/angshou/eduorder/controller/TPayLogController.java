package com.angshou.eduorder.controller;


import com.angshou.commonutils.Result;
import com.angshou.eduorder.service.TPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author angshou
 * @since 2021-07-12
 */
@RestController
@RequestMapping("/eduorder/paylog")
@CrossOrigin
public class TPayLogController {

	@Autowired
	private TPayLogService tPayLogService;

	// 生成微信支付二维码
	@GetMapping("createNative/{orderNo}")
	public Result createNative(@PathVariable String orderNo) {

		Map map = tPayLogService.createNative(orderNo);

		return Result.ok().data(map);
	}


	// 查询订单支付状态，更新订单状态、添加记录到支付表
	@GetMapping("queryPayStatus/{orderNo}")
	public Result queryPayStatus(@PathVariable String orderNo) {
		Map map = tPayLogService.queryPayStatus(orderNo);
		if (map == null) {
			return Result.error().message("微信支付出错哦");
		}

		if (map.get("trade_state").equals("SUCCESS")) {
			// 更新订单状态、添加记录到支付表
			tPayLogService.updateOrderStatus(map);

			return Result.ok().message("微信支付成功哦");
		}

		return Result.ok().code(25000).message("微信支付中，嗷嗷嗷");
	}


}

