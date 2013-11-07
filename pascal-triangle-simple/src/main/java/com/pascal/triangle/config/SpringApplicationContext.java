package com.pascal.triangle.config;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// This class holds the application context configuration. It is a java representation of applicationContext.xml file
@Configuration
@ComponentScan("com.pascal.triangle")
@EnableCaching
public class SpringApplicationContext {

	// Loads and enables the cache manager, allowing the chaching of methods
	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache(
				"getHumanWeightOverShoulders")));
		return cacheManager;
	}

}
