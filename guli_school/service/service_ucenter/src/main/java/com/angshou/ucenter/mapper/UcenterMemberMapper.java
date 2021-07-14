package com.angshou.ucenter.mapper;

import com.angshou.ucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author angshou
 * @since 2021-07-06
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

	Integer countRegister(String day);
}
