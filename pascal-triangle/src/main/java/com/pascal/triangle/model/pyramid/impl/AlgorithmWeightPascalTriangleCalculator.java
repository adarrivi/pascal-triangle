package com.pascal.triangle.model.pyramid.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pascal.triangle.model.pyramid.WeightPascalTriangleCalculator;

/**
 * This class solves the Human Pyramid problem in an algorithmic way without
 * recursion.<br>
 * The intention is compare the results (and as a TODO the performance?) with
 * the formula proposed in {@link FormulaWeightPascalTriangleCalculator}
 * 
 */
@Component("algorithmWeightPascalTriangleCalculator")
class AlgorithmWeightPascalTriangleCalculator implements
		WeightPascalTriangleCalculator {

	private static final int UNEXPECTED_WEIGHT = 1;

	@Autowired
	private PascalTriangleParameterVerifier parameterVerifier;

	private int maxLevelIndex;
	private int humanWeight;

	private int currentLevelIndex;
	private int maxHumanIndex;

	private double[] currentPyramidLevel;
	private double[] nextPyramidLevel;

	AlgorithmWeightPascalTriangleCalculator() {
		// to limit scope
	}

	@Override
	public double getWeigthShareOverShoulders(int rowIndex, int columnIndex,
			int humanWeight) {
		parameterVerifier.assertValidRowAndIndex(rowIndex, columnIndex);
		this.maxLevelIndex = rowIndex;
		this.humanWeight = humanWeight;
		this.maxHumanIndex = 0;

		int safePyramidLevelSize = maxLevelIndex + 1;
		currentPyramidLevel = new double[safePyramidLevelSize];
		nextPyramidLevel = new double[safePyramidLevelSize];
		for (currentLevelIndex = 0; currentLevelIndex <= maxLevelIndex; currentLevelIndex++) {
			processLevel();
			if (currentLevelIndex == rowIndex) {
				return nextPyramidLevel[columnIndex];
			}
			currentPyramidLevel = nextPyramidLevel;
			nextPyramidLevel = new double[safePyramidLevelSize];
			maxHumanIndex++;
		}
		// TODO This line will never be executed. Refactor the algorithm so it
		// can be removed
		return -UNEXPECTED_WEIGHT;
	}

	private void processLevel() {
		for (int humanIndex = 0; humanIndex < maxHumanIndex; humanIndex++) {
			double weightToBeSupported = (currentPyramidLevel[humanIndex] + humanWeight) / 2;
			double weightAlreadyBeingSupportedLeft = nextPyramidLevel[humanIndex];
			double weightAlreadyBeingSupportedRight = nextPyramidLevel[humanIndex + 1];
			nextPyramidLevel[humanIndex] = weightAlreadyBeingSupportedLeft
					+ weightToBeSupported;
			nextPyramidLevel[humanIndex + 1] = weightAlreadyBeingSupportedRight
					+ weightToBeSupported;
		}
	}

}
