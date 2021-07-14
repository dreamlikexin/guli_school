package com.angshou.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-01 16:33
 * @description
 */

@Data
public class SubjectData {

	@ExcelProperty(value = "一级分类", index = 0)
	private String oneSubjectName;

	@ExcelProperty(value = "二级分类", index = 1)
	private String twoSubjectName;

}
