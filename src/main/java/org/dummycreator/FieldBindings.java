package org.dummycreator;

import java.util.HashMap;

import org.dummycreator.dummyfactories.DummyFactory;

public class FieldBindings {
	private final HashMap<String, DummyFactory<?>> bindings = new HashMap<String, DummyFactory<?>>();
	
	public FieldBindings() {
		
	}
	
	public <T> void add(final String field, final DummyFactory<? extends T> factory) {
//		try {
//			if (factory.isValidForType(clazz)) {
				bindings.put(field, factory);
//			} else {
//				// factory didn't throw an exception, so we'll do it ourself
//				throw new IllegalArgumentException();
//			}
//		} catch (final IllegalArgumentException e) {
//			// note: exception is also thrown by DummyFactory.isValidForType
//			throw new IllegalArgumentException(String.format("dummy factory [%s] is not valid for class type [%s]", factory, clazz), e);
//		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> DummyFactory<T> find(final String field) {
		return (DummyFactory<T>) bindings.get(field);
	}
}
