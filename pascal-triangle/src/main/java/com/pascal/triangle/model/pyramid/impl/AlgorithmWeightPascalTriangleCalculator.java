package com.pascal.triangle.model.pyramid.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pascal.triangle.model.pyramid.WeightPascalTriangleCalculator;

/**
 * This class solves the Human Pyramid problem in an algorithmic way without
 * recursion.<br>
 * It solves the problem of calculating the weight for triangles higher than 30
 * rows that {@link FormulaWeightPascalTriangleCalculator} has.
 * 
 */
@Component("algorithmWeightPascalTriangleCalculator")
class AlgorithmWeightPascalTriangleCalculator implements
		WeightPascalTriangleCalculator {

	@Autowired
	private PascalTriangleParameterVerifier parameterVerifier;

	AlgorithmWeightPascalTriangleCalculator() {
		// to limit scope
	}

	@Override
	public double getWeigthShareOverShoulders(int rowIndex, int columnIndex,
			int weight) {
		parameterVerifier.assertValidRowAndIndex(rowIndex, columnIndex);

		AlgorithmParameters parameters = new AlgorithmParameters(weight,
				rowIndex, columnIndex);

		for (parameters.currenRowIndex = 0; parameters.currenRowIndex <= rowIndex; parameters.currenRowIndex++) {
			parameters.moveToNextRow();
			processRowUntilExpectedValueFound(parameters);
		}
		return parameters.getExpectectedValue();
	}

	private void processRowUntilExpectedValueFound(
			AlgorithmParameters parameters) {
		for (parameters.currentColumnIndex = 0; parameters.currentColumnIndex < parameters.maxColumnIndex; parameters.currentColumnIndex++) {
			parameters.addHalfWeightForCurrentRowColumnToLeftInNextRow();
			if (parameters.isExpectedValueFound()) {
				return;
			}
			parameters.addHalfWeightForCurrentRowColumnToRightInNextRow();
		}
	}

}
