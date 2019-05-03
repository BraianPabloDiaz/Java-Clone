import java.lang.reflect.Field;

public class Clone {

	public static Object process(Object original)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException {

		if (null == original) {
			throw new NullPointerException();
		}

		Object copy = original.getClass().newInstance();

		Field[] fieldsClone = copy.getClass().getDeclaredFields();
		for (Field fieldClone : fieldsClone) {
			if(!java.lang.reflect.Modifier.isStatic(fieldClone.getModifiers())) {
				
				fieldClone.setAccessible(true);
				fieldClone.set(copy, Clone.getProperty(original, fieldClone.getName()));
			}
		}

		return copy;
	}

	private static Object getProperty(Object original, String fieldName)
			throws NoSuchFieldException, IllegalAccessException {

		Field field = original.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(original);
	}
}
