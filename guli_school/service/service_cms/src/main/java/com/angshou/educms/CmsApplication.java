package com.angshou.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-05 13:05
 * @description
 */

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.angshou"})
public class CmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(CmsApplication.class, args);
	}
}
