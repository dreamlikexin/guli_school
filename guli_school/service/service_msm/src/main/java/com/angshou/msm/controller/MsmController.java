package com.angshou.msm.controller;

import com.angshou.commonutils.RandomUtil;
import com.angshou.commonutils.Result;
import com.angshou.msm.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-06 16:57
 * @description
 */


@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {

	@Autowired
	private MsmService msmService;

	// SpringBoot 封装的
	@Autowired
	private RedisTemplate<String, String> redisTemplate;


	// 发送短信的方法
	@GetMapping("send/{phone}")
	public Result sendMsm(@PathVariable String phone) {

		// 1、
		String code = redisTemplate.opsForValue().get(phone);
		if (!StringUtils.isEmpty(code)) {
			return Result.ok().data("code",code);
		}


		// 2、

		String sixBitRandom = RandomUtil.getSixBitRandom();
		Map<String, Object> param = new HashMap<>();
		param.put("code", sixBitRandom);
		boolean isSend = msmService.sendMsm(param, phone);
		if (isSend) {

			redisTemplate.opsForValue().set(phone, sixBitRandom, 5, TimeUnit.MINUTES);


			return Result.ok().data("code",sixBitRandom);

		} else {
			return Result.error().message("短信发送失败！");
		}

	}

}
