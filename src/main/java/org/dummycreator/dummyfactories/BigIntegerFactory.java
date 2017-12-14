package org.dummycreator.dummyfactories;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.dummycreator.ClassBindings;
import org.dummycreator.ClassUsageInfo;

public class BigIntegerFactory extends DummyFactory<BigInteger> {
	private static final int NUM_BITS = 20;

	private int numBits;
	private boolean range = false;
	private Random random = new Random();
	
	private long min;
	private long max;
	
	public BigIntegerFactory() {
		this(NUM_BITS);
	}
	
	public BigIntegerFactory(int numBits) {
		this.numBits = numBits;
	}

	public BigIntegerFactory(long min, long max) {
		this.range = true;
		
		this.min = min;
		this.max = max;
	}

	@Override
	public BigInteger createDummy(Type[] genericMetaData, Map<String, ClassUsageInfo<?>> knownInstances,
			ClassBindings classBindings, List<Exception> exceptions) {
		return createDummy();
	}

	public BigInteger createDummy() {
		if (this.range) {
			long number = (long) (this.random.nextDouble() * this.max);
			
			if (number < this.min) {
				number += this.min;
			}
			return new BigInteger(String.valueOf(number));
		} else {
			
			return new BigInteger(this.numBits, this.random);
		}
	}
}
