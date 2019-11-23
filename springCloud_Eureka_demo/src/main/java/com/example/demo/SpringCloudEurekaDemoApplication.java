package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurekaDemoApplication {

	/**
	 * EurekaServer web界面 http://localhost:8761/
	 * 查看Eureka上的微服务实例列表 http://localhost:8761/eureka/apps
	 * 
	 * 高可用的服务注册中心
	 * 1)需要修改hosts文件 (windows系统：C:\Windows\System32\drivers\etc\hosts   linux系统： /etc/hosts)
	 * 添加以下内容：
	 * 127.0.0.1 myhost1
	 * 127.0.0.1 myhost2
	 * 127.0.0.1 myhost3
	 * 2)需运行两个Eureka注册中心服务，修改项目的application.yml配置文件
	 * port:8261,hostname:myhost1,serviceUrl:http://zhoubc:zhoubc@myhost2:8762/eureka/,http://zhoubc:zhoubc@myhost3:8763/eureka/
	 * port:8262,hostname:myhost2,serviceUrl:http://zhoubc:zhoubc@myhost1:8761/eureka/,http://zhoubc:zhoubc@myhost3:8763/eureka/
	 * port:8263,hostname:myhost3,serviceUrl:http://zhoubc:zhoubc@myhost1:8761/eureka/,http://zhoubc:zhoubc@myhost2:8762/eureka/
	 *
	 * 启动成功之后，在浏览器中进行访问，这时候在8261的DS Replicas中可以看到myhost1,myhost2节点，在8262的DS Replicas中可以看到myhost1,myhost3节点，在8263的DS Replicas中可以看到myhost1,myhost2节点
	 * 启动服务提供者springCloud_providers_demo, 配置文件里serviceUrl写任意一个注册中心url
	 * 然后分别访问http://localhost:8261/, http://localhost:8262/,http://localhost:8263/  可以看到提供者已经注册到两个服务注册中心了。
	 *
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaDemoApplication.class, args);
		System.out.println("EurekaServer启动成功。。。");
	}

}
