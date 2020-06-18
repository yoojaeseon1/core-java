package chapter10.src;

import java.util.Comparator;
import java.util.TreeSet;

public class ThreadTest {

	public static void main(String[] args) throws InterruptedException {
		
		TreeSet<Integer> set = new TreeSet<>(new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return 0;
			}
			
		});
		
		Runnable task = () -> {
			for(int i = 1; i <= 1000000; i++) 
			{
				System.out.println("task1 : " + i);
			}
			
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			
			for(int i = 200; i <= 300; i++) {
				System.out.println("task1 : " + i);
			}
			
		};
		
		Runnable task2 = () -> {
			
			for(int i = 1; i <= 100; i++) 
			{
				System.out.println("task2 : " + i);
			}
			
			
			for(int i = 200; i <= 300; i++) {
				System.out.println("task2 : " + i);
			}
			
		};
		
		Thread thread1 = new Thread(task);
		Thread thread2 = new Thread(task2);
		thread1.start();
		thread1.join(10000);
		
		thread2.start();
		

	}

}
