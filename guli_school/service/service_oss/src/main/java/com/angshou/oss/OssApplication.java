package com.angshou.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-01 10:25
 * @description
 */

// 不去加载数据库配置
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.angshou"})
public class OssApplication {

	public static void main(String[] args) {
		SpringApplication.run(OssApplication.class, args);
	}
}
