package com.pascal.triangle.http.util;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.jboss.netty.util.CharsetUtil;

/**
 * Utility class to create different http responses
 * 
 * @author adarrivi
 * 
 */
public class HttpUtils {

	public static HttpResponse createUrlNotFoundResponse() {
		return new DefaultHttpResponse(HttpVersion.HTTP_1_1,
				HttpResponseStatus.NOT_FOUND);
	}

	public static HttpResponse createInvalidInputParametersResponse(
			String message) {
		HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1,
				HttpResponseStatus.PRECONDITION_FAILED);
		return setPlainUTF8TextContent(message, response);
	}

	public static HttpResponse createOkResponse(String content) {
		HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1,
				HttpResponseStatus.OK);
		return setPlainUTF8TextContent(content, response);
	}

	private static HttpResponse setPlainUTF8TextContent(String content,
			HttpResponse response) {
		response.setContent(ChannelBuffers.copiedBuffer(content,
				CharsetUtil.UTF_8));
		response.setHeader(HttpHeaders.Names.CONTENT_TYPE,
				"text/plain; charset=UTF-8");
		return response;
	}

}
