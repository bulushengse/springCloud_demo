package com.example.demo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MQSender {
	
	@Autowired
    private RabbitTemplate  rabbitTemplate;
 

    public void sendDirectMsg(Object object){
    	//消息唯一id
    	String messageId = String.valueOf((int)(Math.random() * 100000));
    	CorrelationData correlationData = new CorrelationData(messageId);
		//correlationData.setId(messageId);
		//消息的其他属性
		TenantMessageProcessor messagePostProcessor = new TenantMessageProcessor(messageId);
		//messagePostProcessor.setMessageId(messageId);
		System.out.println("=============sendDirectMsg:"+object.toString());
    	rabbitTemplate.convertAndSend("direct_exchange","test_key",object,messagePostProcessor,correlationData);
    }
    

   
}
