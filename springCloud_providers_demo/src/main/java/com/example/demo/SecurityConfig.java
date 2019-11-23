package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration //@Configuration启动容器+@Bean注册Bean
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * spring boot 2.0+之后   security.basic.enabled=false配置无效
	 * 重写configure方法， security登录不需要验证
	 */
	protected void configure(HttpSecurity http) throws Exception {
			
		http.authorizeRequests()
					.anyRequest().permitAll().and().logout().permitAll();
	}
}
