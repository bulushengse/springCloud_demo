package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc //开启Spring MVC支持，若无此句，重写WebMvcConfigurerAdapter方法无效  
public class MvcConfig extends WebMvcConfigurerAdapter {

	/**
	 * 设置默认的首页
	 * 
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//super.addViewControllers(registry);
		registry.addViewController("/").setViewName("/index");
	}

	/**
	 * 设置对静态资源的访问
	 * 
	 */
	@Override  
	public void addResourceHandlers(ResourceHandlerRegistry registry) {  
	  super.addResourceHandlers(registry);  
	  //addResourceHandler指的是对外暴露的访问路径，addResourceLocations指的是文件放置的目录
	  //WebJars以jar包的形式来使用前端的各种框架、组件    
	  registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	  //spring-cloud-netflix-hystrix-dashboard-2.0.0.RELEASE.jar静态资源
	  registry.addResourceHandler("/hystrix/**").addResourceLocations("classpath:/static/hystrix/");
	}

}
