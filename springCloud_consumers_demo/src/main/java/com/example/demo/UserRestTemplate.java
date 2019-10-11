package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRestTemplate {

	@Autowired
    private RestTemplate restTemplate;
    
	private Msg msg = new Msg();
	
	@HystrixCommand(fallbackMethod = "getUserFallBack")
    public Msg getUser(String name){
    	//spring-cloud-user是EurekaServer注册的应用名
		Msg msg = 	restTemplate.getForEntity("http://spring-cloud-providers/user/getUser",Msg.class).getBody();
		return msg;
    }
	
	// fallback方法 需要与@HystrixCommand 在同一个类中
	public Msg getUserFallBack(String name) {
		msg.setSign(0);//服务调用数据是否成功
		return msg;
	}
	
	@HystrixCommand(fallbackMethod = "loginCheckFallBack")
	 public Msg loginCheck(String username,String password) {
		Msg msg = restTemplate.getForEntity("http://spring-cloud-providers/user/loginCheck",Msg.class).getBody();
		return msg;
	 };
	
	public Msg loginCheckFallBack(String username,String password) {
		msg.setSign(0);
		return msg;
	}
}
