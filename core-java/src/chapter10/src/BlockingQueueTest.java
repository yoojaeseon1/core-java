package chapter10.src;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {

	public static void main(String[] args) throws InterruptedException {

		BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

		// Queue<Integer> queue = new LinkedList<>();

		Runnable enqueueTask = () -> {

			for (int i = 1; i <= 100; i++) {
				// System.out.println("enqueue : " + i);
				try {
					queue.put(i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				queue.add(i);
			}
		};
		Runnable dequeueTask = () -> {

			for (int i = 1; i <= 100; i++) {
				try {
					System.out.println("dequeue : " + queue.take());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println("dequeue : " + queue.poll());
			}
		};

		Executor executor = Executors.newCachedThreadPool();

		executor.execute(enqueueTask);
		executor.execute(dequeueTask);

	}

}
