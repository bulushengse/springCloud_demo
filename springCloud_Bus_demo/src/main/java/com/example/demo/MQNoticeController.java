package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value="mqNotice")
public class MQNoticeController {

	@Autowired
	private MQSender mqSender;
	
	@RequestMapping(value="/sendDirectMsg", method=RequestMethod.GET)  
	public ModelAndView sendDirectMsg() { 
		ModelAndView mv = new ModelAndView();
		mqSender.sendDirectMsg("mq测试发送~~~~~"+Math.random());
		mv.addObject("msg","测试消息已发送~~~~~~~~~~~~~~~");
		mv.setViewName("result");
		return mv;  
	}  
	
}
