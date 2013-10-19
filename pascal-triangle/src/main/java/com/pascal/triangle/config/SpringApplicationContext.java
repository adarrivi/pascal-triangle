package com.pascal.triangle.config;

import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.pascal.triangle")
@PropertySource("classpath:server.properties")
public class SpringApplicationContext {

	@Bean
	public ServiceLocatorFactoryBean httpControllerLocator() {
		ServiceLocatorFactoryBean serviceLocatorFactoryBean = new ServiceLocatorFactoryBean();
		serviceLocatorFactoryBean
				.setServiceLocatorInterface(HttpControllerFactory.class);
		return serviceLocatorFactoryBean;
	}

}
