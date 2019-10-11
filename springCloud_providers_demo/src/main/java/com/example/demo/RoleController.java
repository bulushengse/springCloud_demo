package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

		@Value("${server.port}")
	    String port;
	
		private Msg msg = new Msg();
	
	 	@RequestMapping("/getRole")
	    public Msg getUser(String roleId){
	 		Role role = new Role();
	    	if(roleId!=null) {
	    		role.setRole_id(roleId);
	        	role.setRole_name("总经理");
	        	role.setRights("9999999999999999999");
	        	role.setRemark("port:"+port);
	    	}
	    	msg.setObj(role);
	    	return msg;
	    }
	 	
	 	
}
