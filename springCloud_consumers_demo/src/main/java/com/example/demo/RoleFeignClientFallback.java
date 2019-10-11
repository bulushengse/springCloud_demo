package com.example.demo;

import org.springframework.stereotype.Component;

@Component // 不要忘记添加
public class RoleFeignClientFallback implements RoleFeignClient{
	
	private Msg msg = new Msg();
	
	/**
	 * 当某些原因调用服务失败时，会执行该回调方法
	 */
	@Override
	public Msg getRole(String roleId) {
		msg.setSign(0);
		return msg;
	}
	

}
