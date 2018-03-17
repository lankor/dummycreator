package org.dummycreator.dummyfactories;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.dummycreator.ClassBindings;
import org.dummycreator.ClassUsageInfo;

public class FixedObjectFromListFactory<T> extends DummyFactory<T> {
	private List<T> list;
	
	private Random random = new Random();
	
	public FixedObjectFromListFactory(List<T> list) {
		this.list = list;
	}
	
	@Override
	public T createDummy(Type[] genericMetaData, Map<String, ClassUsageInfo<?>> knownInstances,
			ClassBindings classBindings, List<Exception> exceptions) {
		return createDummy();
	}
	
	public T createDummy() {
		int index = random.nextInt(list.size());
		
		return list.get(index);
	}

}
