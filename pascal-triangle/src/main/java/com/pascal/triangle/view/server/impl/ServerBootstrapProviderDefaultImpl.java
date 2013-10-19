package com.pascal.triangle.view.server.impl;

import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.springframework.stereotype.Component;

import com.pascal.triangle.view.server.ServerBootstrapProvider;

@Component
public class ServerBootstrapProviderDefaultImpl implements
		ServerBootstrapProvider {

	@Override
	public ServerBootstrap getServerBootstrap() {
		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));
		return bootstrap;
	}

}
