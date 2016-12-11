package com.dimit.tools;

import java.lang.reflect.Field;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ReflectUtil {

	/**
	 * 字段过滤处理
	 * 
	 * @param clazz
	 * @param fc
	 * @param ff
	 * @throws IllegalArgumentException
	 */
	public static void doWithDeclaredFields(Class<?> clazz, Consumer<Field> fc, Predicate<Field> ff)
			throws IllegalArgumentException {
		if (clazz == null || clazz == Object.class) {
			return;
		}
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (ff != null && !ff.test(field)) {
				continue;
			}
			fc.accept(field);
		}
	}
}
