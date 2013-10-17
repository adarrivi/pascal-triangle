package com.pascal.triangle.view.client;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.http.HttpClientCodec;
import org.jboss.netty.handler.codec.http.HttpContentDecompressor;

public class HttpSnoopClientPipelineFactory implements ChannelPipelineFactory {

	private final boolean ssl;

	public HttpSnoopClientPipelineFactory(boolean ssl) {
		this.ssl = ssl;
	}

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		// Create a default pipeline implementation.
		ChannelPipeline pipeline = pipeline();

		pipeline.addLast("codec", new HttpClientCodec());

		// Remove the following line if you don't want automatic content
		// decompression.
		pipeline.addLast("inflater", new HttpContentDecompressor());

		// Uncomment the following line if you don't want to handle HttpChunks.
		// pipeline.addLast("aggregator", new HttpChunkAggregator(1048576));

		pipeline.addLast("handler", new HttpSnoopClientHandler());
		return pipeline;
	}
}