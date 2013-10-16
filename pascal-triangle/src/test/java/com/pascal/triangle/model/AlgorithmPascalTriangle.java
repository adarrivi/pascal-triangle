package com.pascal.triangle.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.primitives.Doubles;

/**
 * This class solves the Human Pyramid problem in an algorithmic way without
 * recursion.<br>
 * The intention is compare the results (and as a TODO the performance?) with
 * the formula proposed in PascalTriangle
 * 
 */
public class AlgorithmPascalTriangle {

	private int maxLevelIndex;
	private int humanWeight;

	private int currentLevelIndex;
	private int maxHumanIndex;
	private double[] currentPyramidLevel;
	private double[] nextPyramidLevel;

	private Map<Integer, List<Double>> pyramidMap = new HashMap<Integer, List<Double>>();

	public AlgorithmPascalTriangle(int maxLevelIndex, int humanWeight) {
		this.maxLevelIndex = maxLevelIndex;
		this.humanWeight = humanWeight;
	}

	public Map<Integer, List<Double>> getWeightOnTheBackPyramidMap() {
		int safePyramidLevelSize = maxLevelIndex + 1;
		currentPyramidLevel = new double[safePyramidLevelSize];
		nextPyramidLevel = new double[safePyramidLevelSize];
		for (currentLevelIndex = 0; currentLevelIndex <= maxLevelIndex; currentLevelIndex++) {
			processLevel();
			currentPyramidLevel = nextPyramidLevel;
			nextPyramidLevel = new double[safePyramidLevelSize];
			maxHumanIndex++;
		}
		return pyramidMap;
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
		addCurrentLevelToPyramidMap();
	}

	private void addCurrentLevelToPyramidMap() {
		pyramidMap.put(currentLevelIndex, Doubles.asList(nextPyramidLevel)
				.subList(0, maxHumanIndex + 1));
	}
}
