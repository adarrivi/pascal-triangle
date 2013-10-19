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
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pascal.triangle.config.HttpControllerFactory;
import com.pascal.triangle.model.exception.InvalidTriangleException;
import com.pascal.triangle.view.exception.InvalidParameterException;

@Component
@Scope("prototype")
public class NettyHttpServerHandler extends SimpleChannelUpstreamHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(NettyHttpServerHandler.class);

	@Autowired
	private HttpControllerFactory httpControllerFactory;
	@Autowired
	private HttpResponseFactory httpResponseFactory;

	private MessageEvent messageEvent;

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
		try {
			writeResponseInChannelAndClose(httpControllerFactory.getController(
					urlPath).processRequest(request));
		} catch (NoSuchBeanDefinitionException ex) {
			LOG.debug("No controller found for the url: {}", urlPath);
			writeResponseInChannelAndClose(httpResponseFactory
					.createUrlNotFoundResponse());
		} catch (InvalidTriangleException ex) {
			LOG.error("Incorrect pyramid parameters", ex);
			writeResponseInChannelAndClose(httpResponseFactory
					.createInvalidInputParametersResponse(ex.getMessage()));
		} catch (InvalidParameterException ex) {
			LOG.error("Invlid request parameters", ex);
			writeResponseInChannelAndClose(httpResponseFactory
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
