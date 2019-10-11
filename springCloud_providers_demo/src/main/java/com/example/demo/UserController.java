package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

		@Value("${server.port}")
	    String port;
	
		private Msg msg = new Msg();
	
	 	@RequestMapping("/getUser")
	    public Msg getUser(String username){
	 		User user = new User();
	 		user.setUser_id("001");
	 		user.setUsername(username);
	 		user.setName("张三");
	 		user.setAge("18");
	 		user.setSex("男");
	 		user.setRole_id("123456");
	 		user.setRemark("port:"+port);
	 		msg.setObj(user);
	        return msg;
	    }
	 	
	 	@RequestMapping("/loginCheck")
	    public Msg loginCheck(String username,String password){
	       String str = "username:"+username+",password:"+password+",登陆验证成功。----port:"+port;
	       msg.setObj(str);
	       return msg;
	    }
}
