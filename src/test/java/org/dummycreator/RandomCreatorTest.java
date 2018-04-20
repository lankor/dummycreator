package org.dummycreator;

import org.junit.Assert;
import org.junit.Test;

public class RandomCreatorTest {
    private RandomCreator random = new RandomCreator();

    @Test
    public void getRandomBooleanTest() {
        boolean dummy = random.getRandomBoolean();
        Assert.assertNotNull(dummy);
    }

    @Test
    public void getRandomDoubleTest() {
        Double randomDouble = random.getRandomDouble();
        Assert.assertTrue(randomDouble > 0);
        Assert.assertTrue(randomDouble < 100);
    }

    @Test
    public void getRandomLong() {
        long maxValue = ((long) Integer.MAX_VALUE + 10000);
        Long randomLong = random.getRandomLong(maxValue);
        Assert.assertNotNull(randomLong);
        Assert.assertTrue(randomLong < maxValue);
    }
}
