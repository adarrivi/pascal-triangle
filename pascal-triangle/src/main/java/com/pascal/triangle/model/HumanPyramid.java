package com.pascal.triangle.model;

public class HumanPyramid {

	private static final int EDGE_INDEX = 0;
	private PascalTriangle pascalTriangle = PascalTriangle.getInstance();
	private int humanWeight;
	private int level;
	private double aggregatedWeightBefore;
	private int humansSharingWeight;
	private int numberOfShares;

	public HumanPyramid(int humanWeight) {
		this.humanWeight = humanWeight;
	}

	public double getHumanEdgeWeight(int level) {
		this.level = level;
		calculateAggregatedWeightBefore();
		calculateHumansSharingWeightSameLevel();
		calculateShareOfTheTotalWeightForHuman(EDGE_INDEX);
		return getSharedWeight();
	}

	private void calculateAggregatedWeightBefore() {
		int numberOfHumansOver = pascalTriangle
				.getAggregatedNumberOfItemsUntilRow(level);
		aggregatedWeightBefore = numberOfHumansOver * humanWeight;
	}

	private void calculateHumansSharingWeightSameLevel() {
		humansSharingWeight = pascalTriangle.getElementSumByRow(level);
	}

	private void calculateShareOfTheTotalWeightForHuman(int humandIndex) {
		numberOfShares = pascalTriangle.getElementByRowAndIndex(level,
				humandIndex);
	}

	private double getSharedWeight() {
		double shareWeight = aggregatedWeightBefore / humansSharingWeight;
		return shareWeight * numberOfShares;
	}
}
