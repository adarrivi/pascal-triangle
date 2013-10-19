package com.pascal.triangle.view.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NettyHttpServer {

	private static final Logger LOG = LoggerFactory
			.getLogger(NettyHttpServer.class);

	@Value("${server.port}")
	private int port;

	@Autowired
	private ChannelPipelineFactory pipelineFactory;

	private Channel serverChannel;

	@PostConstruct
	public void start() {
		ServerBootstrap bootstrap = configureServer();
		startServer(bootstrap);
	}

	private ServerBootstrap configureServer() {
		// Configure the server.
		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));

		// Enable TCP_NODELAY to handle pipelined requests without latency.
		bootstrap.setOption("child.tcpNoDelay", true);

		// Set up the event pipeline factory.
		bootstrap.setPipelineFactory(pipelineFactory);
		return bootstrap;
	}

	private void startServer(ServerBootstrap bootstrap) {
		serverChannel = bootstrap.bind(new InetSocketAddress(port));
		LOG.debug("Http server started");
	}

	@PreDestroy
	public void stop() {
		serverChannel.close();
		LOG.debug("Http server stopped");
	}
}
