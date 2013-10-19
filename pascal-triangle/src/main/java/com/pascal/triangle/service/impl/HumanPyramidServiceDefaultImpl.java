package com.pascal.triangle.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pascal.triangle.model.HumanPyramid;
import com.pascal.triangle.model.pyramid.WeightPascalTriangleCalculator;
import com.pascal.triangle.service.HumanPyramidService;

@Service
public class HumanPyramidServiceDefaultImpl implements HumanPyramidService {

	private static final Logger LOG = LoggerFactory
			.getLogger(HumanPyramidServiceDefaultImpl.class);

	private static final int EDGE_HUMAN_INDEX = 0;
	private static final int DEFAULT_WEIGHT = 50;

	@Autowired
	@Qualifier("algorithmWeightPascalTriangleCalculator")
	private WeightPascalTriangleCalculator weightPascalTriangleCalculator;

	@Override
	@Cacheable(value = "getHumanWeightOverShoulders")
	public String getHumanWeightOverShoulders(int levelIndex,
			Integer optionalHumanIndex) {
		HumanPyramid humanPyramid = new HumanPyramid(
				weightPascalTriangleCalculator, DEFAULT_WEIGHT);
		int humanIndex = optionalHumanIndex == null ? EDGE_HUMAN_INDEX
				: optionalHumanIndex.intValue();
		long currentTimeMillis = System.currentTimeMillis();
		double weight = humanPyramid.getHumanEdgeWeight(levelIndex, humanIndex);
		currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
		LOG.debug("The operation took {} milliseconds", currentTimeMillis);
		return Double.toString(weight);
	}

}
