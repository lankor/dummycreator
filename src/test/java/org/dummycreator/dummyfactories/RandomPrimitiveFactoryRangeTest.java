package org.dummycreator.dummyfactories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for {@link FixedInstanceFactory}
 */
public class RandomPrimitiveFactoryRangeTest {
	@Test
	public void testIntegerMaxValue() {
		int max = 10;
		RandomPrimitiveFactory<Integer> factory = new RandomPrimitiveFactory<Integer>(int.class, max);
		
		Integer i = factory.createDummy(null);
		assertNotNull(i);
		assertTrue(i < max);
	}
	
	@Test
	public void testIntegerRangeValue() {
		int min = 10;
		int max = 100;
		RandomPrimitiveFactory<Integer> factory = new RandomPrimitiveFactory<Integer>(int.class, min, max);
		
		Integer i = factory.createDummy(null);
		assertNotNull(i);
		assertTrue(i < max);
		assertTrue(i > min);
	}
	
	@Test
	public void testLongMaxValue() {
		long max = 10;
		RandomPrimitiveFactory<Long> factory = new RandomPrimitiveFactory<Long>(long.class, max);
		
		Long i = factory.createDummy(null);
		assertNotNull(i);
		assertTrue(i < max);
	}
	
	@Test
	public void testLongRangeValue() {
		long min = 10;
		long max = 100;
		RandomPrimitiveFactory<Long> factory = new RandomPrimitiveFactory<Long>(long.class, min, max);
		
		Long i = factory.createDummy(null);
		assertNotNull(i);
		assertTrue(i < max);
		assertTrue(i > min);
	}
	
	@Test
	public void testLongRangeValueMax() {
		long min = 1000;
		long max = Long.MAX_VALUE;
		RandomPrimitiveFactory<Long> factory = new RandomPrimitiveFactory<Long>(long.class, min, max);
		
		for (int i = 0; i < 10000000; i++) {
			Long dummy = factory.createDummy(null);
			assertNotNull(dummy);
			assertTrue(dummy < max);
			assertTrue(dummy >= min);
		}
	}
	
	
	
	
	
	@Test
	public void testFloatMaxValue() {
		float max = 10;
		RandomPrimitiveFactory<Float> factory = new RandomPrimitiveFactory<Float>(float.class, max);
		
		Float i = factory.createDummy(null);
		assertNotNull(i);
		assertTrue(i < max);
	}
	
	@Test
	public void testFloatRangeValue() {
		float min = 10;
		float max = 100;
		RandomPrimitiveFactory<Float> factory = new RandomPrimitiveFactory<Float>(float.class, min, max);
		
		Float i = factory.createDummy(null);
		assertNotNull(i);
		assertTrue(i < max);
		assertTrue(i > min);
	}
	
	@Test
	public void testFloatRangeValueMax() {
		float min = 1000;
		float max = Float.MAX_VALUE;
		RandomPrimitiveFactory<Float> factory = new RandomPrimitiveFactory<Float>(float.class, min, max);
		
		for (int i = 0; i < 10000000; i++) {
			Float dummy = factory.createDummy(null);
			assertNotNull(dummy);
			assertTrue(dummy < max);
			assertTrue(dummy >= min);
		}
	}
	
	
	
	
	@Test
	public void testByteMaxValue() {
		byte max = 10;
		RandomPrimitiveFactory<Byte> factory = new RandomPrimitiveFactory<Byte>(byte.class, max);
		
		Byte i = factory.createDummy(null);
		assertNotNull(i);
		assertTrue(i < max);
	}
	
	@Test
	public void testByteRangeValue() {
		byte min = 10;
		byte max = Byte.MAX_VALUE;
		RandomPrimitiveFactory<Byte> factory = new RandomPrimitiveFactory<Byte>(byte.class, min, max);
		
		Byte i = factory.createDummy(null);
		assertNotNull(i);
		assertTrue(i < max);
		assertTrue(i >= min);
	}
	
	@Test
	public void testByteRangeValueMax() {
		byte min = 10;
		byte max = 100;
		RandomPrimitiveFactory<Byte> factory = new RandomPrimitiveFactory<Byte>(byte.class, min, max);
		
		for (int i = 0; i < 10000000; i++) {
			Byte dummy = factory.createDummy(null);
			assertNotNull(dummy);
			assertTrue(dummy < max);
			assertTrue(dummy >= min);
		}
	}
	

	
	
	
	@Test
	public void testShortMaxValue() {
		short max = 10;
		RandomPrimitiveFactory<Short> factory = new RandomPrimitiveFactory<Short>(short.class, max);
		
		Short i = factory.createDummy(null);
		assertNotNull(i);
		assertTrue(i < max);
	}
	
	@Test
	public void testShortRangeValue() {
		short min = 10;
		short max = Short.MAX_VALUE;
		RandomPrimitiveFactory<Short> factory = new RandomPrimitiveFactory<Short>(short.class, min, max);
		
		Short i = factory.createDummy(null);
		assertNotNull(i);
		assertTrue(i < max);
		assertTrue(i >= min);
	}
	
	@Test
	public void testShortRangeValueMax() {
		short min = 10;
		short max = 100;
		RandomPrimitiveFactory<Short> factory = new RandomPrimitiveFactory<Short>(short.class, min, max);
		
		for (int i = 0; i < 100000; i++) {
			Short dummy = factory.createDummy(null);
			assertNotNull(dummy);
			assertTrue(dummy < max);
			assertTrue(dummy >= min);
		}
	}
	


	
	
	
	@Test
	public void testDoubleMaxValue() {
		double max = 10;
		RandomPrimitiveFactory<Double> factory = new RandomPrimitiveFactory<Double>(double.class, max);
		
		Double i = factory.createDummy(null);
		assertNotNull(i);
		assertTrue(i < max);
	}
	
	@Test
	public void testDoubleRangeValue() {
		double min = 10;
		double max = Double.MAX_VALUE;
		RandomPrimitiveFactory<Double> factory = new RandomPrimitiveFactory<Double>(double.class, min, max);
		
		Double i = factory.createDummy(null);
		assertNotNull(i);
		assertTrue(i < max);
		assertTrue(i >= min);
	}
	
	@Test
	public void testDoubleRangeValueMax() {
		double min = 10;
		double max = 100;
		RandomPrimitiveFactory<Double> factory = new RandomPrimitiveFactory<Double>(double.class, min, max);
		
		for (int i = 0; i < 10000000; i++) {
			Double dummy = factory.createDummy(null);
			assertNotNull(dummy);
			assertTrue(dummy < max);
			assertTrue(dummy >= min);
		}
	}
}