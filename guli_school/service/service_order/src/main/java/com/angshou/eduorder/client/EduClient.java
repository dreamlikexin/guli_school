package com.angshou.eduorder.client;

import com.angshou.commonutils.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-12 9:55
 * @description
 */


@Component
@FeignClient("service-edu")
public interface EduClient {

	// 课程详情方法
	@GetMapping("/eduservice/coursefront/getCourseInfoInfo/{courseId}")
	public CourseWebVoOrder getCourseInfoInfo(@PathVariable("courseId") String courseId);


}
