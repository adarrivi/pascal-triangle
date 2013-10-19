package com.pascal.triangle.config;

import com.pascal.triangle.view.controller.HttpRequestController;

public interface HttpControllerFactory {

	HttpRequestController getController(String name);
}
