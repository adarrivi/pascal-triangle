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
		Integer level = httpParameterVerifier
				.getMandatoryIntegerParameterValue(LEVEL_INDEX_PARAMETER,
						parameters);
		Integer index = httpParameterVerifier.getOptionalIntegerParameterValue(
				HUMAN_INDEX_PARAMETER, parameters);

		String weight = humanPyramidService.getHumanWeightOverShoulders(level,
				index);

		return httpResponseFactory.createOkTextPlainResponse(weight);
	}

}
