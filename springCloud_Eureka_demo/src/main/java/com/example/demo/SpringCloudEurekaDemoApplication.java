package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurekaDemoApplication {

	/**
	 * EurekaServer web界面 http://localhost:8888/
	 * 查看Eureka上的微服务实例列表 http://localhost:8888/eureka/apps
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaDemoApplication.class, args);
		System.out.println("EurekaServer启动成功。。。");
	}

}
