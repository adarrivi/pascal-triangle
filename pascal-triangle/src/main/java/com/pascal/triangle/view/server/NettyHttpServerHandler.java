package com.pascal.triangle.view.server;

import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.QueryStringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pascal.triangle.config.HttpControllerFactory;
import com.pascal.triangle.view.controller.HttpRequestController;

@Component
@Scope("prototype")
public class NettyHttpServerHandler extends SimpleChannelUpstreamHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(NettyHttpServerHandler.class);

	@Autowired
	private HttpControllerFactory httpControllerFactory;

	private MessageEvent messageEvent;

	@Override
	public void messageReceived(ChannelHandlerContext ctx,
			MessageEvent messageEvent) throws Exception {
		HttpRequest request = (HttpRequest) messageEvent.getMessage();
		this.messageEvent = messageEvent;

		QueryStringDecoder queryStringDecoder = new QueryStringDecoder(
				request.getUri());

		HttpRequestController controller = httpControllerFactory
				.getController(queryStringDecoder.getPath());

		HttpResponse response = controller.processRequest(request);
		writeResponse(response);
	}

	private void writeResponse(HttpResponse response) {
		// Write the response.
		ChannelFuture future = messageEvent.getChannel().write(response);
		future.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,
			ExceptionEvent exceptionEvent) throws Exception {
		LOG.error("Error found handling the request.",
				exceptionEvent.getCause());
		exceptionEvent.getChannel().close();
	}
}
