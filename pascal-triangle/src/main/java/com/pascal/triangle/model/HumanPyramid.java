package com.pascal.triangle.model;

import com.pascal.triangle.model.pyramid.WeightPascalTriangleCalculator;

public class HumanPyramid {

	private WeightPascalTriangleCalculator weightPascalTriangleCalculator;
	private int humanWeight;

	public HumanPyramid(WeightPascalTriangleCalculator weightPascalTriangleCalculator,
			int humanWeight) {
		this.weightPascalTriangleCalculator = weightPascalTriangleCalculator;
		this.humanWeight = humanWeight;
	}

	public double getHumanEdgeWeight(int levelIndex, int columnIndex) {
		return weightPascalTriangleCalculator.getWeigthShareOverShoulders(levelIndex,
				columnIndex, humanWeight);
	}

}
