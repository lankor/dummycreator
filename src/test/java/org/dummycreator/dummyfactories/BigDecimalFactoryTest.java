package org.dummycreator.dummyfactories;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.dummycreator.ClassBindings;
import org.dummycreator.dummyfactories.BigDecimalFactory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BigDecimalFactoryTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(BigDecimalFactoryTest.class);
	
	@Test
	public void create() {
		BigDecimalFactory factory = new BigDecimalFactory();
		
		BigDecimal dummy = factory.createDummy(ClassBindings.defaultBindings());
		Assert.assertNotNull(dummy);
		LOGGER.info("Dummy: {}", dummy);
	}
	
	@Test
	public void createWithoutBindings() {
		BigDecimalFactory factory = new BigDecimalFactory();
		
		BigDecimal dummy = factory.createDummy();
		Assert.assertNotNull(dummy);
		LOGGER.info("Dummy: {}", dummy);
	}
	
	@Test
	public void createMathContext() {
		int presicion = 2;
		MathContext mc = new MathContext(presicion, RoundingMode.HALF_UP);
		BigDecimalFactory factory = new BigDecimalFactory(mc);
		
		BigDecimal dummy = factory.createDummy(ClassBindings.defaultBindings());
		Assert.assertNotNull(dummy);
		
		LOGGER.info("Dummy MathContext: {}", dummy);
	}
	
	@Test
	public void createScale() {
		int presicion = 2;
		BigDecimalFactory factory = new BigDecimalFactory(presicion, BigDecimal.ROUND_HALF_UP);
		
		BigDecimal dummy = factory.createDummy(ClassBindings.defaultBindings());
		Assert.assertNotNull(dummy);
		
		LOGGER.info("Dummy Scale: {}", dummy);
	}
	
	@Test
	public void createScale2() {
		int presicion = 2;
		BigDecimalFactory factory = new BigDecimalFactory(presicion, RoundingMode.HALF_UP);
		
		BigDecimal dummy = factory.createDummy(ClassBindings.defaultBindings());
		Assert.assertNotNull(dummy);
		
		LOGGER.info("Dummy Rounding: {}", dummy);
	}
	
	@Test
	public void createFrom1To2() {
		double min = 1d;
		double max = 2d;
		BigDecimalFactory factory = new BigDecimalFactory(min, max);
		
		for (int i = 0; i < 1000; i++) {
			BigDecimal dummy = factory.createDummy(ClassBindings.defaultBindings());
			Assert.assertNotNull(dummy);
			Assert.assertTrue(dummy.doubleValue() >= min);
			Assert.assertTrue(dummy.doubleValue() <= max);
			
			LOGGER.info("Dummy Rounding: {}", dummy);
		}
	}
	
	@Test
	public void createFrom1To1dot5() {
		double min = 1d;
		double max = 1.5d;
		BigDecimalFactory factory = new BigDecimalFactory(min, max);
		
		for (int i = 0; i < 1000; i++) {
			BigDecimal dummy = factory.createDummy(ClassBindings.defaultBindings());
			Assert.assertNotNull(dummy);
			LOGGER.info("Dummy Rounding: {}", dummy);
			Assert.assertTrue(dummy.doubleValue() >= min);
			Assert.assertTrue(dummy.doubleValue() <= max);
			
		}
	}
	
	@Test
	public void createFrom100To10000() {
		double min = 100d;
		double max = 10000d;
		BigDecimalFactory factory = new BigDecimalFactory(min, max);
		
		for (int i = 0; i < 1000; i++) {
			BigDecimal dummy = factory.createDummy(ClassBindings.defaultBindings());
			Assert.assertNotNull(dummy);
			Assert.assertTrue(dummy.doubleValue() >= min);
			Assert.assertTrue(dummy.doubleValue() <= max);
			
			LOGGER.info("Dummy Rounding: {}", dummy);
		}
	}
	
	@Test
	public void createFrom1To2Scale() {
		double min = 1d;
		double max = 2d;
		BigDecimalFactory factory = new BigDecimalFactory(min, max, 3, BigDecimal.ROUND_HALF_UP);
		
		for (int i = 0; i < 1000; i++) {
			BigDecimal dummy = factory.createDummy(ClassBindings.defaultBindings());
			Assert.assertNotNull(dummy);
			Assert.assertTrue(dummy.doubleValue() >= min);
			Assert.assertTrue(dummy.doubleValue() <= max);
			
			LOGGER.info("Dummy Rounding: {}", dummy);
		}
	}
	
	@Test
	public void createFrom1To1dot5MathContext() {
		double min = 1d;
		double max = 1.5d;
		int presicion = 2;
		MathContext mc = new MathContext(presicion, RoundingMode.HALF_UP);
		BigDecimalFactory factory = new BigDecimalFactory(min, max, mc);
		
		for (int i = 0; i < 1000; i++) {
			BigDecimal dummy = factory.createDummy(ClassBindings.defaultBindings());
			Assert.assertNotNull(dummy);
			LOGGER.info("Dummy Rounding: {}", dummy);
			Assert.assertTrue(dummy.doubleValue() >= min);
			Assert.assertTrue(dummy.doubleValue() <= max);
			
		}
	}
	
	@Test
	public void createFrom100To10000Rounding() {
		double min = 100d;
		double max = 10000d;
		BigDecimalFactory factory = new BigDecimalFactory(min, max, 3, RoundingMode.HALF_UP);
		
		for (int i = 0; i < 1000; i++) {
			BigDecimal dummy = factory.createDummy(ClassBindings.defaultBindings());
			Assert.assertNotNull(dummy);
			Assert.assertTrue(dummy.doubleValue() >= min);
			Assert.assertTrue(dummy.doubleValue() <= max);
			
			LOGGER.info("Dummy Rounding: {}", dummy);
		}
	}
}
