package com.pascal.triangle.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.pascal.triangle.model.exception.InvalidTriangleException;

public class PascalTriangleTest {

	private static Map<Integer, List<Double>> PASCAL_TRIANGLE;

	private PascalTriangle victim = PascalTriangle.getInstance();
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private int row;
	private int index;
	private double element;

	@BeforeClass
	public static void setUpTriangle() {
		PASCAL_TRIANGLE = new HashMap<Integer, List<Double>>();
		PASCAL_TRIANGLE.put(1, Arrays.asList(1d));
		PASCAL_TRIANGLE.put(2, Arrays.asList(1d, 1d));
		PASCAL_TRIANGLE.put(3, Arrays.asList(1d, 2d, 1d));
		PASCAL_TRIANGLE.put(4, Arrays.asList(1d, 3d, 3d, 1d));
		PASCAL_TRIANGLE.put(5, Arrays.asList(1d, 4d, 6d, 4d, 1d));
		PASCAL_TRIANGLE.put(6, Arrays.asList(1d, 5d, 10d, 10d, 5d, 1d));
	}

	@Test
	public void getElementByRowAndIndex_ZeroRow_ThrowsEx() {
		expectInvalidTriangleEx();
		givenRowAndIndex(0, 0);
		whenGetElementByRowAndIndex();
	}

	private void expectInvalidTriangleEx() {
		expectedException.expect(InvalidTriangleException.class);
	}

	private void givenRowAndIndex(int givenRow, int givenIndex) {
		row = givenRow;
		index = givenIndex;
	}

	private void whenGetElementByRowAndIndex() {
		element = victim.getElementByRowAndIndex(row, index);
	}

	@Test
	public void getElementByRowAndIndex_NegativeRow_ThrowsEx() {
		expectInvalidTriangleEx();
		givenRowAndIndex(-1, 0);
		whenGetElementByRowAndIndex();
	}

	@Test
	public void getElementByRowAndIndex_IndexOutOfTriangleRow1_ThrowsEx() {
		expectInvalidTriangleEx();
		givenRowAndIndex(1, 1);
		whenGetElementByRowAndIndex();
	}

	@Test
	public void getElementByRowAndIndex_IndexOutOfTriangleRow3_ThrowsEx() {
		expectInvalidTriangleEx();
		givenRowAndIndex(3, 3);
		whenGetElementByRowAndIndex();
	}

	@Test
	public void getElementByRowAndIndex_NegativeIndex_ThrowsEx() {
		expectInvalidTriangleEx();
		givenRowAndIndex(3, -1);
		whenGetElementByRowAndIndex();
	}

	@Test
	public void getElementByRowAndIndex_Row1Index0_ReturnsCorrectValue() {
		givenRowAndIndex(1, 0);
		whenGetElementByRowAndIndex();
		thenElementShouldBeCorrect();
	}

	private void thenElementShouldBeCorrect() {
		double expectedElement = PASCAL_TRIANGLE.get(row).get(index)
				.doubleValue();
		Assert.assertTrue(expectedElement == element);
	}

	@Test
	public void getElementByRowAndIndex_Row3Index2_ReturnsCorrectValue() {
		givenRowAndIndex(3, 2);
		whenGetElementByRowAndIndex();
		thenElementShouldBeCorrect();
	}

	@Test
	public void getElementByRowAndIndex_Row6Index5_ReturnsCorrectValue() {
		givenRowAndIndex(6, 5);
		whenGetElementByRowAndIndex();
		thenElementShouldBeCorrect();
	}

}
