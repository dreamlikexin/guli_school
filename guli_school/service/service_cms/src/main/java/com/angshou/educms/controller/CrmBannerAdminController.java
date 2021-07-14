package com.angshou.educms.controller;


import com.angshou.commonutils.Result;
import com.angshou.educms.entity.CrmBanner;
import com.angshou.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author angshou
 * @since 2021-07-05
 */
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class CrmBannerAdminController {

	@Autowired
	private CrmBannerService crmBannerService;


	// 分页查询
	@GetMapping("pageBanner/{page}/{limit}")
	public Result pageBanner(@PathVariable int page, @PathVariable int limit) {
		Page<CrmBanner> bannerPage = new Page<>(page, limit);
		crmBannerService.page(bannerPage, null);

		return Result.ok().data("items", bannerPage.getRecords()).data("total", bannerPage.getTotal());
	}


	@ApiOperation(value = "获取Banner")
	@GetMapping("get/{id}")
	public Result get(@PathVariable String id) {
		CrmBanner banner = crmBannerService.getById(id);
		return Result.ok().data("item", banner);
	}


	@ApiOperation(value = "新增Banner")
	@PostMapping("save")
	public Result save(@RequestBody CrmBanner banner) {
		crmBannerService.save(banner);
		return Result.ok();
	}

	@ApiOperation(value = "修改Banner")
	@PutMapping("update")
	public Result updateById(@RequestBody CrmBanner banner) {
		crmBannerService.updateById(banner);
		return Result.ok();
	}

	@ApiOperation(value = "删除Banner")
	@DeleteMapping("remove/{id}")
	public Result remove(@PathVariable String id) {
		crmBannerService.removeById(id);
		return Result.ok();
	}


}

