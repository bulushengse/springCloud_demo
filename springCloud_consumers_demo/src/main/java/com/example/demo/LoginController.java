package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	@Autowired
	private UserRestTemplate userService;
	
	@Autowired
	private RoleFeignClient roleService;
	
	@Value("${com.getInt}")
    String temp; 
	
	/**
	 * springCloud 服务间的调用以及服务降级两种方法：
	 * 服务降级：当调用服务失败时，启用备用方法fallback
	 * 服务熔断：当调用服务失败多次不可用时，熔断该节点微服务的调用
	 * 
	 * 
	 * 一.使用ribbon+restTemplate进行服务调用，
	 * 1.1)Ribbon是一个基于HTTP和TCP的客户端负载均衡工具，它基于Netflix Ribbon实现的
	 * 新增项目启动初始化RestTemplate Bean类，加@LoadBalanced注释。详见BeanConfig.java
	 * 使用restTemplate.getForEntity("http://spring-cloud-user/getUser",User.class).getBody()调用，详见UserRestTemplate.java
	 * 1.2)如果实现服务降级，在SpringCloudRoleDemoApplication启动类加@EnableCircuitBreaker
	 * 调用方法增加@HystrixCommand注释，实现回调方法，详见UserRestTemplate.java
	 * 
	 * 
	 * 二.使用Feign进行服务调用
	 * 2.1)Feign是一个声明式的伪Http客户端，它使得写Http客户端变得更简单。具有可插拔的注解特性。默认集成了Ribbon，实现了负载均衡的效果
     * 在SpringCloudRoleDemoApplication启动类上添加@EnableFeignClients注解
     * 新增一个Feign接口类，加@FeignClient注释，详见RoleFeignClient.java
     * 2.2)如果实现服务降级，Feign已经整合了Hystrix，只需在application.yml，配置hystrix开启feign.hystrix.enabled=true
     * 新增一个回调类，实现Feign接口类，重写回调方法，详见RoleFeignClientFallback.java
     *
     *
     *三.两种方式实现熔断，都是基于Hystrix的，因此使用Hystrix Dashboard断路器监控组件来查看各项指标数据
     *pom文件中添加：
	 *	<dependency>
	 *		<groupId>org.springframework.cloud</groupId>
	 *		<artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
	 *	</dependency>
	 *	<dependency>
     *      <groupId>org.springframework.boot</groupId>
	 *	     <artifactId>spring-boot-starter-actuator</artifactId>
	 *	</dependency>
     *在SpringCloudRoleDemoApplication启动类上添加@EnableHystrixDashboard注解
     *springboot为2.0，默认Actuato只暴露了2个端点heath 和 info，所以在application.yml配置management.endpoints.web.exposure.include='*'
     *springboot为2.0，需要添加 ServletRegistrationBean 因为springboot的默认路径不是 "/hystrix.stream"，详见BeanConfig.java getServlet()
     *访问地址http://localhost:8100/hystrix，
     *单节点模式，输入http://localhost:8100/actuator/hystrix.stream
     *集群模式，输入http://localhost:8100/turbine.stream  //详细见springCloud_Turbine_demo这个项目
     *点击 Monitor Stream，当服务被消费调用时才会有数据，否则是没有是 Loading... 状态
     *注：某些浏览器不支持html5，也会一直显示的是 Loading... 状态
     * 
     *
     *测试时，修改端口port和节点instance-id，多启动几个服务提供者springCloud_providers_demo
     *
	 * @author zhoubc
	 *
	 */
	
	@RequestMapping("/getUser")
	public ModelAndView getUser(String username){
		ModelAndView mv = new ModelAndView();
		Msg msg = userService.getUser(username);
		if(msg.getSign()==1) {//这段判断代码可以放在mvc拦截器里统一
			mv.addObject("msg",msg.getObj().toString());
		}else {
			mv.addObject("msg",msg.getRemark());
		}
		mv.setViewName("result");
	    return mv;
	}
	
	@RequestMapping("/getRole")
	public ModelAndView getRole(String roleId){
		ModelAndView mv = new ModelAndView();
		Msg msg = roleService.getRole(roleId);
		if(msg.getSign()==1) {
			mv.addObject("msg",msg.getObj().toString());
		}else {
			mv.addObject("msg",msg.getRemark());
		}
		mv.setViewName("result");
	    return mv;
	}
	
	@RequestMapping("/loginCheck")
	public ModelAndView loginCheck(String username,String password){
		ModelAndView mv = new ModelAndView();
		Msg msg = userService.loginCheck(username, password);
		if(msg.getSign()==1) {
			mv.addObject("msg",msg.getObj().toString());
		}else {
			mv.addObject("msg",msg.getRemark());
		}
		mv.setViewName("result");
	    return mv;
	}
	
	
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
