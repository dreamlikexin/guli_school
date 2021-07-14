package com.angshou.educms.service;

import com.angshou.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author angshou
 * @since 2021-07-05
 */
public interface CrmBannerService extends IService<CrmBanner> {

	List<CrmBanner> selectAllBanner();

}
