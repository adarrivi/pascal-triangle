package com.pascal.triangle.service;

class AlgorithmParameters {

	int weight;
	int expectedRowIndex;
	int expectedColumnIndex;

	int currenRowIndex;
	int currentColumnIndex;
	int maxColumnIndex;

	double[] currentTriangleRow;
	double[] nextTriangleRow = new double[0];

	AlgorithmParameters(int weight, int expectedRowIndex,
			int expectedColumnIndex) {
		this.weight = weight;
		this.expectedRowIndex = expectedRowIndex;
		this.expectedColumnIndex = expectedColumnIndex;
		this.maxColumnIndex = -1;
	}

	void moveToNextRow() {
		currentTriangleRow = nextTriangleRow;
		maxColumnIndex++;
		nextTriangleRow = new double[maxColumnIndex + 1];
	}

	void addHalfWeightForCurrentRowColumnToLeftInNextRow() {
		double halfWeightToBeSupported = getWeightToBeSupportedForCurrentRowColumn() / 2;
		double weightAlreadyBeingSupported = getWeightAlreadyBeingSupportedByLeftInNextRow();
		nextTriangleRow[currentColumnIndex] = weightAlreadyBeingSupported
				+ halfWeightToBeSupported;
	}

	private double getWeightToBeSupportedForCurrentRowColumn() {
		return currentTriangleRow[currentColumnIndex] + weight;
	}

	private double getWeightAlreadyBeingSupportedByLeftInNextRow() {
		return nextTriangleRow[currentColumnIndex];
	}

	void addHalfWeightForCurrentRowColumnToRightInNextRow() {
		double halfWeightToBeSupported = getWeightToBeSupportedForCurrentRowColumn() / 2;
		double weightAlreadyBeingSupported = getWeightAlreadyBeingSupportedByRightInNextRow();
		nextTriangleRow[currentColumnIndex + 1] = weightAlreadyBeingSupported
				+ halfWeightToBeSupported;
	}

	private double getWeightAlreadyBeingSupportedByRightInNextRow() {
		return nextTriangleRow[currentColumnIndex + 1];
	}

	boolean isExpectedValueFound() {
		return expectedRowIndex == currenRowIndex
				&& expectedColumnIndex == currentColumnIndex;
	}

	double getExpectectedValue() {
		return nextTriangleRow[currentColumnIndex];
	}

}
