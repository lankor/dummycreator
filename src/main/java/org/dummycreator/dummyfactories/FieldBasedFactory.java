package org.dummycreator.dummyfactories;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codemonkey.javareflection.FieldUtils;
import org.codemonkey.javareflection.FieldUtils.BeanRestriction;
import org.codemonkey.javareflection.FieldUtils.Visibility;
import org.codemonkey.javareflection.FieldWrapper;
import org.dummycreator.ClassBindings;
import org.dummycreator.ClassUsageInfo;
import org.dummycreator.FieldBindings;
import org.dummycreator.ReflectionCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldBasedFactory<T> extends DummyFactory<T> {
	private static final Logger logger = LoggerFactory.getLogger(FieldBasedFactory.class);
	/**
	 * The class to create (and populate).
	 */
	private final Class<T> clazz;
	
	private static final ReflectionCache constructorCache = new ReflectionCache();

	public FieldBasedFactory(final Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public T createDummy(Type[] genericMetaData, Map<String, ClassUsageInfo<?>> knownInstances,
			ClassBindings classBindings, List<Exception> exceptions) {
		logger.info("Default dummy factory.");
		return new ClassBasedFactory<T>(this.clazz).createDummy(classBindings);
	}
	
	/**
	 * 
	 * @param genericMetaData
	 * @param knownInstances
	 * @param classBindings
	 * @param fieldBindings
	 * @param exceptions
	 * @return
	 */
	public T createDummy(Type[] genericMetaData, Map<String, ClassUsageInfo<?>> knownInstances,
			ClassBindings classBindings, FieldBindings fieldBindings, List<Exception> exceptions) {
		return createDummy(classBindings, fieldBindings);
	}

	public T createDummy(ClassBindings classBindings, FieldBindings fieldBindings) {
		T retDummy = null;
		T retField = null;
		
		try {
			
			//If there is a factory for the type of the object, we use it
			final DummyFactory<T> factory = classBindings.find(clazz);
			if (factory != null) {
				retDummy = factory.createDummy(classBindings);
				return retDummy;
			} else {
				retDummy = clazz.newInstance();
				List<Method> setters = discoverSetters(clazz);
				
				for (Method method : setters) {
					String field = getFieldName(method.getName());
					//We are looking for the factory field
					final DummyFactory<T> fieldFactory = fieldBindings.find(field);
					if (fieldFactory != null) {
						retField = fieldFactory.createDummy(classBindings);
					} else {
						Class type = method.getParameterTypes()[0];
						retField = new ClassBasedFactory<T>(type).createDummy(classBindings);
					}
					
					if (retField != null) {
						try {
							method.invoke(retDummy, retField);
						} catch (IllegalAccessException ex) {
							throw new IllegalArgumentException(String.format("Could not instantiate object for type [%s], is it abstract and missing a binding?", clazz));
						} catch (InvocationTargetException ex) {
							throw new IllegalArgumentException(String.format("Could not instantiate object for type [%s], is it abstract and missing a binding?", clazz));
						} catch (IllegalArgumentException ex) {
							throw new IllegalArgumentException(String.format("The type [%s] for field [%s] is wrong.", retField.getClass().getName(), field));
						}
					}
				}
			}
		} catch (InstantiationException ex) {
			throw new IllegalArgumentException(String.format("Could not instantiate object for type [%s]", clazz), ex);
		} catch (IllegalAccessException ex) {
			throw new IllegalArgumentException(String.format("Could not instantiate object for type [%s]", clazz), ex);
		}
		
		return retDummy;
	}
	
	/**
	 * 
	 * @param name Setter method name
	 * @return The field name for the setter method
	 */
	private String getFieldName(String name) {
		return StringUtils.uncapitalize(name.replaceFirst("set", ""));
	}

	/**
	 * @param clazz The subject from which to extract setter methods.
	 * @return A list of all bean setter methods.
	 * @see FieldUtils#collectFields(Class, Class, EnumSet, EnumSet)
	 */
	private List<Method> discoverSetters(final Class<?> clazz) {
		List<Method> setters = constructorCache.getMethodCache(clazz);
		if (setters == null) {
			setters = new ArrayList<Method>();
			final Map<Class<?>, List<FieldWrapper>> fields = FieldUtils.collectFields(clazz, Object.class, EnumSet.allOf(Visibility.class), EnumSet.of(BeanRestriction.YES_SETTER));
			for (final List<FieldWrapper> fieldWrappers : fields.values()) {
				for (final FieldWrapper fieldWrapper : fieldWrappers) {
					setters.add(fieldWrapper.getSetter());
				}
			}

			constructorCache.add(clazz, setters.toArray(new Method[] {}));
		}
		return setters;
	}

}
