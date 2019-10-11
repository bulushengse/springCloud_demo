package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @FeignClient
 * value，注册中心应用名
 * fallback，远程服务调用失败的回调方法，也叫服务降级处理，回调类必须实现@FeignClient标识的接口
 */
@FeignClient(value = "spring-cloud-providers",fallback = RoleFeignClientFallback.class)
public interface RoleFeignClient {
	
		@GetMapping(value = "/role/getRole")
	   public Msg getRole(@RequestParam("roleId")String roleId);
	
}
