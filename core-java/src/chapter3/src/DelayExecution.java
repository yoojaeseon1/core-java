package chapter3.src;

import java.util.function.IntConsumer;

public class DelayExecution {
	
	

	public static void main(String[] args) {
		
		
		
//		repeat(10, ()->System.out.println("Hello, World!"));
		
		repeat(10, i -> System.out.println("execution count : " + i));
		
	}
	
//	public static void repeat(int n, Runnable action) {
//		for(int i = 0; i < n; i++) {
//			action.run();
//		}
//	}
	
	public static void repeat(int n, IntConsumer action){

		for(int i = 1; i <= n; i++)
			action.accept(i);

	}

}
