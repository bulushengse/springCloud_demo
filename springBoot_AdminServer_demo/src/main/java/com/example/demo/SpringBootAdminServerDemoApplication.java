package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableEurekaClient
@EnableAdminServer
public class SpringBootAdminServerDemoApplication {

	/**
	 * Spring Boot Admin 用于管理和监控一个或者多个Spring Boot应用，
	 * server端配置：
	 * 在pom.xml添加springBootAdmin相关依赖，
	 * 在启动类SpringBootAdminServerDemoApplication加@EnableAdminServer注解，OK
	 * 注：经过测试，admin jar包版本差距较大，目前是2.0.0版本
	 * 访问地址http://localhost:7777/
	 * client端配置：
	 * 详见springCloud_providers_demo项目
	 * 
	 * 1）security安全认证
	 * 在pom.xml添加springSecurity、springweb 依赖jar包
	 * 新增一个Security初始化类，web监控页面权限的访问，详见SecuritySecureConfig.java类
	 * 在application.yml配置文件，添加spring.security.user.name和spring.security.user.password  用户密码
	 * 
	 * 2）mail邮件通知，当客户端触发UP或OFFLINE或其它状态时，会发邮件通知
	 * 在pom.xml添加springMail jar包
	 * 在application.yml配置文件，添加JavaMailSender配置，详见application.yml
	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminServerDemoApplication.class, args);
		System.out.println("adminServer服务启动成功");
	}

}
