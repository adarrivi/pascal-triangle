package com.pascal.triangle.service;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HumanPyramidServiceTest {

	private static final Logger LOG = LoggerFactory
			.getLogger(HumanPyramidServiceTest.class);
	private HumanPyramidService victim = new HumanPyramidService();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private int rowIndex;
	private int columnIndex;
	private String sharedWeight;

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
		double memoryUsage = getCurrentMemoryUsageKB();
		long nanoTime = System.nanoTime();
		sharedWeight = victim
				.getHumanWeightOverShoulders(rowIndex, columnIndex);
		nanoTime = System.nanoTime() - nanoTime;
		memoryUsage = getCurrentMemoryUsageKB() - memoryUsage;
		LOG.debug("Used Memory: {} KB, time taken {}ms ", memoryUsage,
				TimeUnit.NANOSECONDS.toMillis(nanoTime));
	}

	private double getCurrentMemoryUsageKB() {
		Runtime runtime = Runtime.getRuntime();
		return (runtime.totalMemory() - runtime.freeMemory()) / 1024;
	}

	private void givenRowIndexAndColumnIndex(int givenRowIndex,
			int givenColumnIndex) {
		rowIndex = givenRowIndex;
		columnIndex = givenColumnIndex;
	}

	private void thenSharedWeightShouldBe(double expectedSharedWeight) {
		Assert.assertEquals(expectedSharedWeight,
				Double.parseDouble(sharedWeight), 0);
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

	@Test
	public void getWeigthShareOverShoulders_Row20000Index100_Returns10050() {
		givenRowIndexAndColumnIndex(20000, 100);
		whenGetWeigthShareOverShoulders();
		thenSharedWeightShouldBe(10050.0);
	}

	@Ignore("This test can take up to 7 seconds to execute")
	@Test
	public void getWeigthShareOverShoulders_Row50000Index100_Returns10050() {
		givenRowIndexAndColumnIndex(50000, 100);
		whenGetWeigthShareOverShoulders();
		thenSharedWeightShouldBe(10050.0);
	}

	@Ignore("This test can take up to 1.30 mins to execute")
	@Test
	public void getWeigthShareOverShoulders_Row200000Index100_Returns10050() {
		givenRowIndexAndColumnIndex(200000, 100);
		whenGetWeigthShareOverShoulders();
		thenSharedWeightShouldBe(10050.0);
	}

}
