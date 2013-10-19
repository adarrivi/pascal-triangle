package com.pascal.triangle.view.controller;

import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;

public interface HttpRequestController {

	HttpResponse processRequest(HttpRequest request);
}
