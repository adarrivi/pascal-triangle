package com.pascal.triangle.service;

import com.pascal.triangle.exception.InvalidPyramidException;

/**
 * This class wraps and holds all the parameters and variables needed to solve
 * the humanPyramid problem. It also verifies if the input parameters are valid.
 * The main reason of this class is to simplify the methods in
 * HymanPyramidService. Without it, all the indexes and pyramid rows would need
 * to be passed in each HumanPyramidParameterService private method
 * 
 * @author adarrivi
 * 
 */
class HumanPyramidParameterWrapper {

	int currenRowIndex;
	int currentColumnIndex;
	int maxColumnIndex;

	int expectedRowIndex;
	int expectedColumnIndex;

	double[] currentPyramidRow;
	double[] nextPyramidRow;

	HumanPyramidParameterWrapper(int expectedRowIndex, int expectedColumnIndex) {
		assertValidRowAndIndex(expectedRowIndex, expectedColumnIndex);
		this.expectedRowIndex = expectedRowIndex;
		this.expectedColumnIndex = expectedColumnIndex;
		this.maxColumnIndex = -1;
		this.currentPyramidRow = new double[expectedRowIndex];
		this.nextPyramidRow = new double[expectedRowIndex + 1];
	}

	private void assertValidRowAndIndex(int rowIndex, int columnIndex) {
		assertNoNegativeRowIndex(rowIndex);
		assertIndexWithinRowIndex(columnIndex, rowIndex);
	}

	private void assertNoNegativeRowIndex(int rowIndex) {
		if (rowIndex < 0) {
			throw new InvalidPyramidException(
					"Invalid row. It cannot be lower than 0");
		}
	}

	private void assertIndexWithinRowIndex(int columnIndex, int rowIndex) {
		int maximumIndexAllowedPerRow = rowIndex;
		if (columnIndex > maximumIndexAllowedPerRow) {
			StringBuffer sb = new StringBuffer();
			sb.append("Invalid index ").append(columnIndex)
					.append(" for the given row ").append(rowIndex)
					.append(". Must equals or lower than ")
					.append(maximumIndexAllowedPerRow);
			throw new InvalidPyramidException(sb.toString());
		}
		if (columnIndex < 0) {
			throw new InvalidPyramidException(
					"Invalid index. It cannot be lower than 0");
		}
	}

}
