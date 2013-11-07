package com.pascal.triangle.view.controller;

import java.util.List;
import java.util.Map;

import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pascal.triangle.service.HumanPyramidService;
import com.pascal.triangle.view.server.HttpResponseFactory;

@Controller("/humanEdgeWeight")
public class HumanEdgeWeightController implements HttpRequestController {

	protected static final String HUMAN_INDEX_PARAMETER = "index";

	protected static final String LEVEL_INDEX_PARAMETER = "level";

	@Autowired
	private HumanPyramidService humanPyramidService;

	@Autowired
	private HttpParameterVerifier httpParameterVerifier;

	@Autowired
	private HttpResponseFactory httpResponseFactory;

	@Override
	public HttpResponse processRequest(HttpRequest request,
			Map<String, List<String>> parameters) {
		int mb = 1024 * 1024;

		// Getting the runtime reference from system
		Runtime runtime = Runtime.getRuntime();

		System.out.println("##### Heap utilization statistics [MB] #####");

		// Print used memory
		System.out.println("Used Memory:"
				+ (runtime.totalMemory() - runtime.freeMemory()) / mb);

		// Print free memory
		System.out.println("Free Memory:" + runtime.freeMemory() / mb);

		// Print total available memory
		System.out.println("Total Memory:" + runtime.totalMemory() / mb);

		// Print Maximum available memory
		System.out.println("Max Memory:" + runtime.maxMemory() / mb);

		Integer level = httpParameterVerifier
				.getMandatoryIntegerParameterValue(LEVEL_INDEX_PARAMETER,
						parameters);
		Integer index = httpParameterVerifier.getOptionalIntegerParameterValue(
				HUMAN_INDEX_PARAMETER, parameters);

		String weight = humanPyramidService.getHumanWeightOverShoulders(level,
				index);

		HttpResponse createOkTextPlainResponse = httpResponseFactory
				.createOkTextPlainResponse(weight);

		// Getting the runtime reference from system
		runtime = Runtime.getRuntime();

		System.out.println("##### 2Heap utilization statistics [MB] #####");

		// Print used memory
		System.out.println("2Used Memory:"
				+ (runtime.totalMemory() - runtime.freeMemory()) / mb);

		// Print free memory
		System.out.println("2Free Memory:" + runtime.freeMemory() / mb);

		// Print total available memory
		System.out.println("2Total Memory:" + runtime.totalMemory() / mb);

		// Print Maximum available memory
		System.out.println("2Max Memory:" + runtime.maxMemory() / mb);
		return createOkTextPlainResponse;
	}

}
