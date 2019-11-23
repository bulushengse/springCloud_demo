package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCloudBusDemoApplication {

	
	/**
	 * 当前发送端与接收端都在这一个项目！！！
	 * 
	 * http://localhost:5432/
	 * 更多RabbitMq的使用，见springBoot_rabbitMQ_demo项目
	 * 
	 * 
	 * SpringCloud Bus结合RabbitMQ与springCloud ConfigServer动态刷新配置
	 * 如果要去更新所有微服务的配置，在不重启的情况下去更新配置，只能依靠spring cloud config了，
	 * 但是，是我们要一个服务一个服务的发送post请求?这时候我们就不要忘记消息队列的发布订阅模型。
	 * 当 Git 仓库中某个配置文件中的参数更新后，只需通过 POST方法访问 config Server 的  /actuator/bus-refresh 接口，就可以让所以的微服务节点更新配置。
	 * 详见springCloud_ConfigServer_demo项目
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudBusDemoApplication.class, args);
	}

}
