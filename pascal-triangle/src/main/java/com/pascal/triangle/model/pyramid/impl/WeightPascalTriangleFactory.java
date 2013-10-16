package com.pascal.triangle.model.pyramid.impl;

import com.pascal.triangle.model.pyramid.WeightPascalTriangle;

public class WeightPascalTriangleFactory {

	private static final WeightPascalTriangleFactory INSTANCE = new WeightPascalTriangleFactory();

	private WeightPascalTriangleFactory() {
		// To limit scope
	}

	public static WeightPascalTriangleFactory getInstance() {
		return INSTANCE;
	}

	public WeightPascalTriangle createFormulaWeightPascalTriangle() {
		return new FormulaWeightPascalTriangle();
	}

	public WeightPascalTriangle createAlgorithmWeightPascalTriangle() {
		return new AlgorithmWeightPascalTriangle();
	}

}
