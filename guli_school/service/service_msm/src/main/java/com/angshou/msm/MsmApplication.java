package com.angshou.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-06 16:53
 * @description
 */

@EnableDiscoveryClient // nacos 注册
@EnableFeignClients    // 调用端注解
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.angshou"})  // 引入 Swagger
public class MsmApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsmApplication.class, args);
	}

}
