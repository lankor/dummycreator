package org.dummycreator.dummyfactories;

import java.math.BigInteger;

import org.dummycreator.dummyfactories.BigIntegerFactory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BigIntegerFactoryTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(BigIntegerFactoryTest.class);
	
	@Test
	public void create() {
		BigIntegerFactory factory = new BigIntegerFactory();
		
		BigInteger dummy = factory.createDummy();
		Assert.assertNotNull(dummy);
		LOGGER.info("Dummy: {}", dummy);
	}
	
	@Test
	public void createNumBits() {
		int numBits = 100;
		BigIntegerFactory factory = new BigIntegerFactory(numBits);
		
		BigInteger dummy = factory.createDummy();
		Assert.assertNotNull(dummy);
		LOGGER.info("Dummy: {}", dummy);
	}
	
	@Test
	public void createRange() {
		long min = 100;
		long max = 1000;
		BigIntegerFactory factory = new BigIntegerFactory(min, max);
		
		for (int i = 0; i < 10000; i++) {
			
			BigInteger dummy = factory.createDummy();
			Assert.assertNotNull(dummy);
			Assert.assertTrue(dummy.longValue() < max);
			LOGGER.info("Dummy: {}", dummy);
		}
	}
}
