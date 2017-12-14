package org.dummycreator.dummyfactories;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.dummycreator.DummyCreator;
import org.dummycreator.FieldBindings;
import org.dummycreator.helperutils.BigDecimalClass;
import org.junit.Assert;
import org.junit.Test;

public class FieldDummyFactoryTest {
	
	@Test
	public void testCharset() {
		FieldBindings bindings = new FieldBindings();
		bindings.add("bigDecimalWithMax", new BigDecimalFactory(0d, 10d));
		bindings.add("bigDecimalWithRouning", new BigDecimalFactory(4, BigDecimal.ROUND_HALF_UP));
		bindings.add("bigDecimalWithRouningContext", new BigDecimalFactory(2, RoundingMode.HALF_UP));
		
		DummyCreator creator = new DummyCreator(bindings);
		BigDecimalClass dummy = creator.createFields(BigDecimalClass.class);
		Assert.assertNotNull(dummy);
	}
}
