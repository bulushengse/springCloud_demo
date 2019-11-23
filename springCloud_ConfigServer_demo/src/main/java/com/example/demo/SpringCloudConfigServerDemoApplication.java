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
	 * Config统一配置管理
	 * 1）服务端
	 * 主要是application.yml的配置
	 * 注意远程配置文件的命名，应用名-环境.properties/应用名-环境.yml，如 user-dev.properties
	 * 服务端启动后，可以使用 http://应用IP:port/服务名/环境，
	 * 如http://localhost:9000/consumers/test, 
	 * http://localhost:9000/consumers-test.properties
	 * 
	 * 2）客户端配置获取，详见springCloud_consumers_demo项目
	 * 
	 * 
	 * 与RabbitMq实现config-server自动刷新配置  
	 * 1）服务端
	 * 在pom文件中添加：spring-cloud-starter-bus-amqp和spring-boot-starter-actuator依赖
	 * 在application.yml中加RabbitMq和management的配置
	 * 
	 * 2）客户端配置获取，详见springCloud_consumers_demo项目
	 *          
	 *  把github代码仓库springCloud_profil项目   修改com.getInt=xxx  
	 *  然后使用 POST 方法访问 Config Server 的 /actuator/bus-refresh 接口     
	 *  双击curl-post.cmd，自动更新所有config  client的配置
	 *          
	 *  cmd命令：curl -X POST http://localhost:9000/actuator/bus-refresh
	 * 
	 *  经测试过，其他POST请求访问http://localhost:9000/actuator/bus-refresh没效果！
	 * 
	 *  为解决需要手动去访问Bus刷新配置的接口，才能完成配置文件的动态刷新，
	 *  这里可用WebHooks实现动态更新，当git仓库的文件更新时自动调用Bus用于刷新配置的接口完成配置文件的动态刷新
	 *  打开你的 Github 仓库springCloud_profil项目，找到Settings选项，
	 *  进入Webhooks，点击Add webhook，输入Payload URL:http://www.xxxx.com/monitor
	 *  在pom文件中添加spring-cloud-config-monitor依赖 
	 *  将配置中心Config Server部署到外网可访问的服务器上。。。。。。。。。    （未测试）
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServerDemoApplication.class, args);
		System.out.println("configServer服务启动成功");
	}
	

}
