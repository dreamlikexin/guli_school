package com.angshou.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-03 13:55
 * @description
 */

@Data
public class CourseQuery {

	@ApiModelProperty(value = "课程名称")
	private String title;

	@ApiModelProperty(value = "课程状态")
	private String status;

	@ApiModelProperty(value = "一级类别id")
	private String subjectParentId;

	@ApiModelProperty(value = "二级类别id")
	private String subjectId;

}
