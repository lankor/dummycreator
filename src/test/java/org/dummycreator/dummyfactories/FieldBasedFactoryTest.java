package org.dummycreator.dummyfactories;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.dummycreator.DummyCreator;
import org.dummycreator.FieldBindings;
import org.dummycreator.helperutils.BigDecimalClass;
import org.junit.Assert;
import org.junit.Test;

public class FieldBasedFactoryTest {
	
	@Test
	public void testFieldDummyFactory() {
		FieldBindings bindings = new FieldBindings();
		bindings.add("bigDecimalWithMax", new BigDecimalFactory(0d, 10d));
		bindings.add("bigDecimalWithRounding", new BigDecimalFactory(4, BigDecimal.ROUND_HALF_UP));
		bindings.add("bigDecimalWithRoundingContext", new BigDecimalFactory(2, RoundingMode.HALF_UP));
		
		DummyCreator creator = new DummyCreator(bindings);
		BigDecimalClass dummy = creator.createByFields(BigDecimalClass.class);
		Assert.assertNotNull(dummy);
		Assert.assertNotNull(dummy.getBigDecimalWithMax());
		Assert.assertNotNull(dummy.getBigDecimalWithRounding());
		Assert.assertNotNull(dummy.getBigDecimalWithRoundingContext());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFieldDummyFactoryWrongType() {
		FieldBindings bindings = new FieldBindings();
		bindings.add("bigDecimalWithMax", new BigDecimalFactory(0d, 10d));
		bindings.add("bigDecimalWithRounding", new BigDecimalFactory(4, BigDecimal.ROUND_HALF_UP));
		bindings.add("bigDecimalWithRoundingContext", new ClassBasedFactory<String>(String.class));
		
		DummyCreator creator = new DummyCreator(bindings);
		BigDecimalClass dummy = creator.createByFields(BigDecimalClass.class);
		Assert.assertNotNull(dummy);
		Assert.assertNotNull(dummy.getBigDecimalWithMax());
		Assert.assertNotNull(dummy.getBigDecimalWithRounding());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFieldDummyFactoryNullFactory() {
		FieldBindings bindings = new FieldBindings();
		bindings.add("bigDecimalWithMax", null);
	}
}
