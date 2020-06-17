package chapter10.src;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VisibilityTest {

	// private static volatile boolean done = false;
	private static boolean done;

	public static void main(String[] args) {

		Runnable hellos = () -> {
			for (int i = 1; i <= 1000; i++) {
				System.out.println("Hello : " + i);
			}
			System.out.println("done was changed");
		};

		Runnable goodbye = () -> {
			int i = 1;
			// if (!done)
			// while (true) {
			// i++;
			// System.out.println(i++);
			// }
			while (!done) {
				i++;
//				System.out.println(i++);
			}
			System.out.println("goodbye : " + i);
		};

		Executor executor = Executors.newCachedThreadPool();

		executor.execute(hellos);
		executor.execute(goodbye);

	}

}
