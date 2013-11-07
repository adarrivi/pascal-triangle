package com.pascal.triangle.http.server;

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

import com.pascal.triangle.exception.InvalidHttpParameterException;
import com.pascal.triangle.exception.InvalidTriangleException;
import com.pascal.triangle.http.controller.HumanEdgeWeightController;
import com.pascal.triangle.http.util.HttpUtils;

/**
 * Handles Http requests. It will check the url and delegate it to the right
 * controller.
 * 
 * @author adarrivi
 * 
 */
public class NettyHttpServerHandler extends SimpleChannelUpstreamHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(NettyHttpServerHandler.class);

	private MessageEvent messageEvent;
	private HumanEdgeWeightController humanEdgeWeightController;

	public NettyHttpServerHandler(HumanEdgeWeightController controller) {
		this.humanEdgeWeightController = controller;
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx,
			MessageEvent messageEvent) throws Exception {
		this.messageEvent = messageEvent;
		delegateMessageToController();
	}

	private void delegateMessageToController() {
		HttpRequest request = (HttpRequest) messageEvent.getMessage();
		QueryStringDecoder queryStringDecoder = new QueryStringDecoder(
				request.getUri());
		String urlPath = queryStringDecoder.getPath();

		if (!urlPath.equals("/humanEdgeWeight")) {
			writeResponseInChannelAndClose(HttpUtils
					.createUrlNotFoundResponse());
			return;
		}

		try {
			writeResponseInChannelAndClose(humanEdgeWeightController
					.processRequest(queryStringDecoder.getParameters()));
		} catch (InvalidTriangleException ex) {
			LOG.error("Incorrect pyramid parameters", ex);
			writeResponseInChannelAndClose(HttpUtils
					.createInvalidInputParametersResponse(ex.getMessage()));
		} catch (InvalidHttpParameterException ex) {
			LOG.error("Invlid request parameters", ex);
			writeResponseInChannelAndClose(HttpUtils
					.createInvalidInputParametersResponse(ex.getMessage()));
		}
	}

	private void writeResponseInChannelAndClose(HttpResponse response) {
		ChannelFuture future = messageEvent.getChannel().write(response);
		future.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,
			ExceptionEvent exceptionEvent) throws Exception {
		LOG.error("Error found handling the request", exceptionEvent.getCause());
		exceptionEvent.getChannel().close();
	}

}
