package chapter10.src;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

	private int count;

	public LockTest() {
		count = 0;
	}

	public synchronized int getCount() {
		return count;
	}
	
	public synchronized void setCount(int count) {
		this.count = count;
	}
	
	public synchronized void addCount() {
		for(int i = 1; i <= 1000; i++) {
			this.count++;
		}
	}


	public static void main(String[] args) throws InterruptedException {

		Lock countLock = new ReentrantLock();

		Executor executor = Executors.newCachedThreadPool();

		LockTest test = new LockTest();
		
		Queue<Integer> queue = new LinkedList<>();

		// countLock.lock();
		// try {
		// executor.execute(() -> {
		// for (int i = 1; i <= 100000; i++) {
		// test.setCount(test.getCount() + 100);
		// }
		// });
		//
		// executor.execute(() -> {
		// for (int i = 1; i <= 1000; i++) {
		// test.setCount(test.getCount() + 1);
		// }
		// });
		
		
		
//		List<Integer> list = new ArrayList<>();
//
//		for (int i = 1; i <= 100; i++) {
//			int taskId = i;
//			executor.execute(() -> {
//				countLock.lock();
//				try {
//					for (int k = 1; k <= 1000; k++)
//						test.setCount(test.getCount() + 1);
//					System.out.println(taskId + " : " + test.getCount());
//				} finally {
//					countLock.unlock();
//				}
//			});
//		}
		
		for (int i = 1; i <= 100; i++) {
			int taskId = i;
			executor.execute(() -> {
				test.addCount();
					System.out.println(taskId + " : " + test.getCount());
			});
		}
		
		
		
		
			// } finally {
			// countLock.unlock();
			// }

//			Thread.sleep(3000);
//			System.out.println(test.getCount());


	}
}
