package chapter4.src2;

public class Util {
	
	public Object createInstance(String className, ClassLoader loader) throws ClassNotFoundException {
		Class<?> cl = Class.forName(className, true, loader);
		
		return cl;
	}

}
