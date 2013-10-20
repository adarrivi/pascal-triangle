package com.pascal.triangle.config;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

public class SpringApplicationContextTest {

	private SpringApplicationContext springApplicationContext = new SpringApplicationContext();

	@Test
	public void context_DefinesPropertySourcesPlaceholderConfigurer() {
		Assert.assertTrue(springApplicationContext
				.propertySourcesPlaceholderConfigurer() instanceof PropertySourcesPlaceholderConfigurer);
	}

	@Test
	public void context_DefinesHttpControllerLocator_AsServiceLocatorFactoryBean() {
		Assert.assertTrue(springApplicationContext.httpControllerLocator() instanceof ServiceLocatorFactoryBean);
	}

	@Test
	public void cacheManager_DefinesSimpleCacheManager() {
		Assert.assertTrue(springApplicationContext.cacheManager() instanceof SimpleCacheManager);
	}
}
