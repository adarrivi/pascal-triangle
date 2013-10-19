package com.pascal.triangle.view.server;

import org.jboss.netty.bootstrap.ServerBootstrap;

public interface ServerBootstrapProvider {

	ServerBootstrap getServerBootstrap();
}
