package com.angshou.canal;

import com.angshou.canal.client.CanalClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-13 14:04
 * @description
 */

@SpringBootApplication
public class CanalApplication implements CommandLineRunner {

	@Resource
	private CanalClient canalClient;


	public static void main(String[] args) {
		SpringApplication.run(CanalApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		// 项目启动，执行 canal 客户端监听
		canalClient.run();
	}
}
