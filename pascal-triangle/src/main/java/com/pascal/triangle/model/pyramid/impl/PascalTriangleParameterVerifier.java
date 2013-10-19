package com.pascal.triangle.model.pyramid.impl;

import org.springframework.stereotype.Component;

import com.pascal.triangle.model.exception.InvalidTriangleException;

@Component
class PascalTriangleParameterVerifier {

	PascalTriangleParameterVerifier() {
		// To limit scope
	}

	void assertValidRowAndIndex(int rowIndex, int columnIndex) {
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

}
