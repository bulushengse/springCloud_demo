package com.example.demo;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
	
	@Autowired
	private ConnectionFactory connectionFactory;

	//队列 ，参数：队列名字，是否持久化, 是否自动删除, 是否exclusive
	@Bean
	public Queue directQueue() {
		return new Queue("direct_queue", true, false, false); 
	}

	//Direct交换机(1对1)，参数：交换器名称、是否持久化、是否自动删除
	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange("direct_exchange", true, false);
	}

	//绑定  将队列和交换机绑定, 并设置用于匹配键：test_key
	 @Bean
	 public  Binding directBinding(){
	     return BindingBuilder.bind(directQueue()).to(directExchange()).with("test_key");
	 }
	 
	 //	指定admin信息，当前的exchange和queue会在rabbitmq服务器上自动生成
	@Bean
    public RabbitAdmin rabbitAdmin(){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    } 


	//mq消息确认
	 @Bean    
	 public RabbitTemplate rabbitTemplate() {        
		 RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		// rabbitTemplate.setMessageConverter(); //可以自定义消息转换器  默认使用的JDK的，所以消息对象需要实现Serializable
		 rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		 
		// rabbitTemplate.setMessagePropertiesConverter(new DefaultMessagePropertiesConverter());
		 
		 // 消息发送失败返回到队列中, yml需要配置 publisher-returns: true        
		 rabbitTemplate.setMandatory(true);      
		 
		 // 消息返回,  发送失败时调用此方法。      yml需要配置 publisher-returns: true        
		 rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
			 	System.out.println("消息id："+message.getMessageProperties().getMessageId());
			 	System.out.println("响应状态码-ReplyCode："+replyCode);
		        System.out.println("响应内容-ReplyText："+replyText);
		        System.out.println("Exchange:"+exchange);
		        System.out.println("RouteKey"+routingKey);
		        System.out.println("投递失败的消息："+ new String(message.getBody()));
		 });
		 
		 // 消息确认, yml需要配置 publisher-confirms: true        
		 rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> { 
			 System.out.print("=============消息id:"+correlationData.getId()+",");
			 if (ack) {                
				 System.out.println("发送成功"); 
			 } else {                
				 System.out.println("发送失败,"+cause);  	    
			 }        
		});        
		return rabbitTemplate;    
	 }

}
