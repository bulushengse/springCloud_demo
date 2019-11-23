package com.example.demo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import zipkin.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class SpringCloudSleuthDemoApplication {

	
	/**
	 * 
	 * 微服务架构是一个分布式架构，微服务系统按业务划分服务单元，一个微服务系统往往有多个服务单元，
	 * 由于服务单元数量众多，业务的复杂性较高，如果出现了错误和异常，难以定位。
 	 * 工作流：一个请求可能需要调用很多个服务，而内部服务的调用复杂性决定了问题难以定位。
 	 * 因此在微服务架构中需要实现分布式链路追踪，使得每个请求的步骤清晰可见。
 	 * 例：用户请求->前端A(远程调用)->系统中间件(B/C)->后端服务D/E->处理完成后返回给用户。
 	 * 
 	 *  springcloud sleuth 整合zipkin进行服务链路追踪 
 	 * server端配置：
	 * 在pom.xml添加springCloudSleuth相关依赖，
	 * 在启动类SpringBootAdminServerDemoApplication加@@EnableZipkinServer注解，OK
	 * 访问地址：http://localhost:9411/zipkin/
	 *  
	 * client端配置：
	 * 详见springCloud_consumers_demo项目
 	 *  
 	 *  注：Spring Boot 2.0之后，使用EnableZipkinServer创建自定义的zipkin服务器已经被废弃，可使用到官网提供的jar启动使用。
 	 *  
 	 *  zipkin-server-2.12.9-exec.jar访问地址：http://localhost:9411/zipkin/
 	 *  
 	 *  Zipkin数据持久化 （未测试）
 	 *  默认情况，zipkin存储记录到内存，如果服务重启，则所有记录丢失。
 	 *  为了保证持久性，zipkin支持Mysql、Elasticsearch、Cassandra存储。
 	 *  在pom.xml添加相关依赖，
 	 *  在application.yml增加配置
 	 *  重启服务，查看数据库，会自动生成数据库表
 	 *  
 	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSleuthDemoApplication.class, args);
		System.out.println("sleuth服务启动成功");
		
		/*try {
			Runtime.getRuntime().exec("java -jar zipkin-server-2.12.9-exec.jar");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//停止服务，打开任务管理器，点击详细信息， 在名称等表格头上右键‘选择列’，勾选命令行，
		//查找java -jar zipkin-server-2.12.9-exec.jar的命令结束任务
		*/				
	}

}
