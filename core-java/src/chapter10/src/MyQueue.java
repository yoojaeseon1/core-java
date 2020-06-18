package chapter10.src;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyQueue<E> {

	class Node {
		E value;
		Node next;
	}

	private Node head;
	private Node tail;

	public MyQueue() {

	}

	public synchronized void put(E element) {

		Node node = new Node();
		if (head == null)
			head = node;
		else
			tail.next = node;
		tail = node;
		tail.value = element;
//		notifyAll();
	}

	public synchronized E take() throws InterruptedException {
		while (head == null)
			wait();

		E headElement = this.head.value;
		this.head = head.next;
		return headElement;

	}

	public static void main(String[] args) {

		MyQueue<Integer> queue = new MyQueue<>();

		Executor executor = Executors.newCachedThreadPool();

		executor.execute(() -> {
			for (int i = 1; i <= 1000; i++) {
				queue.put(i);
			}
		});

		executor.execute(() -> {
			for (int i = 1; i <= 1000; i++)
				try {
					System.out.println(queue.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		});

	}
}
