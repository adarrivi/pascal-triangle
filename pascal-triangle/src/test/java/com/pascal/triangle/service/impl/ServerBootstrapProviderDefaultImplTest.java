package com.pascal.triangle.service.impl;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.junit.Assert;
import org.junit.Test;

import com.pascal.triangle.view.server.ServerBootstrapProvider;
import com.pascal.triangle.view.server.impl.ServerBootstrapProviderDefaultImpl;

public class ServerBootstrapProviderDefaultImplTest {

	private ServerBootstrapProvider victim = new ServerBootstrapProviderDefaultImpl();

	private ServerBootstrap bootstrap;

	@Test
	public void getServerBootstrap_ReturnsNotNullBootstrap() {
		whenGetServerBootstrap();
		thenBootstrapShouldNotBeNull();
	}

	private void whenGetServerBootstrap() {
		bootstrap = victim.getServerBootstrap();
	}

	private void thenBootstrapShouldNotBeNull() {
		Assert.assertNotNull(bootstrap);
		Assert.assertTrue(bootstrap.getFactory() instanceof NioServerSocketChannelFactory);
	}

	@Test
	public void getServerBootstrap_UsesNioServerSocketChFactory() {
		whenGetServerBootstrap();
		thenUsesNioServerSocketChFactory();
	}

	private void thenUsesNioServerSocketChFactory() {
		Assert.assertTrue(bootstrap.getFactory() instanceof NioServerSocketChannelFactory);
	}

}
