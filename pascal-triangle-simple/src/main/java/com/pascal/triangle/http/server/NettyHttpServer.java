package com.pascal.triangle.http.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pascal.triangle.http.controller.HumanEdgeWeightController;

/**
 * Main server that will get started after the Spring application context is
 * loaded. It will process all requests coming from the socket port 8080, and
 * create a NEW NettyHttpServerHandler instance for each one
 * 
 * @author adarrivi
 * 
 */
@Component
public class NettyHttpServer {

	private static final Logger LOG = LoggerFactory
			.getLogger(NettyHttpServer.class);

	private Channel serverChannel;

	// The controller will be instantiated ONLY once, and the same instance is
	// going to be and used for all the requests, saving some memory
	@Autowired
	private HumanEdgeWeightController humanEdgeWeightController;

	// Method invoked when the application context is loaded
	@PostConstruct
	private void start() {
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
		bootstrap.setPipelineFactory(new NettyHttpServerPipelineFactory(
				humanEdgeWeightController));
		return bootstrap;
	}

	private void startServer(ServerBootstrap bootstrap) {
		serverChannel = bootstrap.bind(new InetSocketAddress(8080));
		LOG.debug("Http server started");
	}

	// This method will get executed when the application context is closed
	@PreDestroy
	private void stop() {
		serverChannel.close();
		LOG.debug("Http server stopped");
	}
}
