package com.pascal.triangle.model.pyramid.impl;

import org.apache.commons.math.util.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pascal.triangle.model.pyramid.WeightPascalTriangleCalculator;

/**
 * Weight pascal triangle approach using a formula. This solution is limited to
 * just a few lines (<30) because of its factorial operations. The formula and
 * the explanation can be found at:
 * 
 * @see <a
 *      href="http://math.stackexchange.com/questions/486807/how-much-weight-is-on-each-person-in-a-human-pyramid">how
 *      much weight is on each person</a>
 * 
 */
@Component("formulaWeightPascalTriangleCalculator")
class FormulaWeightPascalTriangleCalculator implements
		WeightPascalTriangleCalculator {

	@Autowired
	private PascalTriangleParameterVerifier parameterVerifier;

	FormulaWeightPascalTriangleCalculator() {
		// To limit scope
	}

	@Override
	public double getWeigthShareOverShoulders(int rowIndex, int columnIndex,
			int humanWeight) {
		parameterVerifier.assertValidRowAndIndex(rowIndex, columnIndex);
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
