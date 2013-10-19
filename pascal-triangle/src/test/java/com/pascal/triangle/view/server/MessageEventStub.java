package com.pascal.triangle.view.server;

import java.net.SocketAddress;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpVersion;

class MessageEventStub implements MessageEvent {

	private HttpRequest request = new DefaultHttpRequest(HttpVersion.HTTP_1_1,
			HttpMethod.GET, "humanEdgeWeight?level=6&index=5");
	private ChannelStub channel = new ChannelStub();

	MessageEventStub() {
		// To limit scope
	}

	public boolean isChannelAddClose() {
		return channel.isChannelAddClose();
	}

	@Override
	public Channel getChannel() {
		return channel;
	}

	@Override
	public ChannelFuture getFuture() {
		return null;
	}

	@Override
	public Object getMessage() {
		return request;
	}

	@Override
	public SocketAddress getRemoteAddress() {
		return null;
	}

}
