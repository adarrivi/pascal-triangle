package com.pascal.triangle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pascal.triangle.model.HumanPyramid;
import com.pascal.triangle.model.pyramid.WeightPascalTriangle;
import com.pascal.triangle.service.HumanPyramidService;

@Service
public class HumanPyramidServiceDefaultImpl implements HumanPyramidService {

	private static final int EDGE_HUMAN_INDEX = 0;
	private static final int DEFAULT_WEIGHT = 50;

	@Autowired
	@Qualifier("formulaWeightPascalTriangle")
	private WeightPascalTriangle weightPascalTriangle;

	@Override
	public double getEdgeHumanWeightOverShoulders(int levelIndex) {
		HumanPyramid humanPyramid = new HumanPyramid(weightPascalTriangle,
				DEFAULT_WEIGHT);
		return humanPyramid.getHumanEdgeWeight(levelIndex, EDGE_HUMAN_INDEX);
	}

	@Override
	public double getHumanWeightOverShoulders(int levelIndex, int humanIndex) {
		HumanPyramid humanPyramid = new HumanPyramid(weightPascalTriangle,
				DEFAULT_WEIGHT);
		return humanPyramid.getHumanEdgeWeight(levelIndex, humanIndex);
	}

}
