package chapter4.src;

public class Util {

	public Object createInstance(String className, ClassLoader loader) throws ClassNotFoundException {
		Class<?> cl = Class.forName(className, true, loader);
		
		System.out.println(cl.getName());
		
		return new Object();
	}
	
	public static void main(String[] args) {
		
//		ClassLoader cl = new ClassLoader();
		Util test = new Util();
		
//		test.createInstance("chapter4.src.Util", new ClassLoader);
	}
}
