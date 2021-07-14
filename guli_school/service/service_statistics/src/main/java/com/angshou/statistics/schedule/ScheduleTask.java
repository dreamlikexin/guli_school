package com.angshou.statistics.schedule;

import com.angshou.statistics.service.DailyService;
import com.angshou.statistics.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-13 8:44
 * @description 定时任务类
 */

@Component
public class ScheduleTask {

	@Autowired
	private DailyService dailyService;

	// cron 表达式 7 位，但是SpringBoot 只能是 6位
	/**
	 * 测试
	 * 每天七点到二十三点每五秒执行一次
	 */

	// @Scheduled(cron = "0/5 * * * * ?")
	// public void task1() {
	// 	System.out.println("*********++++++++++++*****执行了");
	// }

	/**
	 * 每天凌晨1点执行定时
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	public void task2() {
		//获取上一天的日期
		String day = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
		dailyService.registerCount(day);

	}


}
