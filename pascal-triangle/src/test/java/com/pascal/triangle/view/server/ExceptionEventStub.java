package com.pascal.triangle.view.server;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ExceptionEvent;

public class ExceptionEventStub implements ExceptionEvent {

	private ChannelStub channel = new ChannelStub();

	public boolean isChannelClosed() {
		return !channel.isOpen();
	}

	@Override
	public Channel getChannel() {
		return channel;
	}

	@Override
	public ChannelFuture getFuture() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Throwable getCause() {
		// TODO Auto-generated method stub
		return null;
	}

}
