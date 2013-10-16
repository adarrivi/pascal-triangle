package com.pascal.triangle.model.pyramid.impl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.pascal.triangle.model.exception.InvalidTriangleException;
import com.pascal.triangle.model.pyramid.WeightPascalTriangle;

public class AlgorithmWeightPascalTriangleTest {

	private static final int HUMAN_WEIGHT = 50;

	private WeightPascalTriangle victim = WeightPascalTriangleFactory
			.getInstance().createAlgorithmWeightPascalTriangle();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private int rowIndex;
	private int columnIndex;
	private double sharedWeight;

	@Test
	public void getWeigthShareOverShoulders_NegativeRow_ThrowsEx() {
		expectInvalidTriangleEx();
		givenRowIndexAndColumnIndex(-1, 0);
		whenGetWeigthShareOverShoulders();
	}

	private void expectInvalidTriangleEx() {
		expectedException.expect(InvalidTriangleException.class);
	}

	private void givenRowIndexAndColumnIndex(int givenRowIndex,
			int givenColumnIndex) {
		rowIndex = givenRowIndex;
		columnIndex = givenColumnIndex;
	}

	private void whenGetWeigthShareOverShoulders() {
		sharedWeight = victim.getWeigthShareOverShoulders(rowIndex,
				columnIndex, HUMAN_WEIGHT);
	}

	@Test
	public void getWeigthShareOverShoulders_IndexOutOfTriangleRow1_ThrowsEx() {
		expectInvalidTriangleEx();
		givenRowIndexAndColumnIndex(1, 2);
		whenGetWeigthShareOverShoulders();
	}

	@Test
	public void getWeigthShareOverShoulders_IndexOutOfTriangleRow3_ThrowsEx() {
		expectInvalidTriangleEx();
		givenRowIndexAndColumnIndex(3, 4);
		whenGetWeigthShareOverShoulders();
	}

	@Test
	public void getWeigthShareOverShoulders_NegativeIndex_ThrowsEx() {
		expectInvalidTriangleEx();
		givenRowIndexAndColumnIndex(3, -1);
		whenGetWeigthShareOverShoulders();
	}

	@Test
	public void getWeigthShareOverShoulders_Row0Index0_Returns0() {
		givenRowIndexAndColumnIndex(0, 0);
		whenGetWeigthShareOverShoulders();
		thenSharedWeightShouldBe(0);
	}

	private void thenSharedWeightShouldBe(double expectedSharedWeight) {
		Assert.assertEquals(expectedSharedWeight, sharedWeight, 0);
	}
}
