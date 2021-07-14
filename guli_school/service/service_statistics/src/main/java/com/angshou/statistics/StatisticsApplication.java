package com.angshou.statistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-12 20:24
 * @description
 */


@SpringBootApplication
@ComponentScan(basePackages = {"com.angshou"})
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling  // 定时任务
public class StatisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatisticsApplication.class, args);
	}
}
