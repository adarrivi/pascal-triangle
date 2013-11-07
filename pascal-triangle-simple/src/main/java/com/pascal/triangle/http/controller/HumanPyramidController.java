package com.pascal.triangle.http.controller;

import java.util.List;
import java.util.Map;

import org.jboss.netty.handler.codec.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.pascal.triangle.exception.InvalidHttpParameterException;
import com.pascal.triangle.http.util.HttpUtils;
import com.pascal.triangle.service.HumanPyramidService;

/**
 * 'Singleton' class (thanks to Spring and @Controller), that will process all
 * human pyramid Http requests
 * 
 * In NettyHttpServerHandler you can see the url assigned to this controller
 * 
 * @author adarrivi
 * 
 */
@Controller
public class HumanPyramidController {

	@Autowired
	private HumanPyramidService humanPyramidService;

	public HttpResponse processRequest(Map<String, List<String>> parameters) {
		Integer level = getMandatoryIntegerValue("level", parameters);
		Integer index = getOptionalIntegerValue("index", parameters);

		String weight = humanPyramidService.getHumanWeightOverShoulders(level,
				index);

		return HttpUtils.createOkResponse(weight);
	}

	private Integer getMandatoryIntegerValue(String parameterName,
			Map<String, List<String>> parameters) {
		Integer value = getOptionalIntegerValue(parameterName, parameters);
		if (value == null) {
			throw new InvalidHttpParameterException("The parameter "
					+ parameterName + " is mandatory");
		}
		return value;
	}

	private Integer getOptionalIntegerValue(String parameterName,
			Map<String, List<String>> parameters) {
		String value = getOptionalParameterValue(parameterName, parameters);
		if (Strings.isNullOrEmpty(value)) {
			return null;
		}
		try {
			return Integer.valueOf(value);
		} catch (NumberFormatException ex) {
			throw new InvalidHttpParameterException(
					"Expected an integer value for the parameter "
							+ parameterName, ex);
		}
	}

	private String getOptionalParameterValue(String parameterName,
			Map<String, List<String>> parameters) {
		if (parameters == null) {
			return null;
		}
		List<String> values = parameters.get(parameterName);
		if (values == null) {
			return null;
		}

		if (values.size() > 1) {
			throw new InvalidHttpParameterException(
					"Expected only one value for the parameter "
							+ parameterName);
		}
		return Iterables.getFirst(values, null);
	}

}
