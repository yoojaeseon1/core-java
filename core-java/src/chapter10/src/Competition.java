package chapter10.src;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Competition {

	private static volatile int count = 0;
	
	public static void main(String[] args) {
		
		Executor executor = Executors.newCachedThreadPool();
		
		for(int i = 1; i <= 100; i++) {
			int taskId = i;
			Runnable task = () -> {
//				System.out.println("before" + taskId + " : " + count);
				for(int k = 1; k <= 1000; k++) {
//					System.out.println(taskId + " : " + count++);
					count++;
				}
				System.out.println(taskId + " : " + count);
			};
			executor.execute(task);
			
		}

	}

}
