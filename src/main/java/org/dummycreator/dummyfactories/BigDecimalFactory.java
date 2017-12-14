package org.dummycreator.dummyfactories;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.dummycreator.ClassBindings;
import org.dummycreator.ClassUsageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BigDecimalFactory extends DummyFactory<BigDecimal> {
	private static final Logger LOGGER = LoggerFactory.getLogger(BigDecimalFactory.class);
	
	private MathContext context;
	private double min;
	private double max;
	
	public BigDecimalFactory() {
		this(0d, 10000d);
	}
	
	public BigDecimalFactory(MathContext context) {
		this();
		this.context = context;
	}

	public BigDecimalFactory(int scale, int roundingMode) {
		this(scale, RoundingMode.valueOf(roundingMode));
	}

	public BigDecimalFactory(int scale, RoundingMode roundingMode) {
		this(new MathContext(scale, roundingMode));
	}
	
	public BigDecimalFactory(double min, double max) {
		this.min = min;
		this.max = max;
	}
	
	public BigDecimalFactory(double min, double max, MathContext context) {
		this(min, max);
		this.context = context;
	}

	public BigDecimalFactory(double min, double max, int scale, int roundingMode) {
		this(min, max, scale, RoundingMode.valueOf(roundingMode));
	}

	public BigDecimalFactory(double min, double max, int scale, RoundingMode roundingMode) {
		this(min, max, new MathContext(scale, roundingMode));
	}

	@Override
	public BigDecimal createDummy(Type[] genericMetaData, Map<String, ClassUsageInfo<?>> knownInstances,
			ClassBindings classBindings, List<Exception> exceptions) {
		return createDummy();
	}
	
	public BigDecimal createDummy() {
		Random random = new Random();
		
		double d = random.nextDouble() * this.max;
		
		LOGGER.debug("Initial: {}", d);
		if (d < this.min) {
			d += this.min;
		}
		
		if (d > this.max) {
			d -= this.min / 2;
		}
		
		LOGGER.debug("Final: {}", d);
		if (context != null) {
			return new BigDecimal(d).setScale(context.getPrecision(), context.getRoundingMode());
		} else {
			return new BigDecimal(d);
		}
	}
}
