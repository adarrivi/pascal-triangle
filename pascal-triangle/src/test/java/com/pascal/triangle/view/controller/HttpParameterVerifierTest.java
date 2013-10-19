package com.pascal.triangle.view.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.pascal.triangle.view.exception.InvalidParameterException;

public class HttpParameterVerifierTest {

	private static final String PARAMETER_NAME = "level";

	private HttpParameterVerifier victim = new HttpParameterVerifier();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private Map<String, List<String>> parameters;
	private Integer parameterValue;
	private String givenParameterName;

	@Test
	public void getOptionalIntegerParameterValue_NullParameters_ReturnNull() {
		givenNullParameters();
		whenGetOptionalIntegerParameterValue();
		thenParameterValueShouldBe(null);
	}

	private void givenNullParameters() {
		parameters = null;
	}

	private void whenGetOptionalIntegerParameterValue() {
		parameterValue = victim.getOptionalIntegerParameterValue(
				givenParameterName, parameters);
	}

	private void thenParameterValueShouldBe(Integer expectedValue) {
		Assert.assertEquals(expectedValue, parameterValue);
	}

	@Test
	public void getOptionalIntegerParameterValue_EmptyParameters_ReturnNull() {
		givenEmptyParameters();
		whenGetOptionalIntegerParameterValue();
		thenParameterValueShouldBe(null);
	}

	private void givenEmptyParameters() {
		parameters = new HashMap<String, List<String>>();
	}

	@Test
	public void getOptionalIntegerParameterValue_DoesntExist_ReturnNull() {
		givenOtherParameter();
		whenGetOptionalIntegerParameterValue();
		thenParameterValueShouldBe(null);
	}

	private void givenOtherParameter() {
		givenParameter("otherParameter", "3");
		givenParameterName = PARAMETER_NAME;
	}

	private void givenParameter(String parameterName, String... values) {
		givenParameterName = parameterName;
		givenEmptyParameters();
		parameters.put(givenParameterName, Arrays.asList(values));
	}

	@Test
	public void getOptionalIntegerParameterValue_Exists_ReturnIntegerValue() {
		Integer expectedValue = Integer.valueOf(3);
		givenParameter(PARAMETER_NAME, expectedValue.toString());
		whenGetOptionalIntegerParameterValue();
		thenParameterValueShouldBe(expectedValue);
	}

	@Test
	public void getOptionalIntegerParameterValue_MultipleValues_ThrowsInvalidEx() {
		expectInvalidEx();
		Integer expectedValue = Integer.valueOf(3);
		givenParameter(PARAMETER_NAME, expectedValue.toString(),
				expectedValue.toString());
		whenGetOptionalIntegerParameterValue();
	}

	private void expectInvalidEx() {
		expectedException.expect(InvalidParameterException.class);
	}

	@Test
	public void getOptionalIntegerParameterValue_InvalidInteger_ThrowsInvalidEx() {
		expectInvalidEx();
		givenParameter(PARAMETER_NAME, "a");
		whenGetOptionalIntegerParameterValue();
	}

	@Test
	public void getMandatoryIntegerParameterValue_NullParameters_ThrowsInvalidEx() {
		expectInvalidEx();
		givenNullParameters();
		whenGetMandatoryIntegerParameterValue();
	}

	private void whenGetMandatoryIntegerParameterValue() {
		parameterValue = victim.getMandatoryIntegerParameterValue(
				givenParameterName, parameters);

	}

	@Test
	public void getMandatoryIntegerParameterValue_EmptyParameters_ThrowsInvalidEx() {
		expectInvalidEx();
		givenEmptyParameters();
		whenGetMandatoryIntegerParameterValue();
	}

	@Test
	public void getMandatoryIntegerParameterValue_DontExist_ThrowsInvalidEx() {
		expectInvalidEx();
		givenOtherParameter();
		whenGetMandatoryIntegerParameterValue();
	}

	@Test
	public void getMandatoryIntegerParameterValue_InvalidInteger_ThrowsInvalidEx() {
		expectInvalidEx();
		givenParameter(PARAMETER_NAME, "a");
		whenGetMandatoryIntegerParameterValue();
	}

	@Test
	public void getMandatoryIntegerParameterValue_MultipleValues_ThrowsInvalidEx() {
		Integer expectedValue = Integer.valueOf(3);
		expectInvalidEx();
		givenParameter(PARAMETER_NAME, expectedValue.toString(),
				expectedValue.toString());
		whenGetMandatoryIntegerParameterValue();
	}

	@Test
	public void getMandatoryIntegerParameterValue_Exist_ReturnsInteger() {
		Integer expectedValue = Integer.valueOf(3);
		givenParameter(PARAMETER_NAME, expectedValue.toString());
		whenGetMandatoryIntegerParameterValue();
		thenParameterValueShouldBe(expectedValue);
	}
}
