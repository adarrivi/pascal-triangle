package com.pascal.triangle.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.pascal.triangle.config.SpringApplicationContext;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		loadApplicationContext();
	}

	private static void loadApplicationContext() {
		// The resource is closed in registerShutdownHook.
		@SuppressWarnings("resource")
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringApplicationContext.class);
		ctx.registerShutdownHook();
		LOG.debug("Application context loaded");
	}

}
