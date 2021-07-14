package com.angshou.eduservice.controller;


import com.angshou.commonutils.JwtUtils;
import com.angshou.commonutils.Result;
import com.angshou.commonutils.ordervo.UcenterMemberOrder;
import com.angshou.eduservice.client.UcenterClient;
import com.angshou.eduservice.entity.EduComment;
import com.angshou.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author angshou
 * @since 2021-07-11
 */
@RestController
@RequestMapping("/eduservice/comment")
@CrossOrigin
public class EduCommentController {

	@Autowired
	private EduCommentService eduCommentService;

	@Autowired
	private UcenterClient ucenterClient;


	// 分页查询
	@GetMapping("pageListCommont/{page}/{limit}")
	public Result pageListCommont(@PathVariable long page, @PathVariable long limit, String courseId) {
		QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
		wrapper.orderByDesc("gmt_create");
		wrapper.eq("course_id", courseId);

		Page<EduComment> commentPage = new Page<>(page, limit);
		eduCommentService.page(commentPage, wrapper);

		Map<String, Object> map = new HashMap<>();
		map.put("items", commentPage.getRecords());
		map.put("current", commentPage.getCurrent());
		map.put("pages", commentPage.getPages());
		map.put("size", commentPage.getSize());
		map.put("total", commentPage.getTotal());
		map.put("hasNext", commentPage.hasNext());
		map.put("hasPrevious", commentPage.hasPrevious());

		return Result.ok().data(map);
	}

	// 添加评论
	@PostMapping("addCommont/save")
	public Result save(@RequestBody EduComment comment, HttpServletRequest request) {
		String memberId = JwtUtils.getMemberIdByJwtToken(request);
		if(StringUtils.isEmpty(memberId)) {
			return Result.error().code(28004).message("请先登录系统！");
		}
		comment.setMemberId(memberId);

		UcenterMemberOrder ucenterInfo = ucenterClient.getUcenterInfo(memberId);

		comment.setNickname(ucenterInfo.getNickname());
		comment.setAvatar(ucenterInfo.getAvatar());

		eduCommentService.save(comment);

		return Result.ok();
	}




}

