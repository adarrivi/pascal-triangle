package com.pascal.triangle.model.pyramid.impl;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;

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

public class AlgorithmWeightPascalTriangleCalculatorTest {

	private static final int HUMAN_WEIGHT = 50;

	@Mock
	private PascalTriangleParameterVerifier parameterVerifier;

	@InjectMocks
	private WeightPascalTriangleCalculator victim = new AlgorithmWeightPascalTriangleCalculator();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private int rowIndex;
	private int columnIndex;
	private double sharedWeight;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getWeigthShareOverShoulders_AssertRowThrowsEx_ThrowsEx() {
		expectInvalidTriangleEx();
		givenAssertRowAndIndexEx();
		whenGetWeigthShareOverShoulders();
	}

	private void expectInvalidTriangleEx() {
		expectedException.expect(InvalidTriangleException.class);
	}

	private void givenAssertRowAndIndexEx() {
		doThrow(InvalidTriangleException.class).when(parameterVerifier)
				.assertValidRowAndIndex(anyInt(), anyInt());
	}

	private void whenGetWeigthShareOverShoulders() {
		sharedWeight = victim.getWeigthShareOverShoulders(rowIndex,
				columnIndex, HUMAN_WEIGHT);
	}

	@Test
	public void getWeigthShareOverShoulders_Row0Index0_Returns0() {
		givenRowIndexAndColumnIndex(0, 0);
		whenGetWeigthShareOverShoulders();
		thenSharedWeightShouldBe(0);
	}

	private void givenRowIndexAndColumnIndex(int givenRowIndex,
			int givenColumnIndex) {
		rowIndex = givenRowIndex;
		columnIndex = givenColumnIndex;
	}

	private void thenSharedWeightShouldBe(double expectedSharedWeight) {
		Assert.assertEquals(expectedSharedWeight, sharedWeight, 0);
	}

	@Test
	public void getWeigthShareOverShoulders_Row1Index1_Returns0() {
		givenRowIndexAndColumnIndex(1, 1);
		whenGetWeigthShareOverShoulders();
		thenSharedWeightShouldBe(25);
	}

	@Test
	public void getWeigthShareOverShoulders_Row4Index3_Returns4() {
		givenRowIndexAndColumnIndex(4, 3);
		whenGetWeigthShareOverShoulders();
		thenSharedWeightShouldBe(125);
	}

}
