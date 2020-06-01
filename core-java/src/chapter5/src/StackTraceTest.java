package chapter5.src;

public class StackTraceTest {

	public static void main(String[] args) {
		
		func1();

	}

	public static void func1() {
		System.out.println("func1 start");
		func2();
		System.out.println("func1 end");
	}

	public static void func2() {

		System.out.println("func2 start");
		func3();
		System.out.println("func2 end");
	}
	
	public static void func3() {
		
		
		
		try{
			System.out.println("func3 start");
			Class<?> cl = Class.forName("myClass");
			
		} catch(ClassNotFoundException ex) {
			
//			StackTraceElement[] frames = ex.getStackTrace();
//			
//			for(StackTraceElement frame : frames) {
//				System.out.println(frame);
//			}
			
			ex.printStackTrace();
		}
		

		System.out.println("func3 end");
	}

}
