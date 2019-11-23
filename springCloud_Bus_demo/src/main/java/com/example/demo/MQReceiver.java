package com.example.demo;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
public class MQReceiver {
	
	@RabbitListener(queues = "direct_queue")
	public void directReceive(Message message, Channel channel) throws IOException {
		// 手动应答
		// 第一个参数代表消费者拒绝一条或者多条消息，第二个参数表示一次是否拒绝多条消息，第三个参数表示是否把当前消息重新入队
		//channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);CorrelationData
		
		System.out.println("=============收到direct_queue消息:"+new String(message.getBody()));
		System.out.println("=============messageId:"+message.getMessageProperties().getMessageId());
		System.out.println("=============parm1:"+message.getMessageProperties().getHeaders().get("parm1"));
	}
	

}

