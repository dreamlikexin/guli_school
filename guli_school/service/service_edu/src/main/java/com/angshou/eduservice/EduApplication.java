package com.angshou.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author adminPC--昂首灬
 * @date 2021-06-28 16:39
 * @description
 */

@EnableDiscoveryClient // nacos 注册
@EnableFeignClients    // 调用端注解
@SpringBootApplication
@ComponentScan(basePackages = {"com.angshou"})  // 引入 Swagger
public class EduApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduApplication.class, args);
	}

}
