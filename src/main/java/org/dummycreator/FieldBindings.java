package org.dummycreator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.dummycreator.dummyfactories.ClassBasedFactory;
import org.dummycreator.dummyfactories.ConstructorBasedFactory;
import org.dummycreator.dummyfactories.DummyFactory;
import org.dummycreator.dummyfactories.MethodBasedFactory;

/**
 * Stores a list of classes / interfaces and their associated deferred types. This list is used to tell {@link DummyCreator} which specific
 * implementation it should use to produce new dummy instances for the field in the class. This is most useful to make sure Dummy Creator can create
 * dummy objects for interface / abstract types it encounters.
 * <p>
 * Deferred types are produced by {@link DummyFactory} implementations. Default factories are in place for strings, primitives, enums. In
 * addition,there are factories that can be configured to invoke a specific {@link Method} ({@link MethodBasedFactory}) or a specific
 * {@link Constructor} ({@link ConstructorBasedFactory}). Finally, there is a factory to return a fixed instance. The
 * {@link ClassBasedFactory} is used automatically internally if no class binding can be found for a particular type.
 * 
 * @author Luis Cardenas <luis.cardeno@gmail.com>
 */
public class FieldBindings {
	private final HashMap<String, DummyFactory<?>> bindings = new HashMap<String, DummyFactory<?>>();
	
	/**
	 * Default constructor
	 */
	public FieldBindings() {}
	
	/**
	 * Binds a {@link DummyFactory} to a specific field in the <code>Class</code>
	 * 
	 * @param field The name of the field to bind the dummy factory to.
	 * @param factory The factory to bind the given class.
	 * 
	 */
	public <T> void add(final String field, final DummyFactory<? extends T> factory) {
		if (factory == null) {
			throw new IllegalArgumentException(String.format("dummy factory for [%s] field can not be null", field));
		}
		bindings.put(field, factory);
	}
	
	/**
	 * This method returns a binding made for the given field name. This binding might be of one of the following type: Constructor
	 * Implementation of a Interface Method Object
	 */
	@SuppressWarnings("unchecked")
	public <T> DummyFactory<T> find(final String field) {
		return (DummyFactory<T>) bindings.get(field);
	}
}
