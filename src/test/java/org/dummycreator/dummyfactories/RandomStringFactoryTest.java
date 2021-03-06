package org.dummycreator.dummyfactories;

import static org.junit.Assert.assertEquals;

import org.dummycreator.RandomCreator;
import org.easymock.EasyMock;
import org.junit.Test;

/**
 * Tests for {@link RandomStringFactory}
 */
public class RandomStringFactoryTest {

	/**
	 * Test for {@link RandomStringFactory#createDummy(java.util.Map, org.dummycreator.ClassBindings, java.util.List)}.
	 */
	@Test
	public void testCreateDummy() {
		final RandomStringFactory factory = new RandomStringFactory();

		final RandomCreator mock = EasyMock.createMock(RandomCreator.class);
		RandomCreator.setInstance(mock);
		EasyMock.expect(mock.getRandomString()).andReturn("1234");
		EasyMock.replay(mock);

		assertEquals("1234", factory.createDummy(null, null, null, null));

		EasyMock.verify(mock);
		RandomCreator.setInstance(new RandomCreator());
	}
}