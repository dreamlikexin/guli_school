package com.angshou.educms.service.impl;

import com.angshou.educms.entity.CrmBanner;
import com.angshou.educms.mapper.CrmBannerMapper;
import com.angshou.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author angshou
 * @since 2021-07-05
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

	@Cacheable(key = "'selectIndexList'", value = "banner")
	@Override
	public List<CrmBanner> selectAllBanner() {
		QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
		wrapper.orderByDesc("id");
		wrapper.last("limit 3");

		List<CrmBanner> bannerList = baseMapper.selectList(wrapper);

		return bannerList;
	}
}
