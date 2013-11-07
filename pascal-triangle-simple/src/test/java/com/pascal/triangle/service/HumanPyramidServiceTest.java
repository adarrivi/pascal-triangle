package com.pascal.triangle.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.MockitoAnnotations;

import com.pascal.triangle.service.HumanPyramidService;

public class HumanPyramidServiceTest {

	private HumanPyramidService victim = new HumanPyramidService();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private int rowIndex;
	private int columnIndex;
	private double sharedWeight;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getWeigthShareOverShoulders_Row0Index0_Returns0() {
		givenRowIndexAndColumnIndex(0, 0);
		whenGetWeigthShareOverShoulders();
		thenSharedWeightShouldBe(0);
	}

	private void whenGetWeigthShareOverShoulders() {
		sharedWeight = victim
				.getWeigthShareOverShoulders(rowIndex, columnIndex);
	}

	private void givenRowIndexAndColumnIndex(int givenRowIndex,
			int givenColumnIndex) {
		rowIndex = givenRowIndex;
		columnIndex = givenColumnIndex;
	}

	private void thenSharedWeightShouldBe(double expectedSharedWeight) {
		Assert.assertEquals(expectedSharedWeight, sharedWeight, 0);
	}

	@Test
	public void getWeigthShareOverShoulders_Row1Index1_Returns0() {
		givenRowIndexAndColumnIndex(1, 1);
		whenGetWeigthShareOverShoulders();
		thenSharedWeightShouldBe(25);
	}

	@Test
	public void getWeigthShareOverShoulders_Row4Index3_Returns4() {
		givenRowIndexAndColumnIndex(4, 3);
		whenGetWeigthShareOverShoulders();
		thenSharedWeightShouldBe(125);
	}

	@Test
	public void getWeigthShareOverShoulders_Row30Index15_Returns1326() {
		givenRowIndexAndColumnIndex(30, 15);
		whenGetWeigthShareOverShoulders();
		thenSharedWeightShouldBe(1326.0801054537296);
	}

	@Test
	public void getWeigthShareOverShoulders_Row40Index20_Returns1792() {
		givenRowIndexAndColumnIndex(40, 20);
		whenGetWeigthShareOverShoulders();
		thenSharedWeightShouldBe(1792.9900903798625);
	}

	@Test
	public void getWeigthShareOverShoulders_Row1000Index500_Returns48787() {
		givenRowIndexAndColumnIndex(1000, 500);
		whenGetWeigthShareOverShoulders();
		thenSharedWeightShouldBe(48787.48784017304);
	}

}
