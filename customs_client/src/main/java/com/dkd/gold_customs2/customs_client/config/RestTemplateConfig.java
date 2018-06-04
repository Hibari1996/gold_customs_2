package com.dkd.gold_customs2.customs_client.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
/**
 * RestTemplate配置类，项目启动时将RestTemplate注入ioc容器，使用时直接使用@Autowired注入即可
 * 使用的@LoadBalanced表示开启了负载均衡，如果是内部调用注册中心的服务，则方法中传入有服务名称的url,可以实现负载均衡
 * 只有使用服务名才能实现负载均衡，传入ip:port的url将调用指定的服务
 * @author Administrator
 */
@Configuration
public class RestTemplateConfig {
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
