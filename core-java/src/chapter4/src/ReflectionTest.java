package chapter4.src;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class ReflectionTest {

	public static void main(String[] args) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InstantiationException, IntrospectionException {

//		Class<?> cl = Class.forName("chapter4.src.Employee");
//
//		while (cl != null) {
//			for (Method m : cl.getDeclaredMethods()) {
//				System.out.println(Modifier
//						.toString(m.getModifiers()) + " " + m.getReturnType().getCanonicalName() + " " + m.getName() + Arrays.toString(m.getParameters()));
//			}
//			
//			cl = cl.getSuperclass();
//		}
		
		
		Employee emp = new Employee();
		
		Class<?> cl = emp.getClass();
		
//		for(Field f : emp.getClass().getDeclaredFields()) {
//		for(Method m : emp.getClass().getMethods()) {
//			m.setAccessible(true);
//			String value = m.getName();
//			
//			System.out.println(value);
//		}
		
//		
//		Employee emp2 = (Employee) cl.newInstance();
//		
//		emp2.setName("haha");
//		
//		System.out.println(emp2.getName());
		
		BeanInfo info = Introspector.getBeanInfo(cl);
		
		PropertyDescriptor[] props = info.getPropertyDescriptors();
		
		for(PropertyDescriptor prop : props) {
			System.out.println(prop.getName());
		}
		
	}

}
