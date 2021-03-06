package com.pascal.triangle.http.server;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.http.HttpChunkAggregator;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;

import com.pascal.triangle.http.controller.HumanPyramidController;

/**
 * Class that sets up the http pipeline
 * 
 * @author adarrivi
 * 
 */
public class NettyHttpServerPipelineFactory implements ChannelPipelineFactory {

	private HumanPyramidController humanPyramidController;

	public NettyHttpServerPipelineFactory(
			HumanPyramidController humanEdgeWeightController) {
		this.humanPyramidController = humanEdgeWeightController;
	}

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		pipeline.addLast("decoder", new HttpRequestDecoder());
		pipeline.addLast("encoder", new HttpResponseEncoder());
		pipeline.addLast("aggregator", new HttpChunkAggregator(65536));
		pipeline.addLast("handler", new NettyHttpServerHandler(
				humanPyramidController));
		return pipeline;
	}
}
