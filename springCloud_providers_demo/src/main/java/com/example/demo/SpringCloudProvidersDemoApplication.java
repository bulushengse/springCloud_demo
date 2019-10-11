package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 服务提供者
 * @author zhoubc
 */
@SpringBootApplication
@EnableEurekaClient
public class SpringCloudProvidersDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudProvidersDemoApplication.class, args);
		System.out.println("providers服务启动成功");
	}

}
