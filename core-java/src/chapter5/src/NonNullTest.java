package chapter5.src;

import java.util.Objects;

public class NonNullTest {

	private String direction;
	
	public static void main(String[] args) {
		
		NonNullTest test = new NonNullTest();
		
		test.func1();

	}

	public void func1() {
		System.out.println("func1 start");
		func2();
		System.out.println("func1 end");
	}

	public void func2() {

		System.out.println("func2 start");
		func3(null);
		System.out.println("func2 end");
	}
	
	public void func3(String nullString) {
		
		this.direction = Objects.requireNonNull(nullString,"executed requireNonNull");
		
		
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
