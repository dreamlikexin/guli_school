package com.angshou.eduservice.entity.vo;

import lombok.Data;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-02 20:33
 * @description
 */

@Data
public class CoursePublicVo {

	private String title;
	private String cover;
	private Integer lessonNum;
	private String subjectLevelOne;
	private String subjectLevelTwo;
	private String teacherName;
	private String price;//只用于显示

}
