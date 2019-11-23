 package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine 
@EnableHystrixDashboard
public class SpringCloudTurbineDemoApplication {

	/**
	 * 断路器监控hystrix dashboard，详见springCloud_consumers_demo项目
	 * 一个微服务的hystrix dashboard只能监控一台服务器上的服务调用情况，使用了turbine后就可以监控多台服务器的情况
	 * 
	 * 在pom.xml添加相关依赖，
	 * 在启动类SpringBootAdminServerDemoApplication加@EnableTurbine注解和@EnableHystrixDashboard注解
	 * 在application.yml配置文件，添加turbine相关配置（重点），OK
	 * 访问地址http://localhost:9999/hystrix，输入ttp://localhost:9999/turbine.stream?cluster=consumers，集群模式
	 * 
	 * 测试时，修改端口port、节点instance-id、应用名spring.application.name，
	 * 多启动几个服务提供者springCloud_consumers_demo,
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudTurbineDemoApplication.class, args);
		System.out.println("turbine服务启动成功");
	}

}
