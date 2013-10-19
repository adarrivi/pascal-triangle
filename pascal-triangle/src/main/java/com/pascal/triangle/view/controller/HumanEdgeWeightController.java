package com.pascal.triangle.view.controller;

import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static org.jboss.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.util.List;
import java.util.Map;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.QueryStringDecoder;
import org.jboss.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.pascal.triangle.service.HumanPyramidService;
import com.pascal.triangle.view.exception.InvalidParameterException;

@Controller("/humanEdgeWeight")
public class HumanEdgeWeightController implements HttpRequestController {

	@Autowired
	private HumanPyramidService humanPyramidService;

	@Override
	public HttpResponse processRequest(HttpRequest request) {
		QueryStringDecoder queryStringDecoder = new QueryStringDecoder(
				request.getUri());
		Map<String, List<String>> parameters = queryStringDecoder
				.getParameters();

		Integer level = getMandatoryIntegerParameterValue("level", parameters);
		Integer index = getOptionalIntegerParameterValue("index", parameters);

		String weight = humanPyramidService.getHumanWeightOverShoulders(level,
				index);

		HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);
		response.setContent(ChannelBuffers.copiedBuffer(weight,
				CharsetUtil.UTF_8));
		response.setHeader(CONTENT_TYPE, "text/plain; charset=UTF-8");
		return response;
	}

	private Integer getMandatoryIntegerParameterValue(String parameterName,
			Map<String, List<String>> parameters) {
		Integer value = getOptionalIntegerParameterValue(parameterName,
				parameters);
		if (value == null) {
			throw new InvalidParameterException("The parameter "
					+ parameterName + " is mandatory");
		}
		return value;
	}

	private Integer getOptionalIntegerParameterValue(String parameterName,
			Map<String, List<String>> parameters) {
		String value = getOptionalParameterValue(parameterName, parameters);
		if (Strings.isNullOrEmpty(value)) {
			return null;
		}
		try {
			return Integer.valueOf(value);
		} catch (NumberFormatException ex) {
			throw new InvalidParameterException(
					"Expected an integer value for the parameter "
							+ parameterName, ex);
		}
	}

	private String getOptionalParameterValue(String parameterName,
			Map<String, List<String>> parameters) {
		List<String> values = parameters.get(parameterName);
		if (values == null) {
			return null;
		}

		if (values.size() > 1) {
			throw new InvalidParameterException(
					"Expected only one value for the parameter "
							+ parameterName);
		}
		return Iterables.getFirst(values, null);
	}

}
