package com.pascal.triangle.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.pascal.triangle.config.SpringApplicationContext;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
	private static final Main INSTANCE = new Main();
	private AbstractApplicationContext applicationContext;

	private Main() {
		// To limit scope
	}

	public static Main getInstance() {
		return INSTANCE;
	}

	public static void main(String[] args) {
		INSTANCE.loadApplicationContext();
	}

	private void loadApplicationContext() {
		// The resource is closed in registerShutdownHook.
		applicationContext = new AnnotationConfigApplicationContext(
				SpringApplicationContext.class);
		applicationContext.registerShutdownHook();
		LOG.debug("Application context loaded");
	}

	protected AbstractApplicationContext getApplicationContext() {
		return applicationContext;
	}

}
