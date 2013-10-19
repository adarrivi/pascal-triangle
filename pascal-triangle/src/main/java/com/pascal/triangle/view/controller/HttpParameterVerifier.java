package com.pascal.triangle.view.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.pascal.triangle.view.exception.InvalidParameterException;

@Service
public class HttpParameterVerifier {

	public Integer getMandatoryIntegerParameterValue(String parameterName,
			Map<String, List<String>> parameters) {
		Integer value = getOptionalIntegerParameterValue(parameterName,
				parameters);
		if (value == null) {
			throw new InvalidParameterException("The parameter "
					+ parameterName + " is mandatory");
		}
		return value;
	}

	public Integer getOptionalIntegerParameterValue(String parameterName,
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
		if (parameters == null) {
			return null;
		}
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
