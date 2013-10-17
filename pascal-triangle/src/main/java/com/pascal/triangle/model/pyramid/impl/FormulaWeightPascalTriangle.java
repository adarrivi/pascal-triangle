package com.pascal.triangle.model.pyramid.impl;

import org.apache.commons.math.util.MathUtils;
import org.springframework.stereotype.Component;

import com.pascal.triangle.model.pyramid.WeightPascalTriangle;

/**
 * This formula and the explanation can be found at:
 * 
 * @see <a
 *      href="http://math.stackexchange.com/questions/486807/how-much-weight-is-on-each-person-in-a-human-pyramid">how
 *      much weight is on each person</a>
 * 
 */
@Component("formulaWeightPascalTriangle")
class FormulaWeightPascalTriangle implements WeightPascalTriangle {

	FormulaWeightPascalTriangle() {
		// To limit scope
	}

	@Override
	public double getWeigthShareOverShoulders(int rowIndex, int columnIndex,
			int humanWeight) {
		PascalTriangleParameterVerifier.getInstance().assertValidRowAndIndex(
				rowIndex, columnIndex);
		double summatory = 0;
		for (int i = 0; i <= columnIndex; i++) {
			summatory += MathUtils.binomialCoefficientDouble(rowIndex + 2, i)
					* (1 + columnIndex - i);
		}
		double weightShare = 1 + 2 * columnIndex
				- (summatory / MathUtils.pow(2, rowIndex));
		return weightShare * humanWeight;
	}

}
