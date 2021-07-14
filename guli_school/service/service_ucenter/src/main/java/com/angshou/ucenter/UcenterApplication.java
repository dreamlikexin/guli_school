package com.angshou.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-06 20:09
 * @description
 */


@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.angshou"})
public class UcenterApplication {
	public static void main(String[] args) {
		SpringApplication.run(UcenterApplication.class, args);
	}
}
