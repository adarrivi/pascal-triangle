package com.pascal.triangle.view.server;

import java.util.concurrent.TimeUnit;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;

public class ChannelFutureStub implements ChannelFuture {

	private boolean addClose;

	boolean isAddClose() {
		return addClose;
	}

	@Override
	public Channel getChannel() {
		return null;
	}

	@Override
	public boolean isDone() {
		return false;
	}

	@Override
	public boolean isCancelled() {
		return false;
	}

	@Override
	public boolean isSuccess() {
		return false;
	}

	@Override
	public Throwable getCause() {
		return null;
	}

	@Override
	public boolean cancel() {
		return false;
	}

	@Override
	public boolean setSuccess() {
		return false;
	}

	@Override
	public boolean setFailure(Throwable cause) {
		return false;
	}

	@Override
	public boolean setProgress(long amount, long current, long total) {
		return false;
	}

	@Override
	public void addListener(ChannelFutureListener listener) {
		if (ChannelFutureListener.CLOSE.equals(listener)) {
			addClose = true;
		}
	}

	@Override
	public void removeListener(ChannelFutureListener listener) {
	}

	@Override
	public ChannelFuture rethrowIfFailed() throws Exception {
		return null;
	}

	@Override
	public ChannelFuture sync() throws InterruptedException {
		return null;
	}

	@Override
	public ChannelFuture syncUninterruptibly() {
		return null;
	}

	@Override
	public ChannelFuture await() throws InterruptedException {
		return null;
	}

	@Override
	public ChannelFuture awaitUninterruptibly() {
		return null;
	}

	@Override
	public boolean await(long timeout, TimeUnit unit)
			throws InterruptedException {
		return false;
	}

	@Override
	public boolean await(long timeoutMillis) throws InterruptedException {
		return false;
	}

	@Override
	public boolean awaitUninterruptibly(long timeout, TimeUnit unit) {
		return false;
	}

	@Override
	public boolean awaitUninterruptibly(long timeoutMillis) {
		return false;
	}

}
