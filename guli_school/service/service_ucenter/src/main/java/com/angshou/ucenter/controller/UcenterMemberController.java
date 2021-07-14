package com.angshou.ucenter.controller;


import com.angshou.commonutils.JwtUtils;
import com.angshou.commonutils.Result;
import com.angshou.commonutils.ordervo.UcenterMemberOrder;
import com.angshou.ucenter.entity.RegisterVo;
import com.angshou.ucenter.entity.UcenterMember;
import com.angshou.ucenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author angshou
 * @since 2021-07-06
 */
@RestController
@RequestMapping("/eduucenter/member")
@CrossOrigin
public class UcenterMemberController {

	@Autowired
	private UcenterMemberService ucenterMemberService;


	// 登录
	@PostMapping("login")
	public Result loginUser(@RequestBody UcenterMember member) {

		String token = ucenterMemberService.login(member);
		return Result.ok().data("token", token);
	}


	// 注册
	@PostMapping("register")
	public Result registerUser(@RequestBody RegisterVo registerVo) {

		ucenterMemberService.register(registerVo);

		return Result.ok();
	}


	// 根据 token 获取用户名
	@GetMapping("getMemberInfo")
	public Result getMemberInfo(HttpServletRequest request) {

		// 根据 jwt 工具类的方法。获取用户id
		String id = JwtUtils.getMemberIdByJwtToken(request);

		UcenterMember member = ucenterMemberService.getById(id);
		return Result.ok().data("userInfo", member);
	}


	//根据用户id 获取用户信息
	@GetMapping("getUcenterInfo/{memberId}")
	public UcenterMemberOrder getInfo(@PathVariable String memberId) {
		//根据用户id获取用户信息
		UcenterMember ucenterMember = ucenterMemberService.getById(memberId);
		//把member对象里面值复制给UcenterMemberOrder对象
		UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
		BeanUtils.copyProperties(ucenterMember, ucenterMemberOrder);
		return ucenterMemberOrder;
	}


	// 查询某一天的注册人数
	@GetMapping("countRegister/{day}")
	public Result countRegister(@PathVariable String day) {

		Integer count = ucenterMemberService.countRegister(day);
		return Result.ok().data("countRegister", count);
	}


}

