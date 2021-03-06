/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.opensource.org/licenses/cddl1.php
 * or http://www.opensource.org/licenses/cddl1.txt.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.opensource.org/licenses/cddl1.php.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 */
package org.dummycreator;

import java.util.Random;

import de.svenjacobs.loremipsum.LoremIpsum;

/**
 * Utility class that contains various methods to generate dummy data for primitive types.
 * 
 * @author Alexander Muthmann <amuthmann@dev-eth0.de> (original author)
 * @author Benny Bottema <b.bottema@projectnibble.org> (further developed project)
 */
public class RandomCreator {

	// we're using an instance here, so we can mock this instance in our junit tests
	private static RandomCreator instance = new RandomCreator();

	private final Random r = new Random();
	private final LoremIpsum loremIpsum = new LoremIpsum();

	public String getRandomString() {
		String gibberish = "";
		for (int i = 0; i <= r.nextInt(3); i++) {
			gibberish += (gibberish.length() != 0 ? " " : "") + loremIpsum.getWords(1, r.nextInt(50));
		}
		return gibberish.replaceAll("\\s", "_").replaceAll("[\\.\\,]", "");
	}

	public boolean getRandomBoolean() {
		return r.nextBoolean();
	}

	public int getRandomInt() {
		return r.nextInt();
	}

	public char getRandomChar() {
		return (char) r.nextInt();
	}

	public byte getRandomByte() {
		return (byte) r.nextInt();
	}

	public long getRandomLong() {
		return r.nextLong();
	}

	/**
	 * @return A float value between 00.00 and 100.00
	 */
	public float getRandomFloat() {
		return r.nextInt(100) + (float) Math.round(r.nextFloat() * 100) / 100;
	}
	
	public float getRandomFloat(float max) {
		return r.nextFloat() * max;
	}
	
	public float getRandomFloat(float min, float max) {
		return getRandomFloat(max - min) + min; 
	}

	/**
	 * @return A double value between 00.00 and 100.00
	 */
	public double getRandomDouble() {
		return r.nextInt(100) + (double) Math.round(r.nextDouble() * 100) / 100;
	}

	public short getRandomShort() {
		return (short) r.nextInt();
	}

	public int getRandomInt(final int max) {
		return r.nextInt(max);
	}

	public static void setInstance(final RandomCreator instance) {
		RandomCreator.instance = instance;
	}

	public static RandomCreator getInstance() {
		return instance;
	}

	public int getRandomInt(final int min, final int max) {
		return getRandomInt(max - min) + min;
	}

	public long getRandomLong(long min, long max) {
		int diff = (int) (max - min);
		long diffL = max - min;
		
		if (diff > 0 && diff < Integer.MAX_VALUE) {
			return r.nextInt(diff) + min;
		} else {
			long l = r.nextLong();
			if (l >= min && l < max) {
				return l;
			} else if (diffL > Integer.MAX_VALUE) {
				return min + r.nextInt(Integer.MAX_VALUE);
			}
			return min;
		}
		
	}

	public long getRandomLong(long max) {
		if (max > Integer.MAX_VALUE) {
			long l = r.nextLong();
			if (l > max) {
				return (long) r.nextInt(Integer.MAX_VALUE);
			}
			
			return l;
		} else {
			return (long) r.nextInt((int) max);
		}
	}

	public byte getRandomByte(byte min, byte max) {
		int diff = max - min;
		return (byte) (r.nextInt(diff) + min);
	}

	public byte getRandomByte(byte max) {
		return (byte) r.nextInt((int) max);
	}

	public short getRandomShort(short min, short max) {
		return (short) (getRandomShort((short) (max - min)) + min);
	}

	public short getRandomShort(short max) {
		return (short) r.nextInt((int) max);
	}

	public double getRandomDouble(double min, double max) {
		return getRandomDouble(max -min) + min;
	}

	public double getRandomDouble(double max) {
		return r.nextDouble() * max;
	}
}