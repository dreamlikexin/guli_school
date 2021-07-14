package com.angshou.eduservice.controller;

import com.angshou.commonutils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author adminPC--昂首灬
 * @date 2021-06-29 19:35
 * @description
 */


@RestController
@RequestMapping("eduservice/user")
@CrossOrigin
public class EduLoginController {

	// login
	@PostMapping("login")
	public Result login(){

		return Result.ok().data("token","admin");
	}


	// info
	@GetMapping("info")
	public Result info(){

		return Result.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");

	}

}
