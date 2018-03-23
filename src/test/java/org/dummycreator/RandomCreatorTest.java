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
}
