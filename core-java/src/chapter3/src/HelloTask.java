package chapter3.src;

public class HelloTask implements Runnable{
	
	@Override
	public void run() {
		
		for(int i = 0; i < 1000; i++) {
			System.out.println(i);
		}
	}
	
	public static void main(String[] args) {
		Runnable task = new HelloTask();
//		Runnable task2 = new HelloTask();
		
		Thread thread = new Thread(task);
		Thread thread2 = new Thread(task);
		
		thread.start();
		thread2.start();
	}
}
