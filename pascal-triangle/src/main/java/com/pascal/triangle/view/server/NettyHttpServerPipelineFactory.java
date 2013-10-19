package com.pascal.triangle.view.server;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class NettyHttpServerPipelineFactory implements ChannelPipelineFactory {

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = createDefaultPipeline();
		pipeline = addEncodingDecoding(pipeline);
		pipeline = addNewHttpHandler(pipeline);
		return pipeline;
	}

	private ChannelPipeline createDefaultPipeline() {
		return Channels.pipeline();
	}

	private ChannelPipeline addEncodingDecoding(ChannelPipeline pipeline) {
		pipeline.addLast("decoder", new HttpRequestDecoder());
		pipeline.addLast("encoder", new HttpResponseEncoder());
		return pipeline;
	}

	private ChannelPipeline addNewHttpHandler(ChannelPipeline pipeline) {
		pipeline.addLast("handler",
				applicationContext.getBean(SimpleChannelUpstreamHandler.class));
		return pipeline;
	}
}
