package com.logicommerce.utilities;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Injector {

	private boolean debugMode;

	private Map<Class<?>, Class<?>> interfaceImplementations = new HashMap<>();

	private Map<Class<?>, Object> instances = new HashMap<>();

	private Map<String, Object> properties = new HashMap<>();

	@SuppressWarnings("unchecked")
	public <T> T getInstance(Class<T> clazz) {
		T instance = null;
		try {
			if (instances.containsKey(clazz)) {
				instance = (T) instances.get(clazz);
			} else {
				if (clazz.isInterface()) {
					Class<T> newClazz = getImplementation(clazz);
					if (newClazz == null) {
						debug("AutoInjector Warning! Implementation of " + clazz + " not found!");
						InvocationHandler handler = new MyInvocationHandler();
						return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, handler);
					} else {
						clazz = newClazz;
					}
				}
				instance = clazz.getConstructor().newInstance();
				if (isRequestScope(clazz)) {
					addInstance(instance);
				}
				
				injectFields(instance);
				callPostConstruct(instance);
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassCastException e) {
			debug("AutoInjector Warning! Error occurred while instantiating " + clazz + "! " + e);
			debug(e.getCause().toString());
		}

		return instance;
	}

	private void callPostConstruct(Object instance) throws IllegalAccessException, InvocationTargetException {
		Class<?> clazz = instance.getClass();

		for (Method method : clazz.getMethods()) {
			if (hasPostConstruct(method)) {
				method.invoke(instance, (Object[]) null);
			}
		}
	}

	protected abstract boolean hasPostConstruct(Method method);

	private Injector addImplementation(Class<?> iFace, Class<?> impl) {
		interfaceImplementations.put(iFace, impl);
		return this;
	}

	public Injector addImplementation(Class<?> impl) {
		for (Class<?> iFace : impl.getInterfaces()) {
			addImplementation(iFace, impl);
		}
		return this;
	}

	public Injector addInstance(Class<?> clazz, Object object) {
		if (!clazz.isAssignableFrom(object.getClass())) {
			debug(clazz + " is not assignable from " + object);
			return this;
		}
		instances.put(clazz, object);
		return this;
	}

	public Injector addInstance(Object object) {
		return addInstance(object.getClass(), object);
	}

	public Injector addProperty(String name, Object object) {
		properties.put(name, object);
		return this;
	}

	@SuppressWarnings("unchecked")
	private <T> Class<T> getImplementation(Class<T> clazz) {
		return (Class<T>) (interfaceImplementations.get(clazz));
	}

	private void injectFields(Object instance) throws IllegalAccessException {
		Class<?> clazz = instance.getClass();

		while (clazz != null) {
			for (Field field : clazz.getDeclaredFields()) {
				if (properties.containsKey(field.getName())) {
					setField(field, instance, properties.get(field.getName()));
				} else if (isInjectable(field)) {
					setField(field, instance, getInstance(field.getType()));
				}
			}
			clazz = clazz.getSuperclass();
		}
	}

	protected abstract boolean isRequestScope(Class<?> clazz);
	
	protected abstract boolean isInjectable(Field field);

	private void setField(Field field, Object instance, Object object) throws IllegalAccessException {
		boolean isAccessible = isAccessible(field, instance);
		field.setAccessible(true);
		field.set(instance, object);
		if (!isAccessible) {
			field.setAccessible(false);
		}
	}

	private boolean isAccessible(Field field, Object object) {
		int modifiers = field.getModifiers();
		return !Modifier.isStatic(modifiers) && field.canAccess(object);
	}

	public Injector debug() {
		this.debugMode = true;
		return this;
	}

	private void debug(String message) {
		if (debugMode) {
			Logger.getLogger("com.logicommerce.utilities").log(Level.FINE, message, Injector.class);
		}
	}

}
