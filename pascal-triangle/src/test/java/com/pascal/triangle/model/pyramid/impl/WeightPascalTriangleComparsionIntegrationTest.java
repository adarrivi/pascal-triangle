package com.pascal.triangle.model.pyramid.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.google.common.collect.Iterables;
import com.pascal.triangle.config.SpringApplicationContext;
import com.pascal.triangle.model.pyramid.WeightPascalTriangleCalculator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = SpringApplicationContext.class)
public class WeightPascalTriangleComparsionIntegrationTest {

	private static final int MAX_ROWS = 30;

	private static final int HUMAN_WEIGHT = 50;

	@Autowired
	@Qualifier("formulaWeightPascalTriangleCalculator")
	private WeightPascalTriangleCalculator formulaCalculator;

	@Autowired
	@Qualifier("algorithmWeightPascalTriangleCalculator")
	private WeightPascalTriangleCalculator algorithmCalculator;

	private List<WeightPascalTriangleCalculator> victims;

	private List<Double> weights;
	private int rowIndex;
	private int columnIndex;
	private int maxColumnIndex;

	@Before
	public void setUp() {
		victims = Arrays.asList(formulaCalculator, algorithmCalculator);
	}

	@Test
	public void getWeightShouldBeSameForAllVictims() {
		maxColumnIndex = 0;
		weights = new ArrayList<Double>();
		for (rowIndex = 0; rowIndex < MAX_ROWS; rowIndex++) {
			processAllColumnsForCurrentRow();
			maxColumnIndex++;
		}
	}

	private void processAllColumnsForCurrentRow() {
		for (columnIndex = 0; columnIndex <= maxColumnIndex; columnIndex++) {
			processAllVictims();
			assertAllWeightsAreTheSame();
		}
	}

	private void processAllVictims() {
		for (WeightPascalTriangleCalculator victim : victims) {
			double weigthShareOverShoulders = victim
					.getWeigthShareOverShoulders(rowIndex, columnIndex,
							HUMAN_WEIGHT);
			weights.add(weigthShareOverShoulders);
		}
	}

	private void assertAllWeightsAreTheSame() {
		Double firstValue = Iterables.getFirst(weights, null);
		for (Double value : weights) {
			Assert.assertEquals(firstValue, value, 0);
		}
		weights.clear();
	}

}
