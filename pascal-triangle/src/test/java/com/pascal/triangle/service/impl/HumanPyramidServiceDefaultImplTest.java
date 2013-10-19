package com.pascal.triangle.service.impl;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pascal.triangle.model.exception.InvalidTriangleException;
import com.pascal.triangle.model.pyramid.WeightPascalTriangleCalculator;
import com.pascal.triangle.service.HumanPyramidService;

public class HumanPyramidServiceDefaultImplTest {

	private static final int EDGE_HUMAN_INDEX = 0;

	@Mock
	private WeightPascalTriangleCalculator weightPascalTriangleCalculator;

	@InjectMocks
	private HumanPyramidService victim = new HumanPyramidServiceDefaultImpl();

	private int givenLevelIndex;
	private Integer givenHumanIndex;
	private String weight;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getHumanWeightOverShoulders_InvalidTriangleEx_ThrowsInvalidTriangleEx() {
		expectInvalidTriangleEx();
		givenInvalidTriangleEx();
		whenGetHumanWeightOverShoulders();
	}

	private void expectInvalidTriangleEx() {
		expectedException.expect(InvalidTriangleException.class);
	}

	private void givenInvalidTriangleEx() {
		doThrow(InvalidTriangleException.class).when(
				weightPascalTriangleCalculator).getWeigthShareOverShoulders(
				anyInt(), anyInt(), anyInt());
	}

	private void whenGetHumanWeightOverShoulders() {
		weight = victim.getHumanWeightOverShoulders(givenLevelIndex,
				givenHumanIndex);
	}

	@Test
	public void getHumanWeightOverShoulders_NullHumanIndex_CalculateEdgeHumanIndex() {
		givenLevelAndHumanIndex(1, null);
		whenGetHumanWeightOverShoulders();
		thenShouldCalculateEdgeHumanIndex();
	}

	private void givenLevelAndHumanIndex(int levelIndex, Integer humanIndex) {
		this.givenLevelIndex = levelIndex;
		this.givenHumanIndex = humanIndex;
	}

	private void thenShouldCalculateEdgeHumanIndex() {
		verify(weightPascalTriangleCalculator).getWeigthShareOverShoulders(
				eq(givenLevelIndex), eq(EDGE_HUMAN_INDEX), anyInt());
	}

	@Test
	public void getHumanWeightOverShoulders_WithHumanIndex_CalculatesWithHumanIndex() {
		givenLevelAndHumanIndex(1, Integer.valueOf(3));
		whenGetHumanWeightOverShoulders();
		thenShouldCalculateWithHumanIndex();
	}

	private void thenShouldCalculateWithHumanIndex() {
		verify(weightPascalTriangleCalculator).getWeigthShareOverShoulders(
				eq(givenLevelIndex), eq(givenHumanIndex), anyInt());
	}

	@Test
	public void getHumanWeightOverShoulders_ReturnsWeightAsString() {
		double weightOnShoulders = 3;
		givenLevelAndHumanIndex(1, Integer.valueOf(3));
		givenWeight(weightOnShoulders);
		whenGetHumanWeightOverShoulders();
		thenWeightShouldBe(Double.toString(weightOnShoulders));
	}

	private void givenWeight(double weight) {
		when(
				weightPascalTriangleCalculator.getWeigthShareOverShoulders(
						eq(givenLevelIndex), eq(givenHumanIndex), anyInt()))
				.thenReturn(weight);
	}

	private void thenWeightShouldBe(String expectedWeight) {
		Assert.assertEquals(expectedWeight, weight);
	}

}
