package com.pascal.triangle.view.server;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.InetSocketAddress;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class NettyHttpServerTest {

	@Mock
	private ServerBootstrapProvider serverBootstrapProvider;
	@Mock
	private ChannelPipelineFactory pipelineFactory;

	@InjectMocks
	private NettyHttpServer victim = new NettyHttpServer();

	@Mock
	private ServerBootstrap bootstrap;

	@Mock
	private Channel serverChannel;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(serverBootstrapProvider.getServerBootstrap())
				.thenReturn(bootstrap);
		when(bootstrap.bind(any(InetSocketAddress.class))).thenReturn(
				serverChannel);
	}

	@Test
	public void start_SetTcpNoDelay() {
		whenStart();
		thenTcpNoDelayShouldBeTrue();
	}

	private void whenStart() {
		victim.start();
	}

	private void thenTcpNoDelayShouldBeTrue() {
		verify(bootstrap).setOption("child.tcpNoDelay", true);
	}

	@Test
	public void start_SetPipelineFactory() {
		whenStart();
		thenPipelineFactoryShouldBeSet();
	}

	private void thenPipelineFactoryShouldBeSet() {
		verify(bootstrap).setPipelineFactory(pipelineFactory);
	}

	@Test
	public void start_BootstrapIsBinded() {
		whenStart();
		thenBootstrapIsBindedWithInetSocket();
	}

	private void thenBootstrapIsBindedWithInetSocket() {
		verify(bootstrap).bind(any(InetSocketAddress.class));
	}

	@Test
	public void start_stop_ServerChannelIsClosed() {
		whenStart();
		whenStop();
		thenServerChannelIsClosed();
	}

	private void whenStop() {
		victim.stop();
	}

	private void thenServerChannelIsClosed() {
		verify(serverChannel).close();
	}

}
