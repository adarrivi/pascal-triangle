package com.pascal.triangle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pascal.triangle.model.HumanPyramid;
import com.pascal.triangle.model.pyramid.WeightPascalTriangleCalculator;
import com.pascal.triangle.service.HumanPyramidService;

@Service
public class HumanPyramidServiceDefaultImpl implements HumanPyramidService {

	private static final int EDGE_HUMAN_INDEX = 0;
	private static final int DEFAULT_WEIGHT = 50;

	@Autowired
	@Qualifier("formulaWeightPascalTriangleCalculator")
	private WeightPascalTriangleCalculator weightPascalTriangleCalculator;

	@Override
	public String getHumanWeightOverShoulders(int levelIndex,
			Integer optionalHumanIndex) {
		HumanPyramid humanPyramid = new HumanPyramid(
				weightPascalTriangleCalculator, DEFAULT_WEIGHT);
		int humanIndex = optionalHumanIndex == null ? EDGE_HUMAN_INDEX
				: optionalHumanIndex.intValue();
		double weight = humanPyramid.getHumanEdgeWeight(levelIndex, humanIndex);
		return Double.toString(weight);
	}

}
