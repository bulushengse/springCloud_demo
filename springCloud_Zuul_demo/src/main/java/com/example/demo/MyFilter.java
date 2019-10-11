package com.example.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * zuul不仅只是路由，并且还能过滤，做一些安全验证。
 * @author zhoubc
 *
 */
@Component
public class MyFilter extends ZuulFilter{

	/**
	 * 是否要过滤
	 */
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 过滤器的具体逻辑，统一服务认证，白名单IP等
	 */
	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	    System.out.println("request url:"+request.getRequestURL().toString());
		return null;
	}

	/**
	 * ZuulFilter过滤器的filterType方法返回一个字符串代表过滤器的类型,
	 * 在zuul中定义了四种不同生命周期的过滤器类型，具体如下： 
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
	 */
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	/**
	 * 过滤的顺序
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
