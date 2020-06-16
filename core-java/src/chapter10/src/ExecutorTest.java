package chapter10.src;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorTest {
	
	public static void main(String[] args) {
		
		Runnable hellos = () -> {
			for(int i = 1; i <= 1000; i++) {
				System.out.println("Hello " + i);
			}
		};
		
		Runnable goodbyes = () -> {
			for(int i = 1; i <= 1000; i++) {
				System.out.println("goodbye " + i);
			}
		};
		
		Executor executor = Executors.newCachedThreadPool();
		
		executor.execute(hellos);
		executor.execute(goodbyes);
		
//		Executor executor = Executors.newFixedThreadPool(5);
		
//		int processors = Runtime.getRuntime().availableProcessors();
		
//		System.out.println(processors);
		
	}

}
