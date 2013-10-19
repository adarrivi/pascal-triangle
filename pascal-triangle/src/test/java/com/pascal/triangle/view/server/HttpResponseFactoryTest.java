package com.pascal.triangle.view.server;

import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.util.CharsetUtil;
import org.junit.Assert;
import org.junit.Test;

public class HttpResponseFactoryTest {

	private HttpResponseFactory victim = new HttpResponseFactory();
	private HttpResponse response;

	@Test
	public void createUrlNotFoundResponse_ReturnsNotFoundStatus() {
		response = victim.createUrlNotFoundResponse();
		thenResultHasStatus(HttpResponseStatus.NOT_FOUND);
	}

	private void thenResultHasStatus(HttpResponseStatus expectedStatus) {
		Assert.assertEquals(expectedStatus, response.getStatus());
	}

	@Test
	public void createInvalidInputParametersResponse_ReturnsPreconditionFailedStatus() {
		response = victim.createInvalidInputParametersResponse("message");
		thenResultHasStatus(HttpResponseStatus.PRECONDITION_FAILED);
	}

	@Test
	public void createInvalidInputParametersResponse_ReturnsUTF8PlainText() {
		response = victim.createInvalidInputParametersResponse("message");
		thenResultShouldBeUTF8PlainText();
	}

	private void thenResultShouldBeUTF8PlainText() {
		Assert.assertEquals("text/plain; charset=UTF-8",
				response.getHeader(HttpHeaders.Names.CONTENT_TYPE));
	}

	@Test
	public void createInvalidInputParametersResponse_ContainsMessage() {
		String message = "message";
		response = victim.createInvalidInputParametersResponse(message);
		thenResultShouldContainStringMessage(message);
	}

	private void thenResultShouldContainStringMessage(String expectedMessage) {
		Assert.assertEquals(expectedMessage,
				response.getContent().toString(CharsetUtil.UTF_8));
	}

	@Test
	public void createOkTextPlainResponse_ReturnsOkStatus() {
		response = victim.createOkTextPlainResponse("message");
		thenResultHasStatus(HttpResponseStatus.OK);
	}

	@Test
	public void createOkTextPlainResponse_ReturnsUTF8PlainText() {
		response = victim.createOkTextPlainResponse("message");
		thenResultShouldBeUTF8PlainText();
	}

	@Test
	public void createOkTextPlainResponse_ContainsMessage() {
		String message = "message";
		response = victim.createOkTextPlainResponse(message);
		thenResultShouldContainStringMessage(message);
	}

}
