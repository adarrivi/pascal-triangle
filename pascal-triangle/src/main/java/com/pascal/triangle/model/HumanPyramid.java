package com.pascal.triangle.model;

import com.pascal.triangle.model.pyramid.WeightPascalTriangle;

public class HumanPyramid {

	private WeightPascalTriangle weightPascalTriangle;
	private int humanWeight;

	public HumanPyramid(WeightPascalTriangle weightPascalTriangle,
			int humanWeight) {
		this.weightPascalTriangle = weightPascalTriangle;
		this.humanWeight = humanWeight;
	}

	public double getHumanEdgeWeight(int levelIndex, int columnIndex) {
		return weightPascalTriangle.getWeigthShareOverShoulders(levelIndex,
				columnIndex, humanWeight);
	}

}
