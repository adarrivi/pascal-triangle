package com.pascal.triangle.model.pyramid.impl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.pascal.triangle.model.exception.InvalidTriangleException;

public class PascalTriangleParameterVerifierTest {

	private PascalTriangleParameterVerifier victim = new PascalTriangleParameterVerifier();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private int rowIndex;
	private int columnIndex;

	@Test
	public void assertValidRowAndIndex_NegativeRow_ThrowsEx() {
		expectInvalidTriangleEx();
		givenRowIndexAndColumnIndex(-1, 0);
		whenAssertValidRowAndIndex();
	}

	private void expectInvalidTriangleEx() {
		expectedException.expect(InvalidTriangleException.class);
	}

	private void givenRowIndexAndColumnIndex(int givenRowIndex,
			int givenColumnIndex) {
		rowIndex = givenRowIndex;
		columnIndex = givenColumnIndex;
	}

	private void whenAssertValidRowAndIndex() {
		victim.assertValidRowAndIndex(rowIndex, columnIndex);
	}

	@Test
	public void assertValidRowAndIndex_IndexOutOfTriangleRow1_ThrowsEx() {
		expectInvalidTriangleEx();
		givenRowIndexAndColumnIndex(1, 2);
		whenAssertValidRowAndIndex();
	}

	@Test
	public void assertValidRowAndIndex_IndexOutOfTriangleRow3_ThrowsEx() {
		expectInvalidTriangleEx();
		givenRowIndexAndColumnIndex(3, 4);
		whenAssertValidRowAndIndex();
	}

	@Test
	public void assertValidRowAndIndex_NegativeIndex_ThrowsEx() {
		expectInvalidTriangleEx();
		givenRowIndexAndColumnIndex(3, -1);
		whenAssertValidRowAndIndex();
	}

	@Test
	public void assertValidRowAndIndex_Row0Index0() {
		givenRowIndexAndColumnIndex(0, 0);
		whenAssertValidRowAndIndex();
	}
}
