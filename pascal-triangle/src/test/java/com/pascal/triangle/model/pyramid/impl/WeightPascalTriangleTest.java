package com.pascal.triangle.model.pyramid.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.pascal.triangle.model.pyramid.WeightPascalTriangle;

public class WeightPascalTriangleTest {

	private static final int MAX_ROWS = 30;

	private static final int HUMAN_WEIGHT = 50;

	private List<WeightPascalTriangle> victims = Arrays.asList(
			WeightPascalTriangleFactory.getInstance()
					.createAlgorithmWeightPascalTriangle(),
			WeightPascalTriangleFactory.getInstance()
					.createFormulaWeightPascalTriangle());

	private List<Double> weights;
	private int rowIndex;
	private int columnIndex;
	private int maxColumnIndex;

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
		for (WeightPascalTriangle victim : victims) {
			double weigthShareOverShoulders = victim
					.getWeigthShareOverShoulders(rowIndex, columnIndex,
							HUMAN_WEIGHT);
			weights.add(weigthShareOverShoulders);
		}
	}

	private void assertAllWeightsAreTheSame() {
		Double firstValue = weights.get(0);
		for (Double value : weights) {
			// TODO with more than 30 levels, the delta is very big. Look for an
			// alternative (maybe BigIteger or guava?)
			Assert.assertEquals(firstValue, value, 0.0000001);
		}
		weights.clear();
	}

}
