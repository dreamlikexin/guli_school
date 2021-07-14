package com.angshou.ucenter.service.impl;

import com.angshou.commonutils.JwtUtils;
import com.angshou.commonutils.MD5;
import com.angshou.servicebase.exceptionhandler.GuliException;
import com.angshou.ucenter.entity.RegisterVo;
import com.angshou.ucenter.entity.UcenterMember;
import com.angshou.ucenter.mapper.UcenterMemberMapper;
import com.angshou.ucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author angshou
 * @since 2021-07-06
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;


	@Override
	public String login(UcenterMember member) {

		// 非空
		String password = member.getPassword();
		String mobile = member.getMobile();
		if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
			throw new GuliException(20001, "账户和密码不能空白");
		}

		// 手机号
		QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
		wrapper.eq("mobile", mobile);
		UcenterMember mobileMember = baseMapper.selectOne(wrapper);
		if (mobileMember == null) {
			throw new GuliException(20001, "手机号不存在");
		}

		// 密码
		String encryptPassword = MD5.encrypt(password);
		if (!encryptPassword.equals(mobileMember.getPassword())) {
			throw new GuliException(20001, "密码错误");
		}

		if (mobileMember.getIsDisabled()) {
			throw new GuliException(20001, "该用户已被禁用");
		}


		String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());


		return jwtToken;
	}


	@Override
	public void register(RegisterVo registerVo) {

		String code = registerVo.getCode();
		String mobile = registerVo.getMobile();
		String nickname = registerVo.getNickname();
		String password = registerVo.getPassword();

		// 非空
		if (StringUtils.isEmpty(code) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password)) {
			throw new GuliException(20001, "注册信息不能空白");
		}

		// 验证码
		String redisCode = redisTemplate.opsForValue().get(mobile);
		if (!code.equals(redisCode)) {
			throw new GuliException(20001, "验证码验证失败");
		}

		// 手机号是否重复
		QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
		wrapper.eq("mobile", mobile);
		Integer count = baseMapper.selectCount(wrapper);
		if (count > 0) {
			throw new GuliException(20001, "手机号已被注册，请直接登录");
		}

		// 添加
		UcenterMember ucenterMember = new UcenterMember();
		ucenterMember.setMobile(mobile);
		ucenterMember.setNickname(nickname);
		ucenterMember.setPassword(MD5.encrypt(password));
		ucenterMember.setIsDisabled(false); // 用户不禁用
		ucenterMember.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");

		int insert = baseMapper.insert(ucenterMember);
		if (insert == 0) {
			throw new GuliException(20001, "注册失败（添加）");
		}


	}

	@Override
	public UcenterMember getOpenIdMember(String openid) {

		QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
		wrapper.eq("openid",openid);
		UcenterMember member = baseMapper.selectOne(wrapper);

		return member;
	}

	@Override
	public Integer countRegister(String day) {

		Integer count = baseMapper.countRegister(day);
		return count;
	}
}
