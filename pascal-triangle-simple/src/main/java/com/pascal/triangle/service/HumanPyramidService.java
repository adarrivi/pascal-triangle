package com.pascal.triangle.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Service that resolves the human pyramid problem. To improve readability and
 * keep it not very complex, the index validation, as well as the problem
 * parameters, have been moved to HumanPyramidParameterWrapper
 * 
 * @author adarrivi
 * 
 */
@Service
public class HumanPyramidService {

	private static final int EDGE_HUMAN_INDEX = 0;
	private static final int DEFAULT_WEIGHT = 50;

	@Cacheable(value = "getHumanWeightOverShoulders")
	public String getHumanWeightOverShoulders(int levelIndex,
			Integer optionalHumanIndex) {
		double weight = getWeigthOverShoulders(levelIndex,
				getValidHumanIndex(optionalHumanIndex));
		return Double.toString(weight);
	}

	private int getValidHumanIndex(Integer optionalHumanIndex) {
		if (optionalHumanIndex == null) {
			return EDGE_HUMAN_INDEX;
		}
		return optionalHumanIndex.intValue();
	}

	private double getWeigthOverShoulders(int expectedRowIndex,
			int expectedColumnIndex) {
		HumanPyramidParameterWrapper parameters = new HumanPyramidParameterWrapper(
				expectedRowIndex, expectedColumnIndex);

		for (parameters.currenRowIndex = 0; parameters.currenRowIndex <= expectedRowIndex; parameters.currenRowIndex++) {
			moveToNextRow(parameters);
			processRowUntilExpectedValueFound(parameters);
		}

		return getExpectectedValue(parameters);
	}

	private void processRowUntilExpectedValueFound(
			HumanPyramidParameterWrapper parameters) {
		for (parameters.currentColumnIndex = 0; parameters.currentColumnIndex < parameters.maxColumnIndex; parameters.currentColumnIndex++) {
			addHalfWeightForCurrentRowColumnToLeftInNextRow(parameters);
			if (isExpectedValueFound(parameters)) {
				return;
			}
			addHalfWeightForCurrentRowColumnToRightInNextRow(parameters);
		}
	}

	private void moveToNextRow(HumanPyramidParameterWrapper parameters) {
		copyContent(parameters.nextPyramidRow, parameters.currentPyramidRow);
		parameters.maxColumnIndex++;
		initializeArrayToZero(parameters.nextPyramidRow);
	}

	private void copyContent(double[] source, double[] destination) {
		for (int i = 0; i < destination.length; i++) {
			destination[i] = source[i];
		}
	}

	private void initializeArrayToZero(double[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
	}

	private void addHalfWeightForCurrentRowColumnToLeftInNextRow(
			HumanPyramidParameterWrapper parameters) {
		double halfWeightToBeSupported = getWeightToBeSupportedForCurrentRowColumn(parameters) / 2;
		double weightAlreadyBeingSupported = getWeightSupportedByLeftInNextRow(parameters);
		parameters.nextPyramidRow[parameters.currentColumnIndex] = weightAlreadyBeingSupported
				+ halfWeightToBeSupported;
	}

	private double getWeightToBeSupportedForCurrentRowColumn(
			HumanPyramidParameterWrapper parameters) {
		return parameters.currentPyramidRow[parameters.currentColumnIndex]
				+ DEFAULT_WEIGHT;
	}

	private double getWeightSupportedByLeftInNextRow(
			HumanPyramidParameterWrapper parameters) {
		return parameters.nextPyramidRow[parameters.currentColumnIndex];
	}

	private void addHalfWeightForCurrentRowColumnToRightInNextRow(
			HumanPyramidParameterWrapper parameters) {
		double halfWeightToBeSupported = getWeightToBeSupportedForCurrentRowColumn(parameters) / 2;
		double weightAlreadyBeingSupported = getWeightSupportedByRightInNextRow(parameters);
		parameters.nextPyramidRow[parameters.currentColumnIndex + 1] = weightAlreadyBeingSupported
				+ halfWeightToBeSupported;
	}

	private double getWeightSupportedByRightInNextRow(
			HumanPyramidParameterWrapper parameters) {
		return parameters.nextPyramidRow[parameters.currentColumnIndex + 1];
	}

	private boolean isExpectedValueFound(HumanPyramidParameterWrapper parameters) {
		return parameters.expectedRowIndex == parameters.currenRowIndex
				&& parameters.expectedColumnIndex == parameters.currentColumnIndex;
	}

	private double getExpectectedValue(HumanPyramidParameterWrapper parameters) {
		return parameters.nextPyramidRow[parameters.currentColumnIndex];
	}

}
