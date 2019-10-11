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
	 * 一个微服务的hystrix dashboard只能监控一台服务器上的服务调用情况，使用了turbine后就可以监控多台服务器的情况
	 * 
	 * 访问地址http://localhost:9999/hystrix，
     * 集群模式，输入ttp://localhost:9999/turbine.stream?cluster=consumers
	 * 
	 * 测试时，修改端口port、节点instance-id、应用名spring.application.name，
	 * 多启动几个服务提供者springCloud_consumers_demo,
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudTurbineDemoApplication.class, args);
	}

}