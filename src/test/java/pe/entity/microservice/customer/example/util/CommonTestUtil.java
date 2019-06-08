package pe.entity.microservice.customer.example.util;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

/**
 * Common Test Util
 * 
 * @author lpazd
 *
 */
public class CommonTestUtil {

	@SuppressWarnings("rawtypes")
	public static void assertAnnotations(List<Class> annotationClasses, List<Annotation> annotations) {
		if (annotationClasses.size() != annotations.size()) {
			throw new AssertionError("The number of annotations don't match");
		}
		annotationClasses.forEach(ac -> {
			long cnt = annotations.stream().filter(a -> a.annotationType().isAssignableFrom(ac)).count();
			if (cnt == 0) {
				throw new AssertionError("The type of the annotation was not found");
			}
		});
	}

	@SuppressWarnings("rawtypes")
	public static void assertType(Class c, Class... annotationClasses) {
		assertAnnotations(Arrays.asList(annotationClasses), Arrays.asList(c.getAnnotations()));
	}

	@SuppressWarnings("rawtypes")
	public static void assertField(Class c, String fieldName, Class... annotationClasses) {
		try {
			assertAnnotations(Arrays.asList(annotationClasses),
					Arrays.asList(c.getDeclaredField(fieldName).getAnnotations()));
		} catch (NoSuchFieldException nsfe) {
			System.out.println("nsfe: " + nsfe);
			throw new AssertionError(nsfe);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void assertMethod(Class c, String getterName, Class... annotationClasses) {
		try {
			assertAnnotations(Arrays.asList(annotationClasses),
					Arrays.asList(c.getDeclaredMethod(getterName).getAnnotations()));
		} catch (NoSuchMethodException nsfe) {
			throw new AssertionError(nsfe);
		}
	}
}
