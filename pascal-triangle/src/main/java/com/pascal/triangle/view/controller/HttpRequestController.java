package com.pascal.triangle.view.controller;

import java.util.List;
import java.util.Map;

import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;

public interface HttpRequestController {

	HttpResponse processRequest(HttpRequest request,
			Map<String, List<String>> parameters);
}
