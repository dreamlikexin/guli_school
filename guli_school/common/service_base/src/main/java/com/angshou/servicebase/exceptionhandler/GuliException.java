package com.angshou.servicebase.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author adminPC--昂首灬
 * @date 2021-06-29 9:46
 * @description 自定义异常
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException {

	@ApiModelProperty(value = "异常状态码")
	private Integer code;

	private String msg;


	@Override
	public String toString() {
		return "GuliException{" +
				"message=" + this.getMessage() +
				", code=" + code +
				'}';
	}


}
