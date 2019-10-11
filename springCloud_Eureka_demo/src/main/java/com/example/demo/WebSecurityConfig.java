package com.example.demo;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 开启注册中心安全配置，Eureka Web页面以及服务注册需用户密码权限
 * 1.pom.xml加入：
 * 	<dependency>
 * 		<groupId>org.springframework.boot</groupId>
 * 	    <artifactId>spring-boot-starter-security</artifactId>
 * 	</dependency>
 * 		
 * 2.application.yml 配置用户密码 spring.security.user.name和spring.security.user.password
 * 
 * 3.Finchley版本，Spring Security 默认开启了所有 CSRF 攻击防御，需要禁用 /eureka 的防御。
 * 新增一个类，如下：
 *
 * 4.EurekaServer服务注册中心地址:
 *    defaultZone: http://zhoubc:zhoubc@localhost:8888/eureka/   
 *    
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }

}
