package com.pascal.triangle.view.server;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.pascal.triangle.model.exception.InvalidTriangleException;
import com.pascal.triangle.view.controller.HttpControllerFactory;
import com.pascal.triangle.view.controller.HttpRequestController;
import com.pascal.triangle.view.exception.InvalidParameterException;

public class NettyHttpServerHandlerTest {

	@Mock
	private HttpControllerFactory httpControllerFactory;
	@Mock
	private HttpResponseFactory httpResponseFactory;
	@InjectMocks
	private NettyHttpServerHandler victim = new NettyHttpServerHandler();

	@Mock
	private HttpRequestController httpRequestController;

	private MessageEventStub messageEvent;
	private ExceptionEventStub exceptionEvent;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		messageEvent = new MessageEventStub();
		exceptionEvent = new ExceptionEventStub();
	}

	@Test
	public void messageReceived_ControllerNotFound_CreatesNotFoundUrlResponse()
			throws Exception {
		givenControllerNotFound();
		whenMesasgeReceived();
		thenNotFoundUrlResponseShouldBeCreated();
	}

	private void givenControllerNotFound() {
		doThrow(NoSuchBeanDefinitionException.class)
				.when(httpControllerFactory).getController(anyString());
	}

	private void whenMesasgeReceived() throws Exception {
		victim.messageReceived(null, messageEvent);
	}

	private void thenNotFoundUrlResponseShouldBeCreated() {
		verify(httpResponseFactory).createUrlNotFoundResponse();
	}

	@Test
	public void messageReceived_ControllerNotFound_CloseAddedToChannelFuture()
			throws Exception {
		givenControllerNotFound();
		whenMesasgeReceived();
		thenCloseShouldBeAddedToChannelFuture();
	}

	private void thenCloseShouldBeAddedToChannelFuture() {
		Assert.assertTrue(messageEvent.isChannelAddClose());
	}

	@Test
	public void messageReceived_InvalidTriangleException_CreatesInvalidInputParameterResponse()
			throws Exception {
		givenException(InvalidTriangleException.class);
		whenMesasgeReceived();
		thenInvalidInputParameterShouldBeCreated();
	}

	private void givenException(
			Class<? extends Exception> expectedExceptionClass) {
		doThrow(expectedExceptionClass).when(httpControllerFactory)
				.getController(anyString());
	}

	private void thenInvalidInputParameterShouldBeCreated() {
		verify(httpResponseFactory).createInvalidInputParametersResponse(
				anyString());
	}

	@Test
	public void messageReceived_InvalidTriangleException_CloseAddedToChannelFuture()
			throws Exception {
		givenException(InvalidTriangleException.class);
		whenMesasgeReceived();
		thenCloseShouldBeAddedToChannelFuture();
	}

	@Test
	public void messageReceived_InvalidParameterException_CreatesInvalidInputParameterResponse()
			throws Exception {
		givenException(InvalidParameterException.class);
		whenMesasgeReceived();
		thenInvalidInputParameterShouldBeCreated();
	}

	@Test
	public void messageReceived_InvalidParameterException_CloseAddedToChannelFuture()
			throws Exception {
		givenException(InvalidParameterException.class);
		whenMesasgeReceived();
		thenCloseShouldBeAddedToChannelFuture();
	}

	@Test
	public void messageReceived_ControllerFound_CloseAddedToChannelFuture()
			throws Exception {
		givenControllerFound();
		whenMesasgeReceived();
		thenCloseShouldBeAddedToChannelFuture();
	}

	private void givenControllerFound() {
		when(httpControllerFactory.getController(anyString())).thenReturn(
				httpRequestController);
	}

	@Test
	public void exceptionCaught_ClosesChannel() throws Exception {
		whenExceptionCaught();
		thenChannelShouldBeClosed();
	}

	private void whenExceptionCaught() throws Exception {
		victim.exceptionCaught(null, exceptionEvent);
	}

	private void thenChannelShouldBeClosed() {
		Assert.assertTrue(exceptionEvent.isChannelClosed());
	}

}
