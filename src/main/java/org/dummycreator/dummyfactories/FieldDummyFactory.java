package org.dummycreator.dummyfactories;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codemonkey.javareflection.FieldUtils;
import org.codemonkey.javareflection.FieldWrapper;
import org.codemonkey.javareflection.FieldUtils.BeanRestriction;
import org.codemonkey.javareflection.FieldUtils.Visibility;
import org.dummycreator.ClassBindings;
import org.dummycreator.ClassUsageInfo;
import org.dummycreator.FieldBindings;
import org.dummycreator.ReflectionCache;

public class FieldDummyFactory<T> extends DummyFactory<T> {
	private static final Logger logger = Logger.getLogger(FieldDummyFactory.class);
	/**
	 * The class to create (and populate).
	 */
	private final Class<T> clazz;
	
	private static final ReflectionCache constructorCache = new ReflectionCache();

	public FieldDummyFactory(final Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public T createDummy(Type[] genericMetaData, Map<String, ClassUsageInfo<?>> knownInstances,
			ClassBindings classBindings, List<Exception> exceptions) {
		logger.info("Default dummy factory.");
		return new ClassBasedFactory<T>(this.clazz).createDummy(classBindings);
	}
	
	public T createDummy(Type[] genericMetaData, Map<String, ClassUsageInfo<?>> knownInstances,
			ClassBindings classBindings, FieldBindings fieldBindings, List<Exception> exceptions) {
		return null;
	}

	public T createDummy(ClassBindings classBindings, FieldBindings fieldBindings) {
		T retDummy = null;
		T retField = null;
		
		try {
			
			//Si existe un factory para el tipo dummy, la usamos
			final DummyFactory<T> factory = classBindings.find(clazz);
			if (factory != null) {
				retDummy = factory.createDummy(classBindings);
				return retDummy;
			} else {
				retDummy = clazz.newInstance();
				List<Method> setters = discoverSetters(clazz);
				
				for (Method method : setters) {
					String field = getFieldName(method.getName());
					//Buscamos la factory del campo
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
							
						} catch (InvocationTargetException ex) {
							
						}
					}
				}
			}
		} catch (InstantiationException ex) {
			
		} catch (IllegalAccessException ex) {
			
		}
		
		return retDummy;
	}
	
	private String getFieldName(String name) {
		name = name.replaceFirst("set", "");
		String remaining = name.substring(1);
		String start = String.valueOf(Character.toLowerCase(name.charAt(0)));
		
		return start + remaining;
	}

	private void populateObject(final T subject, final Type[] genericMetaData, final Map<String, ClassUsageInfo<?>> knownInstances, final ClassBindings classBindings, final List<Exception> exceptions) {
		if (subject instanceof Collection) {
//			populateCollection((Collection<Object>) subject, genericMetaData, knownInstances, classBindings, exceptions);
		} else if (subject instanceof Map) {
//			populateMap((Map<Object, Object>) subject, genericMetaData, knownInstances, classBindings, exceptions);
		} else {
			// populate POJO using it's bean setters (which should always contain exactly one parameter)
			// by creating a new dummy using a ClassBasedFactory for each Method's parameter and finally invoke the method itself
			for (final Method setter : discoverSetters(subject.getClass())) {
				// collect generics meta data if available
				final ClassBasedFactory<T> factory = new ClassBasedFactory<T>((Class<T>) setter.getParameterTypes()[0]);
				final Type genericParameterType = setter.getGenericParameterTypes()[0];
				final boolean isRawClassItself = genericParameterType instanceof Class;
				final Type[] nextGenericsMetaData = isRawClassItself ? null : ((ParameterizedType) genericParameterType).getActualTypeArguments();
				// finally create the parameter with or without generics meta data
				final Object parameter = factory.createDummy(nextGenericsMetaData, knownInstances, classBindings, exceptions);
				try {
					setter.invoke(subject, parameter);
				} catch (final Exception e) {
//					logger.error("error calling setter method [" + setter.getName() + "]", e);
				}
			}
		}
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
