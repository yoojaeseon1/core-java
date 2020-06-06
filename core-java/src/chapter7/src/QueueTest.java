package chapter7.src;

import java.util.PriorityQueue;
import java.util.Queue;

public class QueueTest {

	public static void main(String[] args) {
		
		Queue<Integer> queue = new PriorityQueue<>();
		
		queue.add(5);
		queue.add(2);
		queue.add(4);
		queue.add(3);
		queue.add(1);
		
		while(queue.size() > 0) {
			int element = queue.remove();
			System.out.println(element);
		}

	}

}
