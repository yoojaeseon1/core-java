package chapter4.src2;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ClassTest {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
//		Student stu = new Student();
//		
//		Class<?> cl = stu.getClass();
//		
//		System.out.println(cl.getName());
		
		
		String className = "java.util.Scanner";
		
		Class<?> cl = Class.forName(className);
		
		Method[] methods = cl.getMethods();
		
//		System.out.println(Arrays.toString(methods));
		
		
		Class<?> cl2 = String[].class;
		Class<?> cl3 = Runnable.class;
		Class<?> cl4 = int.class;
		Class<?> cl5 = void.class;
		
		Class<?> cl6 = Student[].class;
		
//		System.out.println(cl2.getName());
//		System.out.println(int[].class.getName());
//		System.out.println(cl2.getCanonicalName());
//		System.out.println(cl6.getCanonicalName());
		
//		System.out.println(cl2.isArray());
//		System.out.println(cl3.isInterface());
//		System.out.println(cl3.isAnonymousClass());
//		System.out.println(cl4.isPrimitive());
//		System.out.println(cl5.isMemberClass());

		
		Student stu = new Student();
		
		System.out.println(stu.getClass() == Student.class);
		
		
	}

}
