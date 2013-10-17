package com.pascal.triangle.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.pascal.triangle")
@PropertySource("classpath:server.properties")
public class SpringApplicationContext {

	// Nothing to define yet

}
