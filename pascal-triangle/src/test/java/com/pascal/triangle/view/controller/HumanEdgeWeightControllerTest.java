package com.pascal.triangle.view.controller;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.pascal.triangle.service.HumanPyramidService;
import com.pascal.triangle.view.server.HttpResponseFactory;

public class HumanEdgeWeightControllerTest {

	@Mock
	private HumanPyramidService humanPyramidService;

	@Mock
	private HttpParameterVerifier httpParameterVerifier;

	@Mock
	private HttpResponseFactory httpResponseFactory;

	@InjectMocks
	private HumanEdgeWeightController victim = new HumanEdgeWeightController();

	@Mock
	private HttpRequest request;
	@Mock
	private Map<String, List<String>> parameters;
	private HttpResponse response;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void processRequest_MandatoryCheckOnLevelParameter() {
		whenProcessRequest();
		thenMandatoryCheckShouldBeDoneOver(HumanEdgeWeightController.LEVEL_INDEX_PARAMETER);
	}

	private void whenProcessRequest() {
		response = victim.processRequest(request, parameters);
	}

	private void thenMandatoryCheckShouldBeDoneOver(String parameter) {
		Mockito.verify(httpParameterVerifier)
				.getMandatoryIntegerParameterValue(parameter, parameters);
	}

	@Test
	public void processRequest_OptionalCheckOnIndexParameter() {
		whenProcessRequest();
		thenOptionalCheckShouldBeDoneOver(HumanEdgeWeightController.HUMAN_INDEX_PARAMETER);
	}

	private void thenOptionalCheckShouldBeDoneOver(String parameter) {
		verify(httpParameterVerifier).getOptionalIntegerParameterValue(
				parameter, parameters);
	}

	@Test
	public void processRequest_ResponseShouldContainServiceResult() {
		String resultWeight = "25.0";
		givenServiceExecution(resultWeight);
		whenProcessRequest();
		thenResultShouldBeOkTestPlainWithContent(resultWeight);
	}

	private void givenServiceExecution(String resultWeight) {
		when(
				humanPyramidService.getHumanWeightOverShoulders(anyInt(),
						anyInt())).thenReturn(resultWeight);
	}

	private void thenResultShouldBeOkTestPlainWithContent(String expectedContent) {
		verify(httpResponseFactory).createOkTextPlainResponse(expectedContent);
	}

}
