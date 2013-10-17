package com.pascal.triangle.service;

public interface HumanPyramidService {

	double getEdgeHumanWeightOverShoulders(int levelIndex);

	double getHumanWeightOverShoulders(int levelIndex, int humanIndex);
}
