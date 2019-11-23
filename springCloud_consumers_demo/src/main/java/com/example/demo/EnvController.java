package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/env")
@RefreshScope       //动态刷新配置类    
public class EnvController {
	
	@Value("${com.getInt}")
    String temp; 
	
	/**
	 * Config 统一配置管理中心
	 * 
	 * 1.Spring Cloud Config 服务端配置，详见springCloud_ConfigServer_demo项目
	 * 2.Spring Cloud Config 客户端配置，
	 * pom文件中添加：
	 * <dependency>
	 *		<groupId>org.springframework.cloud</groupId>
	 *		<artifactId>spring-cloud-starter-config</artifactId>
	 * </dependency>
	 * 新增bootstrap.yml配置文件，比application文件的配置会先加载
	 * 先启动配置中心服务端，客户端获取consumers-test.properties配置
	 * 
	 * 
	 * 与RabbitMq实现config-server自动刷新配置 ，客户端配置：
	 * 在pom文件中添加：spring-cloud-starter-bus-amqp和spring-boot-starter-actuator依赖
	 * 在application.yml中加RabbitMq和management的配置，详见application-rabbitmq.yml
	 * 增加注解@RefreshScope ，哪个类需要自动更新配置，ok
	 * 
	 */
	@RequestMapping("/getInt")
    public ModelAndView getInt(){
		ModelAndView mv = new ModelAndView();
 		System.out.println("-----------------------------------"+temp);
 		mv.addObject("msg","从远程配置文件获取随机数:"+temp);
 		mv.setViewName("result");
    	return mv;
    }
	
	
	
}
