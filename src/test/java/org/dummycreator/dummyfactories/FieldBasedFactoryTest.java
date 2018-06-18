package org.dummycreator.dummyfactories;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.dummycreator.ClassBindings;
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
	public void testFieldDummyFactoryNullBindings() {
		FieldBindings bindings = null;

		DummyCreator creator = new DummyCreator(bindings);
		BigDecimalClass dummy = creator.createByFields(BigDecimalClass.class);
	}

	@Test
	public void testFieldDummyFactoryFull() {
		FieldBasedFactory<BigDecimalClass> bigDecimalClassFactory = new FieldBasedFactory<BigDecimalClass>(BigDecimalClass.class);
        ClassBindings bindigs = ClassBindings.defaultBindings();
        bindigs.add(BigDecimal.class, new BigDecimalFactory(0d, 10d));

		bigDecimalClassFactory.createDummy(null,null, bindigs, null);
	}

    @Test
    public void testFieldDummyFactoryFullField() {
        FieldBasedFactory<BigDecimalClass> bigDecimalClassFactory = new FieldBasedFactory<BigDecimalClass>(BigDecimalClass.class);
        ClassBindings bindigs = ClassBindings.defaultBindings();
        bindigs.add(BigDecimal.class, new BigDecimalFactory(0d, 10d));

        FieldBindings fieldBindings = new FieldBindings();
        fieldBindings.add("bigDecimalWithMax", new BigDecimalFactory(0d, 10d));

        bigDecimalClassFactory.createDummy(null,null, bindigs, fieldBindings, null);
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
