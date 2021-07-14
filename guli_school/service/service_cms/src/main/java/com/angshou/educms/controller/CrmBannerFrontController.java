package com.angshou.educms.controller;


import com.angshou.commonutils.Result;
import com.angshou.educms.entity.CrmBanner;
import com.angshou.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author angshou
 * @since 2021-07-05
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class CrmBannerFrontController {

	@Autowired
	private CrmBannerService crmBannerService;


	@GetMapping("getAllBanner")
	public Result getAllBanner() {
		List<CrmBanner> list = crmBannerService.selectAllBanner();

		return Result.ok().data("list", list);
	}


}

