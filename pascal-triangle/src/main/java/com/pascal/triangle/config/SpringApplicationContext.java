package com.pascal.triangle.config;

import java.util.Arrays;

import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.pascal.triangle.view.controller.HttpControllerFactory;

@Configuration
@ComponentScan("com.pascal.triangle")
@PropertySource("classpath:server.properties")
@EnableCaching
public class SpringApplicationContext {

	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public ServiceLocatorFactoryBean httpControllerLocator() {
		ServiceLocatorFactoryBean serviceLocatorFactoryBean = new ServiceLocatorFactoryBean();
		serviceLocatorFactoryBean
				.setServiceLocatorInterface(HttpControllerFactory.class);
		return serviceLocatorFactoryBean;
	}

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache(
				"getHumanWeightOverShoulders")));
		return cacheManager;
	}

}
