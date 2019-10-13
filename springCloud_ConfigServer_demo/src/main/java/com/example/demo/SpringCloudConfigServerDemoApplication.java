package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class SpringCloudConfigServerDemoApplication {

	/**
	 * 
	 * 1）Config统一配置管理服务端
	 * 主要是application.yml的配置
	 * 注意远程配置文件的命名，应用名-环境.properties/应用名-环境.yml，如 user-dev.properties
	 * 服务端启动后，可以使用 http://应用IP:port/服务名/环境，
	 * 如http://localhost:9000/consumers/test, 
	 * http://localhost:9000/consumers-test.properties
	 * 
	 * 2）客户端配置获取，详见springCloud_consumers_demo项目
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServerDemoApplication.class, args);
	}

}
