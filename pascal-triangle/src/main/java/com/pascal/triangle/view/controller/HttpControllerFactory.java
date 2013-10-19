package com.pascal.triangle.view.controller;


public interface HttpControllerFactory {

	HttpRequestController getController(String name);
}
