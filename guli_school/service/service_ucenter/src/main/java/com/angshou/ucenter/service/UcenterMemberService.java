package com.angshou.ucenter.service;

import com.angshou.ucenter.entity.RegisterVo;
import com.angshou.ucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author angshou
 * @since 2021-07-06
 */
public interface UcenterMemberService extends IService<UcenterMember> {

	String login(UcenterMember member);

	void register(RegisterVo registerVo);

	UcenterMember getOpenIdMember(String openid);

	Integer countRegister(String day);
}
