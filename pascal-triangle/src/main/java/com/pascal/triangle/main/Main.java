package com.pascal.triangle.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import com.pascal.triangle.config.SpringApplicationContext;
import com.pascal.triangle.service.HumanPyramidService;

@Component("main")
public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	@Autowired
	private HumanPyramidService humanPyramidService;

	public static void main(String[] args) {
		// The resource is closed in registerShutdownHook.
		@SuppressWarnings("resource")
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringApplicationContext.class);
		ctx.registerShutdownHook();
		final Main main = (Main) ctx.getBean("main");
		main.run();
	}

	private void run() {
		LOG.debug("Run: {}",
				humanPyramidService.getEdgeHumanWeightOverShoulders(5));
	}

}
