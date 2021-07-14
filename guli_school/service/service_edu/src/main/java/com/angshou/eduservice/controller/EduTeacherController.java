package com.angshou.eduservice.controller;


import com.angshou.commonutils.Result;
import com.angshou.eduservice.entity.EduTeacher;
import com.angshou.eduservice.entity.vo.TeacherQuery;
import com.angshou.eduservice.service.EduTeacherService;
import com.angshou.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author angshou
 * @since 2021-06-28
 */


@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {
	// http://localhost:8001/eduservice/teacher/findAll

	// 注入 service
	@Autowired
	private EduTeacherService teacherService;


	// 查询讲师所有数据
	@ApiOperation(value = "查询讲师数据")
	@GetMapping("findAll")  // rest 风格
	public Result findAllTeacher() {

		List<EduTeacher> list = teacherService.list(null);
		return Result.ok().data("items", list);
	}


	// 逻辑删除讲师
	@ApiOperation(value = "逻辑删除讲师")
	@DeleteMapping("{id}")
	public Result removeTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true)
	                                @PathVariable String id) {

		boolean flag = teacherService.removeById(id);
		if (flag) {
			return Result.ok();
		} else {
			return Result.error();
		}
	}


	// 分页查询讲师
	@GetMapping("pageTeacher/{current}/{limit}")
	public Result pageListTeacher(@PathVariable long current,
	                              @PathVariable long limit) {

		Page<EduTeacher> pageTeacher = new Page<>(current, limit);
		teacherService.page(pageTeacher, null);
		List<EduTeacher> records = pageTeacher.getRecords();
		long total = pageTeacher.getTotal();

		Map map = new HashMap<>();
		map.put("total", total);
		map.put("rows", records);
		return Result.ok().data(map);

		//return Result.ok().data("total",total).data("rows",records);

	}


	// 分页查询讲师，注意使用PutMapping
	@PostMapping("pageTeacherCondition/{current}/{limit}")
	public Result pageListTeacherCondition(@PathVariable long current,
	                                       @PathVariable long limit,
	                                       @RequestBody(required = false) TeacherQuery teacherQuery) {

		// 创建page 对象
		Page<EduTeacher> pageTeacher = new Page<>(current, limit);
		// 构建条件
		QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

		String name = teacherQuery.getName();
		Integer level = teacherQuery.getLevel();
		String begin = teacherQuery.getBegin();
		String end = teacherQuery.getEnd();
		// Mybatis 动态 SQL 或者 拼接条件
		if (!StringUtils.isEmpty(name)) {
			wrapper.like("name", name);
		}
		if (!StringUtils.isEmpty(level)) {
			wrapper.eq("level", level);
		}
		if (!StringUtils.isEmpty(begin)) {
			wrapper.ge("gmt_create", begin);
		}
		if (!StringUtils.isEmpty(end)) {
			wrapper.le("gmt_create", end);
		}

		// 排序
		wrapper.orderByDesc("gmt_create");

		teacherService.page(pageTeacher, wrapper);
		List<EduTeacher> records = pageTeacher.getRecords();
		long total = pageTeacher.getTotal();
		return Result.ok().data("total", total).data("rows", records);
	}


	// 添加讲师
	@PostMapping("addTeacher")
	public Result addTeacher(@RequestBody(required = true) EduTeacher eduTeacher) {

		boolean save = teacherService.save(eduTeacher);
		if (save) {
			return Result.ok();
		} else {
			return Result.error();
		}
	}


	// 根据讲师ID 查询
	@GetMapping("getTeacher/{id}")
	public Result getTeacher(@PathVariable String id) {

		EduTeacher teacher = teacherService.getById(id);
		return Result.ok().data("teacher", teacher);
	}

	// 根据讲师ID修改     1
	@PostMapping("updateTeacher")
	public Result updateTeacherById1(@RequestBody EduTeacher eduTeacher) {

		boolean update = teacherService.updateById(eduTeacher);
		if (update) {
			return Result.ok();
		} else {
			return Result.error();
		}
	}

	// 根据讲师ID修改     2
	@PutMapping("updateTeacher/{id}")
	public Result updateTeacherById2(
			@PathVariable String id,
			@RequestBody EduTeacher teacher) {

		teacher.setId(id);
		teacherService.updateById(teacher);
		return Result.ok();
	}


	// 自定义异常测试
	@GetMapping("ExceptionTest")
	public void ExceptionTest(){
		try{
			int i = 1 / 0;
		}catch (Exception ex){
			throw new GuliException(20001,"执行了自定义异常处理...");
		}
	}





}

