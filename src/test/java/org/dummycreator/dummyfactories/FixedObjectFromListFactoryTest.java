package org.dummycreator.dummyfactories;

import java.util.ArrayList;
import java.util.List;

import org.dummycreator.dummyfactories.FixedObjectFromListFactory;
import org.dummycreator.helperutils.TestPojo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FixedObjectFromListFactoryTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(FixedObjectFromListFactoryTest.class);
	
	private List<TestPojo> list;
	
	@Before
	public void init() {
		list = new ArrayList<TestPojo>();
		int index = 1;
		for (int i = 0; i < 10; i++) {
			list.add(new TestPojo(index++));
		}
	}
	
	@Test
	public void intanceTest() {
		FixedObjectFromListFactory<TestPojo> factory = new FixedObjectFromListFactory<TestPojo>(list);
		
		TestPojo dummy = factory.createDummy();
		LOGGER.info("Instance: {}", dummy);
		Assert.assertNotNull(dummy);
		Assert.assertNotNull(dummy.getId());
	}
	
	@Test
	public void intanceTestA() {
		FixedObjectFromListFactory<TestPojo> factory = new FixedObjectFromListFactory<TestPojo>(list);
		
		for (int i = 0; i < 1000; i++) {
			TestPojo dummy = factory.createDummy();
			LOGGER.info("Instance: {}", dummy);
			Assert.assertNotNull(dummy);
			Assert.assertNotNull(dummy.getId());
		}
	}

}
