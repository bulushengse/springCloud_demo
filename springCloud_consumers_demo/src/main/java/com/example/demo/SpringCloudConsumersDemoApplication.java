package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * 服务消费者
 * @EnableEurekaClient 只支持Eureka注册中心，@EnableDiscoveryClient还支持更多其他的注册中心
 * @EnableCircuitBreaker 当使用ribbon+restTemplate方式进行服务间调用时，为实现服务降级
 * @EnableFeignClients 使用Feign进行服务间调用并实现了服务降级
 * @EnableHystrix  开启断路器，实现熔断降级服务？不知哪里用到，不加也没什么影响！
 * @EnableHystrixDashboard  开启Hystrix断路器监控
 * 
 * @author zhoubc
 */
@SpringBootApplication
@EnableEurekaClient   
@EnableCircuitBreaker
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
public class SpringCloudConsumersDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConsumersDemoApplication.class, args);
		System.out.println("consumers服务启动成功"); 
	}

}
