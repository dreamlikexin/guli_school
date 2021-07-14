package com.angshou.servicebase.exceptionhandler;

import com.angshou.commonutils.ExceptionUtil;
import com.angshou.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author adminPC--昂首灬
 * @date 2021-06-28 20:48
 * @description 统一异常处理
 */

@ControllerAdvice
@Slf4j    // logback 日志
public class GlobalExceptionHandler {

	// 全局异常处理
	@ExceptionHandler(Exception.class)      // 指定出现什么异常执行这个方法
	@ResponseBody
	public Result error(Exception ex){

		ex.printStackTrace();
		return Result.error().message("执行了全局异常处理...");
	}


	// 特殊异常处理
	@ExceptionHandler(ArithmeticException.class)
	@ResponseBody
	public Result error(ArithmeticException ex){

		ex.printStackTrace();
		return Result.error().message("执行了ArithmeticException 异常处理...");
	}


	// 自定义异常处理
	@ExceptionHandler(GuliException.class)
	@ResponseBody
	public Result error(GuliException ex){

		// log.error(ex.getMsg());
		// 使用工具类写入异常处理
		log.error(ExceptionUtil.getMessage(ex));

		ex.printStackTrace();
		return Result.error().code(ex.getCode()).message(ex.getMsg());
	}


}
