package com.pascal.triangle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.pascal.triangle.config.SpringApplicationContext;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		// The server, NettyHttpServer, will start automatically when the
		// application context is loaded thanks to @PostConstruct annotation
		new Main().loadApplicationContextAndStartServer();
	}

	@SuppressWarnings("resource")
	private void loadApplicationContextAndStartServer() {
		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				SpringApplicationContext.class);
		// The resource is closed in registerShutdownHook.
		applicationContext.registerShutdownHook();
		LOG.debug("Application context loaded");
	}

}
