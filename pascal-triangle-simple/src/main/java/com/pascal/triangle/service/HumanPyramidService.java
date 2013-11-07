package com.pascal.triangle.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pascal.triangle.exception.InvalidTriangleException;

@Service
public class HumanPyramidService {

	private static final int EDGE_HUMAN_INDEX = 0;
	private static final int DEFAULT_WEIGHT = 50;

	@Cacheable(value = "getHumanWeightOverShoulders")
	public String getHumanWeightOverShoulders(int levelIndex,
			Integer optionalHumanIndex) {
		int humanIndex = optionalHumanIndex == null ? EDGE_HUMAN_INDEX
				: optionalHumanIndex.intValue();

		double weight = getWeigthShareOverShoulders(levelIndex, humanIndex);

		return Double.toString(weight);
	}

	public double getWeigthShareOverShoulders(int rowIndex, int columnIndex) {
		assertValidRowAndIndex(rowIndex, columnIndex);

		AlgorithmParameters parameters = new AlgorithmParameters(
				DEFAULT_WEIGHT, rowIndex, columnIndex);

		for (parameters.currenRowIndex = 0; parameters.currenRowIndex <= rowIndex; parameters.currenRowIndex++) {
			parameters.moveToNextRow();
			processRowUntilExpectedValueFound(parameters);
		}
		return parameters.getExpectectedValue();
	}

	private void assertValidRowAndIndex(int rowIndex, int columnIndex) {
		assertNoNegativeRowIndex(rowIndex);
		assertIndexWithinRowIndex(columnIndex, rowIndex);
	}

	private void assertNoNegativeRowIndex(int rowIndex) {
		if (rowIndex < 0) {
			throw new InvalidTriangleException(
					"Invalid row. It cannot be lower than 0");
		}
	}

	private void assertIndexWithinRowIndex(int columnIndex, int rowIndex) {
		int maximumIndexAllowedPerRow = rowIndex;
		if (columnIndex > maximumIndexAllowedPerRow) {
			StringBuffer sb = new StringBuffer();
			sb.append("Invalid index ").append(columnIndex)
					.append(" for the given row ").append(rowIndex)
					.append(". Must equals or lower than ")
					.append(maximumIndexAllowedPerRow);
			throw new InvalidTriangleException(sb.toString());
		}
		if (columnIndex < 0) {
			throw new InvalidTriangleException(
					"Invalid index. It cannot be lower than 0");
		}
	}

	private void processRowUntilExpectedValueFound(
			AlgorithmParameters parameters) {
		for (parameters.currentColumnIndex = 0; parameters.currentColumnIndex < parameters.maxColumnIndex; parameters.currentColumnIndex++) {
			parameters.addHalfWeightForCurrentRowColumnToLeftInNextRow();
			if (parameters.isExpectedValueFound()) {
				return;
			}
			parameters.addHalfWeightForCurrentRowColumnToRightInNextRow();
		}
	}

}
