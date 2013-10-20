package com.pascal.triangle.view;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MainTest {

	@BeforeClass
	public static void setUpClass() {
		Main.main(null);
	}

	@Test
	public void main_CreatesApplicationContext() throws Exception {
		Assert.assertNotNull(Main.getInstance().getApplicationContext());
	}

	@Test
	public void main_RegistersShutdownHookInContext() throws Exception {
		// TODO Investigate if there any way to get the registered shutdown
		// hooks
	}

}
