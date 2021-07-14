package com.angshou.ucenter.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-06 20:56
 * @description
 */


@Data
@ApiModel(value="注册对象", description="注册对象")
public class RegisterVo {

	@ApiModelProperty(value = "昵称")
	private String nickname;

	@ApiModelProperty(value = "手机号")
	private String mobile;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "验证码")
	private String code;
}
