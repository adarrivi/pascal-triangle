package com.pascal.triangle.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.pascal.triangle.service.HumanPyramidService;

@Component("main")
public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	@Autowired
	private HumanPyramidService humanPyramidService;

	// The resource is closed in registerShutdownHook.
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ctx.registerShutdownHook();
		final Main main = (Main) ctx.getBean("main");
		main.run();
	}

	private void run() {
		LOG.debug("Run: {}",
				humanPyramidService.getEdgeHumanWeightOverShoulders(5));
	}

}
