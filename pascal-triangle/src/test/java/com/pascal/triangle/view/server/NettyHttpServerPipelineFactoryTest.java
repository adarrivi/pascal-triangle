package com.pascal.triangle.view.server;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.HttpChunkAggregator;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

public class NettyHttpServerPipelineFactoryTest {

	@Mock
	private ApplicationContext applicationContext;

	@InjectMocks
	private NettyHttpServerPipelineFactory victim = new NettyHttpServerPipelineFactory();

	@Mock
	private SimpleChannelUpstreamHandler handler;

	private ChannelPipeline pipeline;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(applicationContext.getBean(SimpleChannelUpstreamHandler.class))
				.thenReturn(handler);
	}

	@Test
	public void getPipeline_AddsHandlerInstanceFromContext() throws Exception {
		whenGetPipeline();
		thenHandlerInstanceFromContextShouldBeAdded();
	}

	private void whenGetPipeline() throws Exception {
		pipeline = victim.getPipeline();
	}

	private void thenHandlerInstanceFromContextShouldBeAdded() {
		verify(applicationContext).getBean(SimpleChannelUpstreamHandler.class);
		Assert.assertEquals(handler, pipeline.get("handler"));
	}

	@Test
	public void getPipeline_AddsHttpRequestDecoder() throws Exception {
		whenGetPipeline();
		thenPipelineShouldContain("decoder", HttpRequestDecoder.class);
	}

	private void thenPipelineShouldContain(String propertyName,
			Class<?> expectedEntityClass) {
		ChannelHandler channelHandler = pipeline.get(propertyName);
		Assert.assertNotNull(channelHandler);
		Assert.assertEquals(expectedEntityClass, channelHandler.getClass());
	}

	@Test
	public void getPipeline_AddsHttpRequestEncoder() throws Exception {
		whenGetPipeline();
		thenPipelineShouldContain("encoder", HttpResponseEncoder.class);
	}

	@Test
	public void getPipeline_AddsHttpChunkAggregator() throws Exception {
		whenGetPipeline();
		thenPipelineShouldContain("aggregator", HttpChunkAggregator.class);
	}

}
