package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class SpringCloudZuulDemoApplication {

	/**
	 * 服务路由
	 * 网关是系统的唯一对外的入口，介于客户端和服务器端之间的中间层，处理非业务功能 
	 * 提供路由请求、鉴权、监控、缓存、限流等功能。它将"1对N"问题转换成了"1对1”问题。
	 * 
	 * 在pom.xml添加相关依赖，
	 * 在启动类SpringBootAdminServerDemoApplication加@EnableZuulProxy注解
	 * 新增一个zuul过滤器类，编写业务的逻辑，详见MyFilter.java
	 * 在application.yml配置文件，添加zuul相关配置（重点），OK
	 * 
	 * http://localhost:8887/api-consumenrs/
	 * http://localhost:8887/api-providers/user/getUser
	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudZuulDemoApplication.class, args);
		System.out.println("zuul服务启动成功");
	}

}
