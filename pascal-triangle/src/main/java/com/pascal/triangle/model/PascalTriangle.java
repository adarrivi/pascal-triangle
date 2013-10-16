package com.pascal.triangle.model;

import org.apache.commons.math.util.MathUtils;

import com.pascal.triangle.model.exception.InvalidTriangleException;

public class PascalTriangle {

	private static final PascalTriangle INSTANCE = new PascalTriangle();

	private PascalTriangle() {
		// To limit scope
	}

	public static PascalTriangle getInstance() {
		return INSTANCE;
	}

	public int getElementByRowAndIndex(int row, int index) {
		assertValidRowAndIndex(row, index);
		int elementPosition = index + 1;
		double element = MathUtils.factorial(row)
				/ (MathUtils.factorial(elementPosition) / MathUtils
						.factorial(row - elementPosition));
		return Double.valueOf(element).intValue();
	}

	private void assertValidRowAndIndex(int row, int index) {
		assertNotZeroNegativeRow(row);
		assertIndexWithinRow(index, row);
	}

	private void assertNotZeroNegativeRow(int row) {
		if (row < 1) {
			throw new InvalidTriangleException(
					"Invalid row; must be greater than 0");
		}
	}

	private void assertIndexWithinRow(int index, int row) {
		int maximumIndexAllowedPerRow = row - 1;
		if (index > maximumIndexAllowedPerRow) {
			StringBuffer sb = new StringBuffer();
			sb.append("Invalid index ").append(index)
					.append(" for the given row ").append(row)
					.append(". Must equals or lower than ")
					.append(maximumIndexAllowedPerRow);
			throw new InvalidTriangleException(sb.toString());
		}
		if (index < 0) {
			throw new InvalidTriangleException(
					"Invalid index. It cannot be lower than 0");
		}
	}

	public int getElementSumByRow(int row) {
		assertNotZeroNegativeRow(row);
		return MathUtils.pow(2, row - 1);
	}

	public int getAggregatedNumberOfItemsUntilRow(int row) {
		assertNotZeroNegativeRow(row);
		return summatoryOneTillValue(row);
	}

	private int summatoryOneTillValue(int value) {
		int result = 0;
		for (int i = 1; i <= value; i++) {
			result += i;
		}
		return result;
	}

	public double getWeigthShareOverShoulders(int rowIndex, int columnIndex) {
		double summatory = 0;
		for (int i = 0; i <= columnIndex; i++) {
			summatory += MathUtils.binomialCoefficientDouble(rowIndex + 2, i)
					* (1 + columnIndex - i);
		}
		double weightShare = 1 + 2 * columnIndex
				- (summatory / MathUtils.pow(2, rowIndex));
		return weightShare;
	}

}
